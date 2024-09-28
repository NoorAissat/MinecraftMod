package com.noor.stitchcraft.item;


import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.item.custom.ChiselItem;
import com.noor.stitchcraft.item.custom.ModTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StitchCraft.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",() -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",() -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",() -> new ChiselItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> SHADOW_SWORD = ITEMS.register("shadow_sword",() -> new SwordItem(ModTiers.SHADOW, new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.SHADOW,10,6f))));

    public static final RegistryObject<Item> SHADOW_SCYTHE = ITEMS.register("shadow_scythe",() -> new SwordItem(ModTiers.SHADOW, new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.SHADOW,10,6f))));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
