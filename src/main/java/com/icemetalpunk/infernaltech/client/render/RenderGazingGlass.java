package com.icemetalpunk.infernaltech.client.render;

import com.icemetalpunk.infernaltech.tile.TileEntityGazingGlass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RenderGazingGlass extends TileEntitySpecialRenderer<TileEntityGazingGlass> {
	@Override
	public void render(TileEntityGazingGlass te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		World world = te.getWorld();
		EntityItem item = new EntityItem(world, 0.0d, 0.0d, 0.0d, new ItemStack(Items.ENDER_EYE, 1));
		item.hoverStart = 0.0F;

		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5 + te.getYOffset(), (float) z + 0.5F);
		GlStateManager.rotate(te.getAngle(), 0.0f, 1.0f, 0.0f);

		Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);

		GlStateManager.popMatrix();

	}
}
