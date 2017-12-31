package com.icemetalpunk.infernaltech.interfaces;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public interface ITileRegistrar {
	public Class<? extends TileEntity> getTEClass();

	public String getTEName();

	@Nullable
	default public Class<? extends TileEntitySpecialRenderer> getRendererClass() {
		return null;
	}
}
