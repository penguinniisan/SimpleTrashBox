package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerTrashBox extends Container {

	private final IInventory trashboxInventory;

	public ContainerTrashBox( InventoryPlayer player, IInventory inventory ){

		// 渡されたごみ箱の中身を受け取る
		this.trashboxInventory = inventory;

		// ごみ箱の中身用のアイテムスロット作成
		for (int x = 0; x < inventory.getSizeInventory(); x++) {
			addSlotToContainer(new Slot(inventory, x, 44 + x * 18, 20));
		}

		// Playerインベントリの基本場所(Y)
		int baseline = 51;

		// 持ち物スロットを作成(Inventoryの中身)
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				// スロットの追加
				addSlotToContainer(
					new Slot(
						player,				// どのInventory?
						x  + y * 9 + 9,		// スロット番号
						8  + x * 18,		// X座標(描画位置)
						y * 18 + baseline	// Y座標(描画位置)
					)
				);
			}
		}

		// ホットスロットを作成(1～9までのやつ)
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(
				new Slot(
					player,			// どのInventory?
					x,				// スロット番号
					8 + x * 18,		// X座標(描画位置)
					58 + baseline	// Y座標(描画位置)
				)
			);
		}

	}

	/*
	 * 描画のたびに呼ばれるほど呼ばれる頻度が高い。
	 * おそらく途中でキャンセルされる場合があるから？
	 */
	// そのプレイヤーがこの箱を使えるかどうかを返す(未検証)
	@Override
	public boolean canInteractWith(EntityPlayer paramEntityPlayer) {
		//System.out.println("Player[" + paramEntityPlayer + "]" );
		return this.trashboxInventory.isUseableByPlayer(paramEntityPlayer);
	}

	// コンテナが閉じられたときの処理。
	// TileEntityに対してcloseInventory()を呼び出している
	@Override
	public void onContainerClosed(EntityPlayer paramEntityPlayer) {
		//System.out.println("Player[" + paramEntityPlayer + "]" );
		super.onContainerClosed(paramEntityPlayer);
		this.trashboxInventory.closeInventory(paramEntityPlayer);
	}

	/*
	public ItemStack transferStackInSlot(EntityPlayer p_transferStackInSlot_1_,
			int p_transferStackInSlot_2_) {
		ItemStack lvt_3_1_ = null;
		Slot lvt_4_1_ = (Slot) this.inventorySlots
				.get(p_transferStackInSlot_2_);
		if ((lvt_4_1_ != null) && (lvt_4_1_.getHasStack())) {
			ItemStack lvt_5_1_ = lvt_4_1_.getStack();
			lvt_3_1_ = lvt_5_1_.copy();
			if (p_transferStackInSlot_2_ < this.hopperInventory
					.getSizeInventory()) {
				if (!mergeItemStack(lvt_5_1_,
						this.hopperInventory.getSizeInventory(),
						this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!mergeItemStack(lvt_5_1_, 0,
					this.hopperInventory.getSizeInventory(), false)) {
				return null;
			}
			if (lvt_5_1_.stackSize == 0) {
				lvt_4_1_.putStack(null);
			} else {
				lvt_4_1_.onSlotChanged();
			}
		}
		return lvt_3_1_;
	}
	*/


}
