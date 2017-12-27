package com.icemetalpunk.infernaltech.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class SmeltingRecipe {
	public ItemStack inputStack;
	public ItemStack output;
	public Item inputItem;
	public boolean isStack;
	public int xp;

	public SmeltingRecipe(ItemStack in, ItemStack out, int xp) {
		this.xp = xp;
		this.output = out;
		this.inputStack = in;
		this.isStack = true;
	}

	public SmeltingRecipe(ItemStack in, ItemStack out) {
		this(in, out, 0);
	}

	public SmeltingRecipe(Item in, ItemStack out, int xp) {
		this.xp = xp;
		this.output = out;
		this.inputItem = in;
		this.isStack = false;
	}

	public SmeltingRecipe(Item in, ItemStack out) {
		this(in, out, 0);
	}

	public void register() {
		if (this.isStack) {
			FurnaceRecipes.instance().addSmeltingRecipe(this.inputStack, this.output, this.xp);
		} else {
			FurnaceRecipes.instance().addSmelting(this.inputItem, this.output, this.xp);
		}
	}

}
