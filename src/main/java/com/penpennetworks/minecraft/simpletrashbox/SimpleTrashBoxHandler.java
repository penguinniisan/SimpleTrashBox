package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SimpleTrashBoxHandler implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if( id == SimpleTrashBoxCore.GUI_ID) {
			System.out.println("CreateGUI");
			TileEntity tile = world.getTileEntity(new BlockPos(x,y,z));
			return new ContainerGuiTrashBox( player. inventory,(IInventory) tile );
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if( id == SimpleTrashBoxCore.GUI_ID ){
			System.out.println("Create");
			TileEntity tile = world.getTileEntity(new BlockPos(x,y,z));
            return new ContainerTrashBox(player.inventory,(IInventory) tile);
        }
        return null;
	}

}
