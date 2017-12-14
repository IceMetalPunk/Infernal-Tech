package com.icemetalpunk.infernaltech.gui;

import com.icemetalpunk.infernaltech.containers.ContainerHellfireSmeltery;
import com.icemetalpunk.infernaltech.registries.GuiRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class InfernalTechGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiRegistry.GUI_HELLFIRE_SMELTERY) {
			return new ContainerHellfireSmeltery(player.inventory,
					(IInventory) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiRegistry.GUI_HELLFIRE_SMELTERY) {
			return new GuiHellfireSmeltery(player.inventory, (IInventory) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

}
