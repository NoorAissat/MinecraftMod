package com.noor.stitchcraft.recipe;

import com.noor.stitchcraft.StitchCraft;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, StitchCraft.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, StitchCraft.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ShadowTableRecipe>>SHADOW_TABLE_SERIALIZER =
            SERIALIZERS.register("shadow_table", ShadowTableRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<ShadowTableRecipe>> SHADOW_TABLE_TYPE =
            TYPES.register("shadow_table", () -> new RecipeType<ShadowTableRecipe>() {
                @Override
                public String toString() {
                    return "shadow_table";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
