package com.icemetalpunk.infernaltech.blocks;

import com.icemetalpunk.infernaltech.client.render.RenderGazingGlass;
import com.icemetalpunk.infernaltech.interfaces.ITileRegistrar;
import com.icemetalpunk.infernaltech.tile.TileEntityGazingGlass;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGazingGlass extends BasicBlock implements ITileRegistrar, ITileEntityProvider {

	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockGazingGlass(String mod, String name, CreativeTabs tab) {
		super(mod, name, Material.GLASS, tab);
		this.setSoundType(SoundType.GLASS).setHardness(0.3F);
		this.setDefaultState(this.getDefaultState().withProperty(POWERED, false));
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		/*
		 * TileEntity te = blockAccess.getTileEntity(pos); if (!(te instanceof
		 * TileEntityGazingGlass)) { return 0; } TileEntityGazingGlass gazing =
		 * (TileEntityGazingGlass) te; return gazing.getPower();
		 */
		return (blockState.getValue(POWERED) ? 15 : 0);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { POWERED });
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(POWERED, meta == 1);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(POWERED) ? 1 : 0);
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
