package com.icemetalpunk.infernaltech.registries;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.icemetalpunk.infernaltech.items.BasicItem;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	private HashMap<String, BasicItem> registry = new HashMap<>();

	public ItemRegistry() {

	}

	@Nullable
	public BasicItem put(@Nonnull String name, @Nonnull BasicItem item) {
		return this.registry.put(name, item);
	}

	@Nullable
	public BasicItem get(String name) {
		return registry.get(name);
	}

	@SubscribeEvent
	public void register(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();
		for (BasicItem item : this.registry.values()) {
			reg.register(item);
		}
	}

	public void registerModels(ModelRegistryEvent ev) {
		for (BasicItem item : this.registry.values()) {
			item.registerModel(ev);
		}
	}

}
