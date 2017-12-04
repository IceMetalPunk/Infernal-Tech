package com.icemetalpunk.infernaltech.proxy;

import java.util.HashMap;
import java.util.Map;

import com.icemetalpunk.infernaltech.registries.BlockRegistry;
import com.icemetalpunk.infernaltech.registries.ItemRegistry;
import com.icemetalpunk.infernaltech.tile.TileEntityHellfireSmeltery;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public BlockRegistry blocks = new BlockRegistry();
	public ItemRegistry items = new ItemRegistry();
	public final HashMap<String, Class<? extends TileEntity>> tileEntities = new HashMap<>();

	public CommonProxy() {
		MinecraftForge.EVENT_BUS.register(this);
		this.tileEntities.put("hellfire_smeltery", TileEntityHellfireSmeltery.class);
	}

	public void preInit(FMLPreInitializationEvent ev) {

	}

	public void init(FMLInitializationEvent ev) {

	}

	public void postInit(FMLPostInitializationEvent ev) {

	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> ev) {
		this.blocks.register(ev.getRegistry());
		for (Map.Entry<String, Class<? extends TileEntity>> entry : this.tileEntities.entrySet()) {
			GameRegistry.registerTileEntity(entry.getValue(), entry.getKey());
		}
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> ev) {
		this.items.register(ev.getRegistry());
		this.blocks.registerItemBlocks(ev.getRegistry());
	}
}
