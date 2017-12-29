package com.icemetalpunk.infernaltech.registries;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.icemetalpunk.infernaltech.InfernalTech;
import com.icemetalpunk.infernaltech.interfaces.ISmeltingOutput;
import com.icemetalpunk.infernaltech.items.BasicArmor;
import com.icemetalpunk.infernaltech.items.BasicItem;
import com.icemetalpunk.infernaltech.util.SmeltingRecipe;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemRegistry {
	private static HashMap<String, BasicItem> registry = new HashMap<>();
	private static HashMap<String, BasicArmor> armorRegistry = new HashMap<>();

	static {
		armorRegistry.put("spirit_chestplate", new BasicArmor(InfernalTech.MODID, "spirit_chestplate",
				BasicArmor.SPIRIT_MATERIAL, EntityEquipmentSlot.CHEST, InfernalTech.tab));
		armorRegistry.put("spirit_helmet", new BasicArmor(InfernalTech.MODID, "spirit_helmet",
				BasicArmor.SPIRIT_MATERIAL, EntityEquipmentSlot.HEAD, InfernalTech.tab));
		armorRegistry.put("spirit_boots", new BasicArmor(InfernalTech.MODID, "spirit_boots", BasicArmor.SPIRIT_MATERIAL,
				EntityEquipmentSlot.FEET, InfernalTech.tab));
		armorRegistry.put("spirit_leggings", new BasicArmor(InfernalTech.MODID, "spirit_leggings",
				BasicArmor.SPIRIT_MATERIAL, EntityEquipmentSlot.LEGS, InfernalTech.tab));
	}

	public ItemRegistry() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Nullable
	public BasicItem put(@Nonnull String name, @Nonnull BasicItem item) {
		return registry.put(name, item);
	}

	@Nullable
	public BasicArmor put(@Nonnull String name, @Nonnull BasicArmor armor) {
		return armorRegistry.put(name, armor);
	}

	@Nullable
	public BasicItem get(String name) {
		return registry.get(name);
	}

	@Nullable
	public BasicArmor getArmor(String name) {
		return armorRegistry.get(name);
	}

	@SubscribeEvent
	public void register(RegistryEvent.Register<Item> ev) {
		IForgeRegistry<Item> reg = ev.getRegistry();
		for (BasicItem item : registry.values()) {
			reg.register(item);

			// Register smelting recipes
			if (item instanceof ISmeltingOutput) {
				ISmeltingOutput smelt = (ISmeltingOutput) item;
				List<SmeltingRecipe> recipes = smelt.getSmeltingRecipes();
				for (SmeltingRecipe recipe : recipes) {
					recipe.register();
				}
			}

		}

		// Armor items... I know, poor code design, it was a quick fix
		for (BasicArmor item : armorRegistry.values()) {
			reg.register(item);

			// Register smelting recipes
			if (item instanceof ISmeltingOutput) {
				ISmeltingOutput smelt = (ISmeltingOutput) item;
				List<SmeltingRecipe> recipes = smelt.getSmeltingRecipes();
				for (SmeltingRecipe recipe : recipes) {
					recipe.register();
				}
			}

		}
	}

	public void registerModels(ModelRegistryEvent ev) {
		for (BasicItem item : registry.values()) {
			item.registerModel(ev);
		}
		for (BasicArmor item : armorRegistry.values()) {
			item.registerModel(ev);
		}
	}

}
