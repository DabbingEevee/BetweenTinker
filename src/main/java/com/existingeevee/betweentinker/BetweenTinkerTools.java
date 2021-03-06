package com.existingeevee.betweentinker;

import com.existingeevee.betweentinker.tools.BetweenAxe;
import com.existingeevee.betweentinker.tools.ItemBase;

import slimeknights.tconstruct.library.TinkerRegistry;
//import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

public class BetweenTinkerTools {

	public static ToolPart betweenAxeHead;
	public static ToolPart betweenPickHead;
	public static ToolPart betweenShovelHead;
	public static ToolPart betweenSwordBlade;
	
	public static ToolCore toolBetweenAxe;
	
	public static void init() {
		betweenAxeHead = (ToolPart) new ToolPart(Material.VALUE_Ingot * 3).setUnlocalizedName("blaxehead");
		//RegisterHelper.registerToolPartModel(betweenAxeHead);
		//betweenPickHead = (ToolPart) new ToolPart(Material.VALUE_Ingot * 3).setUnlocalizedName("blpickaxehead");
		//betweenShovelHead = (ToolPart) new ToolPart(Material.VALUE_Ingot * 1).setUnlocalizedName("blshovelhead");
		//betweenSwordBlade = (ToolPart) new ToolPart(Material.VALUE_Ingot * 2).setUnlocalizedName("blswordblade");
		
		RegisterHelper.registerItem(new ItemBase("i"));
		
		RegisterHelper.registerItem(betweenAxeHead);
		//RegisterHelper.registerItem(betweenPickHead);
		//RegisterHelper.registerItem(betweenShovelHead);
		//RegisterHelper.registerItem(betweenSwordBlade);

		toolBetweenAxe = new BetweenAxe();
		RegisterHelper.registerItem(toolBetweenAxe);
	}
	
}
