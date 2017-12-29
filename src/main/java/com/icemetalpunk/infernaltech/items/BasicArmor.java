package com.icemetalpunk.infernaltech.items;

import com.icemetalpunk.infernaltech.InfernalTech;
import com.icemetalpunk.infernaltech.interfaces.IModeledObject;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;

public class BasicArmor extends ItemArmor implements IModeledObject {
	public BasicArmor(String mod, String name, ArmorMaterial mat, EntityEquipmentSlot slot) {
		super(mat, 5, slot);
		this.setRegistryName(new ResourceLocation(mod, name)).setUnlocalizedName(mod + "." + name);
	}

	public BasicArmor(String mod, String name, ArmorMaterial mat, EntityEquipmentSlot slot, CreativeTabs tab) {
		this(mod, name, mat, slot);
		this.setCreativeTab(tab);
	}

	public static final ArmorMaterial SPIRIT_MATERIAL = EnumHelper.addArmorMaterial("spirit_glass",
			InfernalTech.MODID + ":spirit_glass", 10, new int[] { 2, 5, 6, 2 }, 20,
			SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0f);

	@Override
	public void registerModel(ModelRegistryEvent ev) {
		ModelResourceLocation model = new ModelResourceLocation(this.getRegistryName(), "inventory");
		ModelLoader.registerItemVariants(this, model);
		ModelLoader.setCustomModelResourceLocation(this, 0, model);
	}
}
