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
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Rebro entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        // 1. Получаем предмет из основной руки
        ItemStack stack = entity.getMainHandItem();
        if (stack.isEmpty()) {
            return;
        }

        // 2. Определяем, какая рука главная (в вашем случае - правая)
        HumanoidArm arm = entity.getMainArm();
        
        // 3. Получаем нужную ModelPart из нашей модели
        // В вашей RebroModel поля называются rightArm и leftArm
        ModelPart armPart = (arm == HumanoidArm.RIGHT) ? 
            this.getParentModel().rightArm : 
            this.getParentModel().leftArm;

        // 4. Применяем трансформации руки к PoseStack
        // Это "привяжет" наш предмет к позиции руки.
        // Если предмет летает не там, где нужно — меняйте трансформации (translate, rotate, scale) ЗДЕСЬ.
        poseStack.pushPose();
        
        // Следуем за движениями руки (атака, ходьба и т.д.)
        armPart.translateAndRotate(poseStack);
        
        // --- РУЧНАЯ НАСТРОЙКА ПОЗИЦИИ ПРЕДМЕТА ---
        // Эти значения нужно подбирать под вашу модель. Начните с этих:
        // Сдвигаем предмет к концу руки (обычно рука идёт вниз, поэтому Y отрицательный)
        poseStack.translate(0.0, -0.6, 0.0); 
        
        // Поворачиваем предмет. Для мечей/палок часто нужен поворот на 180 по X.
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
        
        // Масштабируем, если предмет слишком большой/маленький
        poseStack.scale(0.8F, 0.8F, 0.8F);
        // ----------------------------------------

        // 5. Рендерим сам предмет
        Minecraft.getInstance().getItemRenderer().renderStatic(
            stack,
            ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, // Контекст для правильного хвата
            packedLight,
            OverlayTexture.NO_OVERLAY,
            poseStack,
            bufferSource,
            entity.level(),
            entity.getId()
        );

        poseStack.popPose();
    }
}