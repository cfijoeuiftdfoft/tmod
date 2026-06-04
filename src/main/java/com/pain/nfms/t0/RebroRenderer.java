package com.pain.nfms.t0;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
// import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
// import net.minecraft.client.renderer.entity.MobRenderer;
// import net.minecraft.client.renderer.entity.AllayRenderer;
import net.minecraft.resources.ResourceLocation;
// import net.minecraft.client.model.AllayModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
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

    @Override
    public void render(@Nonnull Rebro entity, float entityYaw, float partialTick, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight) {
        this.model.setPartialTick(partialTick);
        
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    protected boolean shouldShowName(@Nonnull Rebro entity) {
        return entity.hasCustomName();
    }
}
