package com.pain.nfms.t0;

import javax.annotation.Nonnull;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class TModelLayers {
    @Nonnull
    public static final ModelLayerLocation RebroModelLocation = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("tmod", "rebro"), "main");

    @Nonnull
    public static final ModelLayerLocation METLA_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("tmod", "metla"), "main");
}
