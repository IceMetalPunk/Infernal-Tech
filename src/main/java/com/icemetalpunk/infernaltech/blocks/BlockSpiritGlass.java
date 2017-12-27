package com.icemetalpunk.infernaltech.blocks;

import java.util.ArrayList;
import java.util.List;

import com.icemetalpunk.infernaltech.interfaces.ISmeltingOutput;
import com.icemetalpunk.infernaltech.util.SmeltingRecipe;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpiritGlass extends BasicBlock implements ISmeltingOutput {

	public BlockSpiritGlass(String mod, String name, CreativeTabs tab) {
		super(mod, name, Material.GLASS, tab);
		this.setHardness(0.3F);
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks
	 * for render
	 */
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (blockState != iblockstate) {
			return true;
		}

		if (block == this) {
			return false;
		}

		return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}

	@Override
	public List<SmeltingRecipe> getSmeltingRecipes() {
		ArrayList<SmeltingRecipe> list = new ArrayList<>();
		list.add(new SmeltingRecipe(Item.getItemFromBlock(Blocks.SOUL_SAND), new ItemStack(this.getItemBlock(), 1)));
		return list;
	}

}
