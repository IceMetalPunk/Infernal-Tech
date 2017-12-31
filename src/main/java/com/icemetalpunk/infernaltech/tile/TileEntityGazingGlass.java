package com.icemetalpunk.infernaltech.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TileEntityGazingGlass extends TileEntity implements ITickable {
	protected float angle = 0.0f;

	public TileEntityGazingGlass() {
		super();
	}

	@Override
	public void update() {
		this.angle = 0.0f;
		float x = this.getPos().getX() + 0.5f;
		float y = this.getPos().getY();
		float z = this.getPos().getZ() + 0.5f;
		EntityPlayer player = this.getWorld().getClosestPlayer(x, y, z, 25, false);
		if (player != null) {
			BlockPos playerPos = player.getPosition();
			float angleRad = (float) MathHelper.atan2(playerPos.getX() - x, playerPos.getZ() - z);
			this.angle = angleRad * 180.0f / (float) Math.PI;

		}
	}

	public float getAngle() {
		return this.angle;
	}
}
