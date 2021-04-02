package com.existingeevee.betweentinker;

import org.lwjgl.util.Point;

import com.existingeevee.betweentinker.tools.BetweenAxe;
import com.existingeevee.betweentinker.tools.BetweenBow;
import com.existingeevee.betweentinker.tools.BetweenPickaxe;
import com.existingeevee.betweentinker.tools.BetweenShovel;
import com.existingeevee.betweentinker.tools.BetweenSword;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.tools.harvest.TinkerHarvestTools;
import thebetweenlands.common.handler.OverworldItemHandler;

@Mod(modid = VersionInfo.MODID, name = VersionInfo.NAME, version = VersionInfo.VERSION)
public class BetweenTinker
{

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ConfigHandler.init(event);
    	if(Loader.isModLoaded("thebetweenlands")) {
    		blackListTinkerTools();
    	}
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	BetweenTinkerTools.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	//for (Point x : TinkerRegistryClient.getToolBuildInfoForTool(TinkerHarvestTools.hatchet).positions) {
    		//Logging.Log("(" + x.getX() + ", " + x.getY() + ")");
    	//}
    }
      
    //		    

    private static void blackListTinkerTools() {
    	OverworldItemHandler.TOOL_BLACKLIST.put(new ResourceLocation(VersionInfo.MODID, "tinker_blacklist"), stack -> {
		return (stack.getItem() instanceof ToolCore &&
				!(stack.getItem() instanceof BetweenAxe)// &&
				//!(stack.getItem() instanceof BetweenBow) &&
				//!(stack.getItem() instanceof BetweenPickaxe) &&
				//!(stack.getItem() instanceof BetweenShovel) &&
				//!(stack.getItem() instanceof BetweenSword)	
				);
		});
    }
    
}
