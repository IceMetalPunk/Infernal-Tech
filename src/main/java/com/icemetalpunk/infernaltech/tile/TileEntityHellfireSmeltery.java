package com.icemetalpunk.infernaltech.tile;

import com.icemetalpunk.infernaltech.InfernalTech;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.MathHelper;

public class TileEntityHellfireSmeltery extends TileEntityFurnace {

	private String customName = null;
	public float burnModifier = 1.0f;

	public TileEntityHellfireSmeltery(float modifier) {
		this.burnModifier = modifier;
	}

	public TileEntityHellfireSmeltery() {
		this(1.0f);
	}

	@Override
	public void setCustomInventoryName(String name) {
		super.setCustomInventoryName(name);
		this.customName = name;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.customName = compound.getString("CustomName");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		if (this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}
		return compound;
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container." + InfernalTech.MODID + ".hellfiresmeltery";
	}

	@Override
	public int getCookTime(ItemStack stack) {
		return MathHelper.ceil(200 * this.burnModifier);
	}
}
