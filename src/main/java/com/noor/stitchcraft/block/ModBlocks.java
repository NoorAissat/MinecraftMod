package com.noor.stitchcraft.block;


import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.block.custom.MagicBlock;
import com.noor.stitchcraft.block.custom.ShadowTable;
import com.noor.stitchcraft.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, StitchCraft.MOD_ID);

    public static final RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block", ()-> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> RAW_ALEXANDRITE_BLOCK = registerBlock("raw_alexandrite_block", ()-> new Block(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)));

    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore", ()-> new DropExperienceBlock(UniformInt.of(2,4),BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ALEXANDRITE_DEEPSLATE_ORE = registerBlock("alexandrite_deepslate_ore", ()-> new DropExperienceBlock(UniformInt.of(3,6), BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> SHADOW_END_ORE = registerBlock("shadow_end_ore", () -> new DropExperienceBlock(UniformInt.of(5, 9), BlockBehaviour.Properties.of().strength(7f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SHADOW_NETHER_ORE = registerBlock("shadow_nether_ore", () -> new DropExperienceBlock(UniformInt.of(1, 5), BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SHADOW_DEEPSLATE_ORE = registerBlock("shadow_deepslate_ore", ()-> new DropExperienceBlock(UniformInt.of(3,6), BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));







    public static final RegistryObject<Block> MAGIC_BLOCK = registerBlock("magic_block", ()-> new MagicBlock(BlockBehaviour.Properties.of().strength(2f).noLootTable()));
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    public static final RegistryObject<Block> SHADOW_TABLE = registerBlock("shadow_table", () -> new ShadowTable(BlockBehaviour.Properties.of()));

    public static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }



    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
