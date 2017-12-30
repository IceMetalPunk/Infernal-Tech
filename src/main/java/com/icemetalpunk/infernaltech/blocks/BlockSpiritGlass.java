package com.icemetalpunk.infernaltech.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.icemetalpunk.infernaltech.interfaces.ISmeltingOutput;
import com.icemetalpunk.infernaltech.items.BasicArmor;
import com.icemetalpunk.infernaltech.util.SmeltingRecipe;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSpiritGlass extends BasicBlock implements ISmeltingOutput {

	public BlockSpiritGlass(String mod, String name, CreativeTabs tab) {
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

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		if (entityIn instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entityIn;
			Iterable<ItemStack> armorList = living.getArmorInventoryList();
			for (ItemStack stack : armorList) {
				Item item = stack.getItem();
				if (item instanceof ItemArmor) {
					ArmorMaterial mat = ((ItemArmor) item).getArmorMaterial();
					if (mat == BasicArmor.CRYSTAL_SPIRIT || mat == BasicArmor.SPIRIT_MATERIAL) {
						return;
					}
				}
			}
		}
		addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(worldIn, pos));
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
