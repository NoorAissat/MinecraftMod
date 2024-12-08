package com.noor.stitchcraft.datagen;

import com.noor.stitchcraft.StitchCraft;

import com.noor.stitchcraft.block.ModBlocks;
import net.minecraft.data.PackOutput;

import net.minecraft.world.level.block.Block;

import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {


   public ModBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
       super(packOutput, StitchCraft.MOD_ID, existingFileHelper);
   }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.MAGIC_BLOCK);


    }

    private void blockWithItem (RegistryObject<Block> blockRegistryObject){

        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
