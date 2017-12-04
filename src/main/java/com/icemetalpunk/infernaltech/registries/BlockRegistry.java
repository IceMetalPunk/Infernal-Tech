package com.icemetalpunk.infernaltech.registries;

import java.util.HashMap;

import com.icemetalpunk.infernaltech.InfernalTech;
import com.icemetalpunk.infernaltech.blocks.BasicBlock;
import com.icemetalpunk.infernaltech.blocks.BlockHellfireSmeltery;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry {
	private static HashMap<String, BasicBlock> registry = new HashMap<>();

	static {
		registry.put("hellfire_smeltery",
				new BlockHellfireSmeltery(InfernalTech.MODID, "hellfire_smeltery", InfernalTech.tab, 0, false));
	}

	public BlockRegistry() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public BasicBlock get(String name) {
		return registry.get(name);
	}

	public void put(String name, BasicBlock block) {
		this.registry.put(name, block);
	}

	@SubscribeEvent
	public void registerAll(RegistryEvent.Register<Block> ev) {
		IForgeRegistry<Block> reg = ev.getRegistry();
		for (BasicBlock block : registry.values()) {
			reg.register(block);
		}
	}

	public void registerModels(ModelRegistryEvent ev) {
		for (BasicBlock block : registry.values()) {
			block.registerModel(ev);
		}
	}

	public void register(IForgeRegistry<Block> reg) {
		for (BasicBlock block : this.registry.values()) {
			reg.register(block);
		}
	}

	public void registerItemBlocks(IForgeRegistry<Item> reg) {
		for (BasicBlock block : this.registry.values()) {
			Item itemBlock = block.getItemBlock();
			if (itemBlock != null) {
				reg.register(itemBlock);
			}
		}
	}

}
