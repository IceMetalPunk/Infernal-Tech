package com.icemetalpunk.infernaltech.proxy;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent ev) {
		this.blocks.registerModels(ev);
		this.items.registerModels(ev);
	}

}
