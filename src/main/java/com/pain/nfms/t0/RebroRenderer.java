package com.pain.nfms.t0;

import javax.annotation.Nonnull;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RebroRenderer extends HumanoidMobRenderer<Rebro, RebroModel<Rebro>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("tmod", "textures/entity/rebro.png");

    public RebroRenderer(EntityRendererProvider.Context context) {
        super(context, new RebroModel<>(context.bakeLayer(TModelLayers.RebroModelLocation)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(@Nonnull Rebro entity) {
        return TEXTURE;
    }
}
