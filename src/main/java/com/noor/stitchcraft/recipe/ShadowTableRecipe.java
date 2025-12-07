package com.noor.stitchcraft.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record ShadowTableRecipe (Ingredient inputItem, ItemStack output) implements Recipe<ShadowTableRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    // read in JSON file --> turns into new ShadowTableRecipe



    @Override
    public boolean matches(ShadowTableRecipeInput pInput, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(ShadowTableRecipeInput shadowTableRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SHADOW_TABLE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.SHADOW_TABLE_TYPE.get();
    }


    public static class Serializer implements RecipeSerializer<ShadowTableRecipe> {
        public static final MapCodec<ShadowTableRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ShadowTableRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ShadowTableRecipe::output)
        ).apply(inst, ShadowTableRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ShadowTableRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, ShadowTableRecipe::inputItem,
                        ItemStack.STREAM_CODEC, ShadowTableRecipe::output,
                        ShadowTableRecipe::new);

        @Override
        public MapCodec<ShadowTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ShadowTableRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
