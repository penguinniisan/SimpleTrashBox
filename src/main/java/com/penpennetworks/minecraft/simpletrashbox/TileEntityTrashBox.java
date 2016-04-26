package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityTrashBox extends TileEntity implements IInventory {

	// 保持するアイテムスタック
	private ItemStack[] trashboxItemStacks  = new ItemStack[5];

	// カスタム名保存用
	private String trashboxCustomName;

	// コンストラクタ
	public TileEntityTrashBox(){
		super();
		System.out.println("Create New Tile Entity");
	}

	// インベントリのサイズ(内部で保持しているサイズを返す)
	@Override
	public int getSizeInventory() {
		return trashboxItemStacks.length;
	}

	/*
	 * 結構いろんな頻度で呼ばれてた。
	 * 画面の更新時にも呼ばれてるんじゃないかって言うレベル
	 */
	// 渡されたスロット番号のアイテムを返す
	@Override
	public ItemStack getStackInSlot(int slotNo) {
		return trashboxItemStacks[slotNo];
	}

	// 指定されたアイテムスロットのアイテムスタックから指定された数だけ引き
	// そのアイテムスタックを返す
	// スロットからアイテムが取り出されたときに呼び出されている。
	@Override
	public ItemStack decrStackSize(int paramIndex, int paramCount ) {
		return ItemStackHelper.func_188382_a(this.trashboxItemStacks, paramIndex, paramCount);
	}

	// 指定されたアイテムスロットのアイテムを削除し、そのアイテムスタックを返す
	// (未検証)
	@Override
	public ItemStack removeStackFromSlot(int paramIndex) {
		System.out.println("SlotNo[" + paramIndex + "]");
		return ItemStackHelper.func_188383_a(this.trashboxItemStacks, paramIndex);
	}

	// 名前の取得
	@Override
	public String getName() {
		return hasCustomName() ? this.trashboxCustomName : "container.simpletrashbox";
	}

	// カスタム名を持っているか
	@Override
	public boolean hasCustomName() {
		return (this.trashboxCustomName != null)
				&& (!this.trashboxCustomName.isEmpty());
	}

	// カスタム名の設定
	public void setCustomInventoryName(String paramName) {
		this.trashboxCustomName = paramName;
	}

	// 表示名の取得
	@Override
	public ITextComponent getDisplayName() {
		return hasCustomName() ? new TextComponentString(getName())
				: new TextComponentTranslation(getName(), new Object[0]);
	}

	// アイテムがスロットに配置されたときの処理
	@Override
	public void setInventorySlotContents(int paramIndex, ItemStack paramItemStack) {

		/*
		 *  TileEntityFurnaceより（今回未使用）
		 *
		// 同じアイテムが既に存在するかをチェック
		boolean isSameItemStackAlreadySlot = paramItemStack != null
			&& paramItemStack.isItemEqual(this.trashboxItemStacks[paramIndex])
			&& ItemStack.areItemStackTagsEqual(paramItemStack, this.trashboxItemStacks[paramIndex]);
		*/

		// 渡されたItemStackをスロットに入れる
		this.trashboxItemStacks[paramIndex] = paramItemStack;

		// スタックの上限をチェックし超えていたら最大値とする
		if( paramItemStack != null && paramItemStack.stackSize > getInventoryStackLimit() ){
			paramItemStack.stackSize = getInventoryStackLimit();
		}

		/*
		 * TileEntityChestの場合は必ず呼んでいる。
		 * 再読み込みさせるため？メソッドの先まで未検証
		 * NBT周りの処理をやってそうな気もする(名前的に)
		 */
		markDirty();

		/*
		 *  TileEntityFurnaceよりコピペ。（今回未使用）
		 *
		// スロットが0番(燃やされるもの)のときに同じアイテムでなければ燃焼時間をリセットしている。
		if ((p_setInventorySlotContents_1_ == 0) && (!flag)) {
			this.totalCookTime = getCookTime(p_setInventorySlotContents_2_);
			this.cookTime = 0;
			// たぶん再読み込みさせるんだと思う
			markDirty();
		}
		*/

	}

	/*
	 * ちなみにこれはチェスト。
	 * fillWithLoot()はダンジョンチェストの場合に未開封なら内容物の生成を行っている。
	 * 　→ 詳しくは TileEntityLockableLoot を参照
	 *
	public void setInventorySlotContents(int p_setInventorySlotContents_1_,
			ItemStack p_setInventorySlotContents_2_) {
		fillWithLoot((EntityPlayer) null);
		this.chestContents[p_setInventorySlotContents_1_] = p_setInventorySlotContents_2_;
		if ((p_setInventorySlotContents_2_ != null)
				&& (p_setInventorySlotContents_2_.stackSize > getInventoryStackLimit())) {
			p_setInventorySlotContents_2_.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}
	 */


	// NBTから読み込む
	// (未検証)
	public void readFromNBT( NBTTagCompound compound ) {
		super.readFromNBT(compound);
		System.out.println("Compound[" + compound.toString() + "]" );

		// カスタム名が設定されていれば
		if( compound.hasKey("CustomName", 8) ){
			this.trashboxCustomName = compound.getString("CustomName");
		}

		// アイテムの一覧を取得
		NBTTagList nbttaglist = compound.getTagList("Items", 10);

		// アイテムスタックの一覧を作り直す
		this.trashboxItemStacks = new ItemStack[getSizeInventory()];

		// インベントリにアイテムスタックを追加していく
		for( int i = 0 ; i < nbttaglist.tagCount() ; i++ ){

			// iの位置のデータを取得する
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);

			// スロットデータの取得
			int j = nbttagcompound.getByte("Slot");

			// 取得したデータが0～スロット数までの場合
			if( (j >= 0) && ( j < this.trashboxItemStacks.length ) ){

				// データを受け取る
				this.trashboxItemStacks[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound);

			}
		}

	}

	// NBTに書き込む
	// (未検証)
	@Override
	public void writeToNBT( NBTTagCompound compound ){
		super.writeToNBT(compound);
		System.out.println("Compound[" + compound.toString() + "]" );

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0 ; i < this.trashboxItemStacks.length ; i++ ){
			if( this.trashboxItemStacks[i] != null ){
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				nbtTagCompound.setByte( "Slot", (byte)i );
				this.trashboxItemStacks[i].writeToNBT(nbtTagCompound);
				nbttaglist.appendTag(nbtTagCompound);
			}
		}
		compound.setTag( "Items", nbttaglist );

		// カスタム名
		if( hasCustomName() ){
			compound.setString( "CustomName", this.trashboxCustomName );
		}

	}

	// スタックサイズの最大
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	// プレイヤーが使うことが出来るか
	public boolean isUseableByPlayer(EntityPlayer paramEntityPlayer) {
		// その場所にあるのが自分なら大丈夫？
		return this.worldObj.getTileEntity(this.pos) == this;
	}

	// 指定されたスロットに指定されたアイテムを入れることができるか？
	// たとえば燃料しか入れられないなどの指定に使う
	@Override
	public boolean isItemValidForSlot(int paramInt, ItemStack paramItemStack) {
		return true;
	}

	// アイテムスタックの中身を綺麗にしてる
	// 親クラスでやっといてくれよ
	// (未検証)
	@Override
	public void clear() {
		System.out.println("ALL CLEAR!!");
		for (int i = 0; i < this.trashboxItemStacks.length; i++) {
			this.trashboxItemStacks[i] = null;
		}
	}

	/* 以下未使用 */

	// 追加情報を取得する
	// (未検証)
	@Override
	public int getField(int id) {
		System.out.println("ID[" + id + "]" );
		/*
		 * TileEntityFurnaceより抜粋
		 * 燃焼時間などの情報を返している。
		 * 引数はidらしい
		 * 何もない場合は0を返している
		 *
		switch (id) {
		case 0:
			return this.furnaceBurnTime;
		case 1:
			return this.currentItemBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		}
		*/
		return 0;
	}

	// 追加情報を設定する
	// (未検証)
	@Override
	public void setField(int id, int value) {
		System.out.println("ID[" + id + "] VALUE[" + value + "]" );
		/*
		 * TileEntityFurnaceより抜粋
		 * こちらは設定を行っている。
		 *
		switch (id) {
		case 0:
			this.furnaceBurnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
		*/
	}

	// 追加情報の数
	// (未検証)
	@Override
	public int getFieldCount() {
		System.out.println("Count[0]" );
		return 0;
	}

	// 開かれたとき
	@Override
	public void openInventory(EntityPlayer paramEntityPlayer) {
	}

	// 閉じられたとき
	@Override
	public void closeInventory(EntityPlayer paramEntityPlayer) {
	}


}
