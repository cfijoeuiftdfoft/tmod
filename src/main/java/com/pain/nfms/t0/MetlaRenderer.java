package com.pain.nfms.t0;
import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MetlaRenderer extends BlockEntityWithoutLevelRenderer {
    private final MetlaModel model;
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("tmod", "textures/item/Metla.png");

    public MetlaRenderer(MetlaModel model) {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.model = model;
    }

    @Override
    public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemDisplayContext transformType, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        
        // Масштабирование и позиционирование в зависимости от контекста отображения
        switch (transformType) {
            case GUI:
                poseStack.scale(1.2F, 1.2F, 1.2F);
                poseStack.translate(0F, -0.1F, 0F);
                break;
            case THIRD_PERSON_RIGHT_HAND:
            case THIRD_PERSON_LEFT_HAND:
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
                poseStack.translate(0.1F, -0.2F, -0.1F);
                poseStack.scale(0.8F, 0.8F, 0.8F);
                break;
            case FIRST_PERSON_RIGHT_HAND:
            case FIRST_PERSON_LEFT_HAND:
                poseStack.translate(0.5F, 0.3F, 0.5F);
                poseStack.scale(0.7F, 0.7F, 0.7F);
                break;
            default:
                break;
        }
        
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(buffer, this.model.renderType(TEXTURE), false, stack.hasFoil());
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 0xFFFFFFFF);
        
        poseStack.popPose();
    }

    
}