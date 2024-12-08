package com.noor.stitchcraft.datagen;

import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StitchCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ALEXANDRITE.get());
        basicItem(ModItems.SHADOW_INGOT.get());
        basicItem(ModItems.CHISEL.get());
        handheldItem(ModItems.SHADOW_SWORD);
        handheldItem(ModItems.SHADOW_SCYTHE);
        handheldItem(ModItems.SHADOW_AXE);
        handheldItem(ModItems.SHADOW_BLADE);
        handheldItem(ModItems.SHADOW_PICKAXE);
        handheldItem(ModItems.SHADOW_SHARD);
        handheldItem(ModItems.SHADOW_GEM);
        handheldItem(ModItems.SHADOW_BLADE_HANDLE);


    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(StitchCraft.MOD_ID, "item/" + item.getId().getPath()));
    }


}
