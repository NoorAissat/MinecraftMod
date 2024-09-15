package com.noor.stitchcraft.datagen;

import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.item.ModItems;
import com.noor.stitchcraft.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, StitchCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
tag(ModTags.Items.TRANSFORMABLE_ITEMS)
        .add(ModItems.ALEXANDRITE.get())
        .add(ModItems.RAW_ALEXANDRITE.get())
        .add(Items.COAL)
        .add(Items.STICK)
        .add(Items.COMPASS);
    }
}
