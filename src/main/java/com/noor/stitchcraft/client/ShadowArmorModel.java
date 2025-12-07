package com.noor.stitchcraft.client;

import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.item.custom.ShadowArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShadowArmorModel extends GeoModel<ShadowArmorItem> {
    @Override
    public ResourceLocation getModelResource(ShadowArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(StitchCraft.MOD_ID, "geo/shadow_armor2.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShadowArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(StitchCraft.MOD_ID, "textures/models/shadow_armor2.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShadowArmorItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(StitchCraft.MOD_ID, "animations/shadow_scythe.animation.json");
    }
}
