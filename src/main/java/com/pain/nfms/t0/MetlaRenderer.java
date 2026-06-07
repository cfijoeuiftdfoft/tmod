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
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("tmod", "textures/item/metla.png");

    public MetlaRenderer(MetlaModel model) {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.model = model;
    }

    @Override
    public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemDisplayContext transformType, @Nonnull PoseStack poseStack, @Nonnull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        
        switch (transformType) {
        case GUI: // Инвентарь
            poseStack.scale(1.5F, 1.5F, 1.5F);
            poseStack.translate(0F, 1.0F, 0F);
            break;
            
        case GROUND: // На земле
            poseStack.scale(0.8F, 0.8F, 0.8F);
            poseStack.translate(0F, 1.0F, 0F);
            break;
            
        case FIXED: // В рамке/item frame
            poseStack.scale(1.0F, 1.0F, 1.0F);
            poseStack.translate(0F, 1.0F, 0F);
            break;
            
        case THIRD_PERSON_RIGHT_HAND:
        case THIRD_PERSON_LEFT_HAND: // Вид от третьего лица
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            poseStack.mulPose(Axis.XP.rotationDegrees(10));
            poseStack.translate(0.1F, -0.3F, -0.1F);
            poseStack.scale(0.7F, 0.7F, 0.7F);
            break;
            
        case FIRST_PERSON_RIGHT_HAND:
        case FIRST_PERSON_LEFT_HAND: // Вид от первого лица
            poseStack.mulPose(Axis.ZP.rotationDegrees(-15)); // Наклон в руке
            poseStack.translate(0.4F, 0.6F, 0.3F);
            poseStack.scale(0.6F, 0.6F, 0.6F);
            break;
            
        default:
            poseStack.scale(0.8F, 0.8F, 0.8F);
            break;
    }
        
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(buffer, this.model.renderType(TEXTURE), false, stack.hasFoil());
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 0xFFFFFFFF);
        poseStack.popPose();
    }
}