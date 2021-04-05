package com.existingeevee.betweentinker.proxy;

import com.existingeevee.betweentinker.Logging;
import com.existingeevee.betweentinker.RegisterHelper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

public class ClientProxy implements IProxy{
	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	protected void registerModels(ModelRegistryEvent e) {

	}
	
	@Override
	public void init() {
		/*/Logging.Log("test");
		for (Item item : RegisterHelper.items) {
			//
			if (item instanceof ToolCore) {
				//Logging.Log(item.getUnlocalizedName() + " - toolcore");
				//ModelRegisterUtil.registerToolModel((ToolCore) item);
			} else if (item instanceof ToolPart){
				//Logging.Log(item.getUnlocalizedName() + " - toolpart");

				//ModelRegisterUtil.registerPartModel((ToolPart)item);
			} else {
				//Logging.Log(item.getUnlocalizedName() + " - else");
				RegisterHelper.registerItemModel(item);
			}
			
		}
		for (Block block : RegisterHelper.blocks) {
			RegisterHelper.registerBlockModel(block);
		}*/
	}
	
	@Override
	public void postInit() {
		
	}
	
}
