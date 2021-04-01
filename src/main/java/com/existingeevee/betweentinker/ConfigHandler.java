package com.existingeevee.betweentinker;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	
	public static File configFile;

	public static Configuration config;
	
	//public static boolean shouldForceLoadDust;
	
	//public static boolean shouldAllowJokeItems = true;
	
	//public static boolean shouldAllowTwilightForest = true;
	
	public static void initConfig(File file)
	{
		config = new Configuration(file, VersionInfo.VERSION);
		
		config.load();
		
		String category;

		category = "General";
		config.addCustomCategoryComment(category, "General configs for the mod");
		//ConfigHandler.shouldAllowTwilightForest = config.getBoolean("twilightforest", category, true, "Set to \"false\" if you want to not allow Twilight Forest Compatibility to load.");
		//ConfigHandler.shouldAllowIceAndFire = config.getBoolean("iceandfire", category, true, "Set to \"false\" if you want to not allow Ice and Fire Compatibility to load.");

		config.save();		
	}
	
	public static void init(FMLPreInitializationEvent event)
	{
		configFile = new File(event.getModConfigurationDirectory() + "/" + VersionInfo.MODID);
		configFile.mkdirs();
		initConfig(new File(configFile.getPath(), VersionInfo.MODID + ".cfg"));
		//config.load();
	}
}
