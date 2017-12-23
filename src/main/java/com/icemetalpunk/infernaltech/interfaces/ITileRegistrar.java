package com.icemetalpunk.infernaltech.interfaces;

import net.minecraft.tileentity.TileEntity;

public interface ITileRegistrar {
	public Class<? extends TileEntity> getTEClass();

	public String getTEName();
}
