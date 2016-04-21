package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTrashBox extends Block {

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

		/*
		// GUIを開くよ
		player.openGui(
			GUISampleCore.INSTANCE,	// GUIのインスタンス
			GUISampleCore.GUI_ID,	// GUI_ID
			world,					// 開く世界ｗ
			blockpos.getX(),		// ブロックの位置 X
			blockpos.getY(),		// ブロックの位置 Y
			blockpos.getZ()			// ブロックの位置 Z
		);
		*/

		return true;
	}

	public TileEntity createNewTileEntity(World world, int intparam) {
		System.out.println("makemakemakemakemakemake");
		return new TileEntityTrashBox();
	}
}
