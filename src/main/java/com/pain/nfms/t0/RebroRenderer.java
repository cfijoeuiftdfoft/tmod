package com.pain.nfms.t0;

import javax.annotation.Nonnull;

// import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
// import net.minecraft.client.renderer.entity.AllayRenderer;
import net.minecraft.resources.ResourceLocation;
// import net.minecraft.client.model.AllayModel;

public class RebroRenderer extends MobRenderer<Rebro, RebroModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("tmod", "textures/entity/rebro.png");

    @SuppressWarnings("null")
    public RebroRenderer(EntityRendererProvider.Context context) {
        super(context, new RebroModel(context.bakeLayer(TModelLayers.RebroModelLocation)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(@Nonnull Rebro entity) {
        return TEXTURE;
    }
}
