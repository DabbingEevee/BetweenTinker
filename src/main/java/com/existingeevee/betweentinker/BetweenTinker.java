package com.existingeevee.betweentinker;

import com.existingeevee.betweentinker.tools.Tools;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
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
    	BetweenTinkerTools.init();
    	Tools.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
    
    private static void blackListTinkerTools() {
    	OverworldItemHandler.TOOL_BLACKLIST.put(new ResourceLocation(VersionInfo.MODID, "tinker_blacklist"), stack -> {
			return (stack.getItem() instanceof ToolCore);
		});
    }
    
}
