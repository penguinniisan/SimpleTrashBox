package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerTrashBox extends Container {

	public ContainerTrashBox( InventoryPlayer player ){

		// 持ち物スロットを作成(Inventoryの中身)
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				// スロットの追加
				addSlotToContainer(
					new Slot(
						player,			// どのInventory?
						x  + y * 9 + 9,	// スロット番号
						8  + x * 18,	// X座標(描画位置)
						84 + y * 18		// Y座標(描画位置)
					)
				);
			}
		}

		// ホットスロットを作成(1～9までのやつ)
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(
				new Slot(
					player,		// どのInventory?
					x,			// スロット番号
					8 + x * 18,	// X座標(描画位置)
					142			// Y座標(描画位置)
				)
			);
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer paramEntityPlayer) {
		return true;
	}

}
