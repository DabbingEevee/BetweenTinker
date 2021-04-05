package com.existingeevee.betweentinker;

import com.existingeevee.betweentinker.proxy.IProxy;
import com.existingeevee.betweentinker.tools.BetweenAxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
import thebetweenlands.common.handler.OverworldItemHandler;

@Mod(modid = VersionInfo.MODID, name = VersionInfo.NAME, version = VersionInfo.VERSION)
public class BetweenTinker
{

	@SidedProxy(clientSide = "com.existingeevee.betweentinker.proxy.ClientProxy", serverSide = "com.existingeevee.betweentinker.proxy.ServerProxy")
	public static IProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	proxy.preInit();
    	
    	ConfigHandler.init(event);
    	
    	if(Loader.isModLoaded("thebetweenlands")) {
    		blackListTinkerTools();
    	}
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	BetweenTinkerTools.init();

    	proxy.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit();
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
