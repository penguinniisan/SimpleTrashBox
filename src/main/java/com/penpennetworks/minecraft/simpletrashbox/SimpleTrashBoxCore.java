package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION )
public class SimpleTrashBoxCore {

	@Mod.Instance(Reference.MOD_ID)
	public static SimpleTrashBoxCore INSTANCE;

	// Mod内での識別用ID
	// 同じMod内でかぶらなければいくつでも良い。
	// 複数のGUIを作成するModの場合は気をつける
	public final static int GUI_ID = 0;

	public static Block trashBoxBlock;

	// Event Handlers
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){

		// ブロック登録の準備
		trashBoxBlock = new BlockTrashBox();
		ResourceLocation registryName = new ResourceLocation(Reference.MOD_ID,"simpletrashbox");
		ItemBlock trashBoxItemBlock = new ItemBlock(trashBoxBlock);

		// ブロック登録
		GameRegistry.register(trashBoxBlock, registryName);
		GameRegistry.register(trashBoxItemBlock, registryName);

		// 後でサバ・クラにわける
		GameRegistry.registerTileEntity(TileEntityTrashBox.class, "simpletrashboxentity");

		// クライアントサイド
		if( e.getSide() == Side.CLIENT ){

			ModelLoader.setCustomModelResourceLocation(
					trashBoxItemBlock,
				0,
				new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID,"simpletrashbox"), "inventory")
			);

		}

	}

	@EventHandler
	public void init(FMLInitializationEvent e){
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new SimpleTrashBoxHandler());
	}

}
