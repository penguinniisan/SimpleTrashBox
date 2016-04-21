package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;

public class TileEntityTrashBox extends TileEntity implements ISidedInventory {

	public TileEntityTrashBox(){
		super();
		System.out.println("ConstructorConstructorConstructorConstructor");
	}

	@Override
	public int getSizeInventory() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int paramInt) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ItemStack decrStackSize(int paramInt1, int paramInt2) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int paramInt) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void setInventorySlotContents(int paramInt, ItemStack paramItemStack) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getInventoryStackLimit() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer paramEntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void openInventory(EntityPlayer paramEntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void closeInventory(EntityPlayer paramEntityPlayer) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean isItemValidForSlot(int paramInt, ItemStack paramItemStack) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public int getField(int paramInt) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void setField(int paramInt1, int paramInt2) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getFieldCount() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String getName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing paramEnumFacing) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean canInsertItem(int paramInt, ItemStack paramItemStack,
			EnumFacing paramEnumFacing) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean canExtractItem(int paramInt, ItemStack paramItemStack,
			EnumFacing paramEnumFacing) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
