package com.icemetalpunk.infernaltech.blocks;

import com.icemetalpunk.infernaltech.client.render.RenderGazingGlass;
import com.icemetalpunk.infernaltech.interfaces.ITileRegistrar;
import com.icemetalpunk.infernaltech.tile.TileEntityGazingGlass;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGazingGlass extends BasicBlock implements ITileRegistrar, ITileEntityProvider {
	public BlockGazingGlass(String mod, String name, CreativeTabs tab) {
		super(mod, name, Material.GLASS, tab);
		this.setSoundType(SoundType.GLASS).setHardness(0.3F);
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public Class<? extends TileEntity> getTEClass() {
		return TileEntityGazingGlass.class;
	}

	@Override
	public String getTEName() {
		return "gazing_glass";
	}

	@Override
	public Class<? extends TileEntitySpecialRenderer> getRendererClass() {
		return RenderGazingGlass.class;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityGazingGlass();
	}
}
