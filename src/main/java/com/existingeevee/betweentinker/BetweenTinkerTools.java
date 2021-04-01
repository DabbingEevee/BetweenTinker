package com.existingeevee.betweentinker;

//import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolPart;

public class BetweenTinkerTools {

	public static ToolPart betweenAxeHead;
	//public static ToolPart betweenBow;
	public static ToolPart betweenPickHead;
	public static ToolPart betweenShovelHead;
	public static ToolPart betweenSwordBlade;

	
	public static void init() {
		betweenAxeHead = new ToolPart(Material.VALUE_Ingot * 3);
		betweenPickHead = new ToolPart(Material.VALUE_Ingot * 3);
		betweenShovelHead = new ToolPart(Material.VALUE_Ingot * 1);
		betweenSwordBlade = new ToolPart(Material.VALUE_Ingot * 2);
		
		RegisterHelper.registerItem(betweenAxeHead);
		RegisterHelper.registerItem(betweenPickHead);
		RegisterHelper.registerItem(betweenShovelHead);
		RegisterHelper.registerItem(betweenSwordBlade);
		
		//TinkerRegistry.registerToolPart(betweenAxeHead);
		//TinkerRegistry.registerToolPart(betweenPickHead);
		//TinkerRegistry.registerToolPart(betweenShovelHead);
		//TinkerRegistry.registerToolPart(betweenSwordBlade);

	}
	
}
