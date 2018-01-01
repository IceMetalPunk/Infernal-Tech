package com.icemetalpunk.infernaltech.tile;

import java.util.List;

import com.icemetalpunk.infernaltech.blocks.BlockGazingGlass;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TileEntityGazingGlass extends TileEntity implements ITickable {
	protected float angle = 0.0f;
	protected int power = 0;
	protected float yoffset = 0.0f;

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

		int tempPower = calcPower();
		if (this.power != tempPower) {
			IBlockState state = this.world.getBlockState(this.pos);
			this.world.setBlockState(this.pos, state.withProperty(BlockGazingGlass.POWERED, tempPower > 0));
		}
		this.power = tempPower;

		if (this.power > 0 && this.yoffset < 0.25) {
			this.yoffset += 0.01;
		} else if (this.power <= 0 && this.yoffset > 0) {
			this.yoffset -= 0.01;
		}

	}

	public int calcPower() {

		List<EntityPlayer> players = this.world.playerEntities;

		for (EntityPlayer player : players) {
			Vec3d vec3d = player.getLook(1.0F).normalize();
			Vec3d vec3d1 = new Vec3d(this.pos.getX() + 0.5 - player.posX,
					this.pos.getY() + 0.5 - (player.posY + (double) player.getEyeHeight()),
					this.pos.getZ() + 0.5 - player.posZ);
			double d0 = vec3d1.lengthVector();
			vec3d1 = vec3d1.normalize();
			double d1 = vec3d.dotProduct(vec3d1);
			if (d1 > 1.0D - 0.025D / d0) {
				RayTraceResult trace = this.traceFromEntity(player, d0, 1.0f);
				if (trace.getBlockPos().equals(this.pos)) {
					return 15;
				}
			}
		}
		return 0;
	}

	public RayTraceResult traceFromEntity(EntityLivingBase ent, double blockReachDistance, float partialTicks) {
		Vec3d vec3d = ent.getPositionEyes(partialTicks);
		Vec3d vec3d1 = ent.getLook(partialTicks);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance,
				vec3d1.z * blockReachDistance);
		return ent.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return (oldState.getBlock() != newState.getBlock());
	}

	public int getPower() {
		return this.power;
	}

	public float getAngle() {
		return this.angle;
	}

	public float getYOffset() {
		return this.yoffset;
	}
}
