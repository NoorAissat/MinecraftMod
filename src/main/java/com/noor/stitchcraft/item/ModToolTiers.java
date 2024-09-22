package com.noor.stitchcraft.item;

import com.noor.stitchcraft.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;


public class ModToolTiers {
    public static final Tier SHADOW = new ForgeTier(2031,9,4.0F,18, ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL,() -> Ingredient.of(ModItems.ALEXANDRITE.get()), ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL);
}
