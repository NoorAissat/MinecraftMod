package com.noor.stitchcraft.client;

import com.noor.stitchcraft.item.custom.ShadowScytheItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShadowScytheRenderer extends GeoItemRenderer<ShadowScytheItem> {
    public ShadowScytheRenderer() {
        super( new ShadowScytheModel() );
    }
}
