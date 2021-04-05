package com.existingeevee.betweentinker.tools;

import net.minecraft.item.Item;

public class ItemBase extends Item{
	
	//private List<String> list;

	public ItemBase(String itemName) {
		
		super();
		setupItem(this, itemName.toLowerCase());
		

	}
	
	public void setupItem(final Item item, final String itemName) {
		item.setUnlocalizedName(itemName);
	}
	
	
	

	
	
}
