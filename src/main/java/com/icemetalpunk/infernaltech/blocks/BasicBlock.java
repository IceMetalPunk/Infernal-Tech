package com.icemetalpunk.infernaltech.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.icemetalpunk.infernaltech.interfaces.IModeledObject;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

public class BasicBlock extends Block implements IModeledObject {

	private ItemBlock itemBlock = new ItemBlock(this);

	public BasicBlock(String mod, String name, Material materialIn) {
		super(materialIn);
		ResourceLocation resLoc = new ResourceLocation(mod, name);
		this.setRegistryName(resLoc).setUnlocalizedName(mod + "." + name);
		this.itemBlock.setRegistryName(resLoc).setUnlocalizedName(mod + "." + name);
	}

	public BasicBlock(String mod, String name, Material materialIn, CreativeTabs tab) {
		this(mod, name, materialIn);
		this.itemBlock.setCreativeTab(tab);
		this.setCreativeTab(tab);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.itemBlock;
	}

	public BasicBlock setNoItem() {
		this.itemBlock = null;
		return this;
	}

	@Nullable
	public ItemBlock getItemBlock() {
		return this.itemBlock;
	}

	@Override
	public void registerModel(ModelRegistryEvent ev) {
		if (this.itemBlock != null) {
			ModelResourceLocation model = new ModelResourceLocation(this.getRegistryName(), "inventory");
			ModelLoader.registerItemVariants(this.itemBlock, model);
			ModelLoader.setCustomModelResourceLocation(this.itemBlock, 0, model);
		}
	}

}
