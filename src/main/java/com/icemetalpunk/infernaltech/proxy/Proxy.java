package com.icemetalpunk.infernaltech.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Proxy {

	public Proxy() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent ev) {
	}

	@EventHandler
	public void init(FMLInitializationEvent ev) {
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent ev) {
	}

}
