package com.icemetalpunk.infernaltech.proxy;

import com.icemetalpunk.infernaltech.InfernalTech;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends Proxy {

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent ev) {
		InfernalTech.blocks.registerModels(ev);
		InfernalTech.items.registerModels(ev);
	}

}
