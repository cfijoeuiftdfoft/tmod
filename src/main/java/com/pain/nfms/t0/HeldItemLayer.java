package com.pain.nfms.t0;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.model.geom.ModelPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeldItemLayer extends RenderLayer<Rebro, RebroModel> {

	public HeldItemLayer(RenderLayerParent<Rebro, RebroModel> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Rebro entity,
			float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw,
			float headPitch) {
		ItemStack stack = entity.getMainHandItem();
		if (stack.isEmpty()) {
			return;
		}

		// HumanoidArm arm = entity.getMainArm();

		ModelPart armPart = this.getParentModel().rightArm;

		poseStack.pushPose();

		// Следуем за движениями руки (атака, ходьба и т.д.)
		armPart.translateAndRotate(poseStack);

		poseStack.mulPose(Axis.YN.rotationDegrees(90.0F));

		poseStack.translate(0.4, 0.6, 0.2);

		// poseStack.mulPose(Axis.XN.rotationDegrees(180.0F));

		// poseStack.scale(0.8F, 0.8F, 0.8F);

		Minecraft.getInstance().getItemRenderer().renderStatic(
				stack,
				ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,
				packedLight,
				OverlayTexture.NO_OVERLAY,
				poseStack,
				bufferSource,
				entity.level(),
				entity.getId());

		poseStack.popPose();
	}
}