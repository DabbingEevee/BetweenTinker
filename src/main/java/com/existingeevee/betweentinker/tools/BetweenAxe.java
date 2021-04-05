package com.existingeevee.betweentinker.tools;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.existingeevee.betweentinker.BetweenTinkerTools;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.client.particle.Particles;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.AoeToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;
import thebetweenlands.api.item.CorrosionHelper;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.api.item.ICorrodible;
import thebetweenlands.common.item.BLMaterialRegistry;

public class BetweenAxe extends AoeToolCore implements ICorrodible, IAnimatorRepairable{

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG,
			Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER,
			Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

	public static final ImmutableSet<net.minecraft.block.material.Material> effective_materials = ImmutableSet.of(
			net.minecraft.block.material.Material.WOOD, net.minecraft.block.material.Material.VINE,
			net.minecraft.block.material.Material.PLANTS, net.minecraft.block.material.Material.GOURD,
			net.minecraft.block.material.Material.CACTUS);

	public BetweenAxe() {
		this(
			PartMaterialType.handle(TinkerTools.toolRod), 
			PartMaterialType.head(BetweenTinkerTools.betweenAxeHead),
			PartMaterialType.extra(TinkerTools.binding)
			);
		this.setUnlocalizedName("blaxe");
		TinkerRegistry.registerToolCrafting(this);
		try {
			ToolBuildGuiInfo blaxeInfo = new ToolBuildGuiInfo(this);
			blaxeInfo.addSlotPosition(22, 53); 
			blaxeInfo.addSlotPosition(31, 22); 
			blaxeInfo.addSlotPosition(51, 34); 
		    TinkerRegistryClient.addToolBuilding(blaxeInfo);
		} catch (NoSuchMethodError e) { }
		
		//this.setRegistryName("blaxe");
	}

	protected BetweenAxe(PartMaterialType... requiredComponents) {
		super(requiredComponents);

		addCategory(Category.HARVEST);
		addCategory(Category.WEAPON);

		this.setHarvestLevel("axe", 0);
	}

	@Override
	public boolean isEffective(IBlockState state) {
		return effective_materials.contains(state.getMaterial()) || EFFECTIVE_ON.contains(state.getBlock());
	}

	@Override
	public float damagePotential() {
		return 1.1f;
	}

	@Override
	public double attackSpeed() {
		return 1.1f; // a bit faster than vanilla axes
	}

	@Override
	public float knockback() {
		return 1.3f;
	}

	// hatches 1 : leaves 0
	// @Override
	// public float getStrVsBlock(ItemStack stack, IBlockState state) {
	// if(state.getBlock().getMaterial(state) ==
	// net.minecraft.block.material.Material.LEAVES) {
	// return ToolHelper.calcDigSpeed(stack, state);
	// }
	// return super.getStrVsBlock(stack, state);
//  }

	@Override
	public void afterBlockBreak(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase player,
			int damage, boolean wasEffective) {
		// breaking leaves does not reduce durability
		if (state.getBlock().isLeaves(state, world, pos)) {
			damage = 0;
		}
		super.afterBlockBreak(stack, world, state, pos, player, damage, wasEffective);
	}

	@Override
	public boolean dealDamage(ItemStack stack, EntityLivingBase player, Entity entity, float damage) {
		boolean hit = super.dealDamage(stack, player, entity, damage);

		if (hit && readyForSpecialAttack(player)) {
			TinkerTools.proxy.spawnAttackParticle(Particles.HATCHET_ATTACK, player, 0.8d);
		}

		return hit;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity,
			EntityLivingBase attacker) {
		return true;
	}

	@Override
	protected ToolNBT buildTagData(List<Material> materials) {
		ToolNBT data = buildDefaultTag(materials);
		data.attack += 0.5f;
		return data;
	}
	
	@Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        net.minecraft.block.material.Material material = state.getMaterial();
        float str = material != net.minecraft.block.material.Material.WOOD && material != net.minecraft.block.material.Material.PLANTS && material != net.minecraft.block.material.Material.VINE ? super.getDestroySpeed(stack, state) : ToolHelper.calcDigSpeed(stack, state);
        str = CorrosionHelper.getDestroySpeed(str, stack, state);
        return str; 
    }

    @Override
    public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
        return CorrosionHelper.shouldCauseBlockBreakReset(oldStack, newStack);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return CorrosionHelper.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity holder, int slot, boolean isHeldItem) {
        CorrosionHelper.updateCorrosion(itemStack, world, holder, slot, isHeldItem);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        return CorrosionHelper.getAttributeModifiers(super.getAttributeModifiers(slot, stack), slot, stack, ATTACK_DAMAGE_MODIFIER, ToolHelper.getActualAttack(stack));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        CorrosionHelper.addCorrosionTooltips(stack, tooltip, flagIn.isAdvanced());
    }

    @Override
    public int getMinRepairFuelCost(ItemStack stack) {
		return Math.round(stack.getMaxDamage() / 120) + 1;
    }

    @Override
    public int getFullRepairFuelCost(ItemStack stack) {
		return Math.round(stack.getMaxDamage() / 75) + 1;
    }

	@Override
	public int getFullRepairLifeCost(ItemStack arg0) {
		return Math.round(arg0.getMaxDamage() / 75) + 1;
	}


	@Override
	public int getMinRepairLifeCost(ItemStack arg0) {
		return Math.round(arg0.getMaxDamage() / 60) + 1;
	}
}