package com.existingeevee.betweentinker.tools;

import com.existingeevee.betweentinker.BetweenTinkerTools;
import com.existingeevee.betweentinker.Logging;
import com.existingeevee.betweentinker.RegisterHelper;

import net.minecraft.item.Item;

public class Tools {

	public static Item betweenAxe;
	
	public static void init() {
		Logging.Log(BetweenTinkerTools.betweenAxeHead.toString());
		
		betweenAxe = new BetweenAxe();
		
		RegisterHelper.registerItem(betweenAxe);
	}
	
}
