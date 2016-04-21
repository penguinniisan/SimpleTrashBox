package com.penpennetworks.minecraft.simpletrashbox;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod( modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION )
public class SimpleTrashBoxCore {

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

		// クライアントサイド
		if( e.getSide() == Side.CLIENT ){

			ModelLoader.setCustomModelResourceLocation(
					trashBoxItemBlock,
				0,
				new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID,"simpletrashbox"), "inventory")
			);

		}

	}

}
