package com.noor.stitchcraft.screen;

import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.screen.custom.ShadowTableMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, StitchCraft.MOD_ID);

    public static final RegistryObject<MenuType<ShadowTableMenu>> SHADOW_TABLE_MENU =
            MENUS.register("shadow_table_menu", () -> IForgeMenuType.create(ShadowTableMenu::new));


    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
