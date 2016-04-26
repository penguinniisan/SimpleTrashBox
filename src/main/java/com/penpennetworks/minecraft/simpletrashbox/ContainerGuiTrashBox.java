package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class ContainerGuiTrashBox extends GuiContainer {

	// テクスチャのパス
	private static final ResourceLocation TEXTURE = new ResourceLocation( Reference.MOD_ID, "textures/gui/simpletrashbox_frame.png" );

	public ContainerGuiTrashBox( InventoryPlayer player, IInventory entity ){
		super( new ContainerTrashBox( player, entity ) );
	}

	public void initGui() {
		super.initGui();
		System.out.println("Initialized!!!");
	}

	/*GUIの文字等の描画処理*/
	@Override
	protected void drawGuiContainerForegroundLayer( int mouseX, int mouseZ ){
		super.drawGuiContainerForegroundLayer( mouseX, mouseZ );
		this.fontRendererObj.drawString(
			I18n.format("container.crafting", new Object[0]), // Strings
			97, 8, // X, Y
			4210752 // 色
		);
	}

	/*GUIの背景の描画処理*/
	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTick, int mouseX, int mouseZ ){
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(TEXTURE);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	// GUIが開いている時にゲームの処理を止めるかどうか。
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
