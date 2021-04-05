package com.existingeevee.betweentinker;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

public class RegisterHelper {

	//public static Item[] items = new Item[0];
	public static List<Item> items = new ArrayList<Item>();
	public static List<Block> blocks = new ArrayList<Block>();
	
	public static void registerBlock(Block block) {
		RegisterHelper.registerBlock(block, ItemBlock::new);
	}

	public static void registerBlock(Block block, @Nullable Function<Block, ItemBlock> itemBlock) {
		String name = block.getUnlocalizedName().substring(5);
		ForgeRegistries.BLOCKS.register(block.setRegistryName(name));

		if (itemBlock != null) {
			ForgeRegistries.ITEMS.register(itemBlock.apply(block).setRegistryName(block.getRegistryName()));
			//try {
			blocks.add(block);
				//registerBlockModel(block);
			//} catch (NoSuchMethodError e) {
				//return;
			//}
			//if(block instanceof BlockFluidClassic) {
			//  RenderHandler.registerCustomMeshesAndStates(block);
			//}
		}
	}
	
	public static void registerItem(Item item) {
		String name = item.getUnlocalizedName().substring(5);
		//item.setCreativeTab(CreativeTabs.MISC);
		item.setRegistryName(name);
		ForgeRegistries.ITEMS.register(item);
		//items.add(item);
		if (item instanceof ToolCore) {
			//Logging.Log(item.getUnlocalizedName() + " - toolcore");
			ModelRegisterUtil.registerToolModel((ToolCore) item);
		} else if (item instanceof ToolPart){
			//Logging.Log(item.getUnlocalizedName() + " - toolpart");
			ModelRegisterUtil.registerPartModel((ToolPart) item);
		} else {
			//Logging.Log(item.getUnlocalizedName() + " - else");
			RegisterHelper.registerItemModel(item);
		}
		
		//}
	}

	public static void registerBiome(Biome biome) {
		ForgeRegistries.BIOMES.register(biome);
	}

	public static boolean registerMaterial(Material material, Fluid fluid, String suffix) {
		//List<String> list = Arrays.asList(ConfigHandler.blacklist);
		//if(!list.contains(material.getIdentifier())) {
		if (fluid != null) {
			integrate(material, fluid, suffix);
		} else {
			TinkerRegistry.addMaterial(material);
		}
		return true;
		//} 
		//return false; 
		
	}

	@SideOnly(Side.CLIENT)
	public static void registerItemModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(
				VersionInfo.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerBlockModel(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(
				VersionInfo.MODID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
	public static void registerModifier(IModifier modifier) {
		TinkerRegistry.registerModifier(modifier);
	}	

	/*
	 * public static void registerTrait(AbstractTrait trait) {
	 * 
	 * }
	 */

	/*
	 * public static void registerFluid(Fluid fluid) { String name =
	 * fluid.getUnlocalizedName().substring(5); FluidRegistry.registerFluid(fluid);
	 * FluidRegistry.addBucketForFluid(fluid); Block fluidBlock = fluid.getBlock();
	 * 
	 * if (fluidBlock != null) { Item fluidItem = Item.getItemFromBlock(fluidBlock);
	 * BlockFluid.FluidStateMapper mapper = new BlockFluid.FluidStateMapper(fluid);
	 * 
	 * if (fluidItem != Items.AIR) ModelLoader.setCustomMeshDefinition(fluidItem,
	 * mapper);
	 * 
	 * ModelLoader.setCustomStateMapper(fluidBlock, mapper);
	 * 
	 * } }
	 */

	private static MaterialIntegration add(MaterialIntegration integration) {
		return TinkerRegistry.integrate(integration);
	}

	private static MaterialIntegration integrate(Material material, Fluid fluid, String suffix) {
		return add(new MaterialIntegration(material, fluid, suffix));
	}

	public static void registerFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}

}
