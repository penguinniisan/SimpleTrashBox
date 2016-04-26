package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTrashBox extends BlockContainer {

	public BlockTrashBox(){
		super(Material.wood);
		setCreativeTab(CreativeTabs.tabDecorations);
		setUnlocalizedName("simpletrashbox");
		setHardness(0.5f);
	}

	@Override
	public boolean onBlockActivated(
		World world, BlockPos blockpos, IBlockState blockstate,
		EntityPlayer player, EnumHand hand, ItemStack handitem,
		EnumFacing facing, float hitX, float hitY, float hitZ
	) {

		// GUIを開くよ
		player.openGui(
			SimpleTrashBoxCore.INSTANCE,	// Modのインスタンス
			SimpleTrashBoxCore.GUI_ID,		// GUI_ID
			world,							// どこで開かれたか
			blockpos.getX(),				// ブロックの位置 X
			blockpos.getY(),				// ブロックの位置 Y
			blockpos.getZ()					// ブロックの位置 Z
		);

		return true;
	}
	public boolean onBlockEventReceived(World p_onBlockEventReceived_1_,
			BlockPos p_onBlockEventReceived_2_,
			IBlockState p_onBlockEventReceived_3_,
			int p_onBlockEventReceived_4_, int p_onBlockEventReceived_5_) {
		System.out.println("ひゃっほい！");
		return super.onBlockEventReceived(p_onBlockEventReceived_1_, p_onBlockEventReceived_2_, p_onBlockEventReceived_3_, p_onBlockEventReceived_4_, p_onBlockEventReceived_5_);
	}

	public TileEntity createNewTileEntity(World world, int meta) {
		System.out.println("World[" + world.toString() + "] meta[" + meta + "]");
		return new TileEntityTrashBox();
	}

	// 描画タイプ(モデル)
	public EnumBlockRenderType getRenderType(IBlockState blockstate) {
		return EnumBlockRenderType.MODEL;
	}

}
