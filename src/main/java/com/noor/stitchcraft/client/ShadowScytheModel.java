package com.noor.stitchcraft.client;

import com.noor.stitchcraft.StitchCraft;
import com.noor.stitchcraft.item.custom.ShadowScytheItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShadowScytheModel extends GeoModel<ShadowScytheItem> {
    @Override
    public ResourceLocation getModelResource(ShadowScytheItem shadowScytheItem) {
        return ResourceLocation.fromNamespaceAndPath(StitchCraft.MOD_ID, "geo/shadow_scythe.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShadowScytheItem shadowScytheItem) {
        return ResourceLocation.fromNamespaceAndPath(StitchCraft.MOD_ID, "textures/models/shadow_scythe.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShadowScytheItem shadowScytheItem) {
        return null;
    }
}
