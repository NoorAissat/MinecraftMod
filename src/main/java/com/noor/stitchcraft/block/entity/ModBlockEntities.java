package com.noor.stitchcraft.block.entity;

import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.block.ModBlocks;
import com.noor.stitchcraft.block.entity.custom.ShadowTableEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, StitchCraft.MOD_ID);


    public static final RegistryObject<BlockEntityType<ShadowTableEntity>> SHADOW_TABLE_BE =
            BLOCK_ENTITIES.register("shadow_table_be", () -> BlockEntityType.Builder.of(
                    ShadowTableEntity::new, ModBlocks.SHADOW_TABLE.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}