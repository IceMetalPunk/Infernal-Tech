package com.icemetalpunk.infernaltech.proxy;

import com.icemetalpunk.infernaltech.InfernalTech;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends Proxy {

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent ev) {
		InfernalTech.blocks.registerModels(ev);
		InfernalTech.items.registerModels(ev);
	}

	@SubscribeEvent
	public void registerAll(RegistryEvent.Register<Block> ev) {
		InfernalTech.blocks.registerTESRs();
	}

}
