package com.pain.nfms.t0;

import javax.annotation.Nonnull;

// import net.minecraft.client.model.AnimationUtils;
// import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
// import net.minecraft.util.Mth;
// import net.minecraft.world.InteractionHand;
// import net.minecraft.world.entity.HumanoidArm;
// import net.minecraft.world.entity.LivingEntity; doHurtTarget
// import net.minecraft.world.entity.animal.allay.Allay;
// import net.minecraft.world.entity.player.Player;
// import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RebroModel extends HierarchicalModel<Rebro> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart right_wing;
    private final ModelPart left_wing;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    // private final ModelPart right_hip;
    // private final ModelPart left_hip;
    // private final ModelPart right_shin;
    // private final ModelPart left_shin;

    private float partialTick;
    public float swimAmount;
    // public float attackTime;

    public RebroModel(ModelPart root) {
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.body = this.root.getChild("body");
        this.rightArm = this.body.getChild("rightArm");
        this.leftArm = this.body.getChild("leftArm");
        this.right_wing = this.body.getChild("right_wing");
        this.right_wing.visible = false;
        this.left_wing = this.body.getChild("left_wing");
        this.left_wing.visible = false;
        this.rightLeg = this.body.getChild("rightLeg");
        this.leftLeg = this.body.getChild("leftLeg");
        // this.left_hip = this.body.getChild("left_hip");
        // this.right_hip = this.body.getChild("right_hip");
        // this.left_shin = this.left_hip.getChild("left_shin");
        // this.right_shin = this.right_hip.getChild("right_shin");
    }

    public ModelPart root() {
      return this.root;
   }
   /*
   i ld
   128 128 64 64
   256 256 32 32
   512 512 16 16
   */

   @SuppressWarnings("null")
    public static LayerDefinition createBodyLayer() {
        float yOffset = 0.0F;
        // float rn30 = (float)Math.toRadians(-30);
        float rn15 = (float)Math.toRadians(-15);
        float r15 = (float)Math.toRadians(15);
        // float r30 = (float)Math.toRadians(30);

        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition rroot = mesh.getRoot();

        /*
        8 - 128 127
        4 - 64 63
        12 - 192 191
        */

        PartDefinition root = rroot.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F)); // head
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F + yOffset, 0.0F)); // body

        // 64 64 64 64
        // 192

        body.addOrReplaceChild("rightArm",  CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-5.0F, 2.0F + yOffset, 0.0F)); // arms r
        body.addOrReplaceChild("leftArm",   CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(5.0F, 2.0F + yOffset, 0.0F));

        body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 1.0F, 0.0F, 0.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)),     PartPose.offsetAndRotation(0.0F, 0.0F + yOffset, 0.0F, r15, 0.0F, 0.0F)); // wings r
        body.addOrReplaceChild("left_wing",  CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 1.0F, 0.0F, 0.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)),     PartPose.offsetAndRotation(0.0F, 0.0F + yOffset, 0.0F, rn15, 0.0F, 0.0F));
        // PartDefinition right_hip = body.addOrReplaceChild("right_hip", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
        // PartDefinition left_hip = body.addOrReplaceChild("left_hip", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        // right_hip.addOrReplaceChild("right_shin", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 12.0F, 0));
        // left_hip.addOrReplaceChild("left_shin", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 12.0F, 0));

        body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(16, 16)        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F + yOffset, 0.0F)); // legs r
        body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F + yOffset, 0.0F));
        int xy = 64;
        return LayerDefinition.create(mesh, xy, xy);
    }

    public void setPartialTick(float partialTick) {
        this.partialTick = partialTick;
    }

    public void setupAnim(@Nonnull Rebro entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
        // СБРОС - возвращаем руки в базовое положение
        this.rightArm.x = -5.0F;  // исходная позиция из модели
        this.rightArm.y = 2.0F;
        this.rightArm.z = 0.0F;
        this.leftArm.x = 5.0F;
        this.leftArm.y = 2.0F;
        this.leftArm.z = 0.0F;
        
        // Сброс ротаций
        this.rightArm.xRot = 0.0F;
        this.rightArm.yRot = 0.0F;
        this.rightArm.zRot = 0.0F;
        this.leftArm.xRot = 0.0F;
        this.leftArm.yRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        
        this.body.yRot = 0.0F;
        
        // Базовая анимация ходьбы (если нужна)
        float legSwing = Mth.cos(limbSwing) * limbSwingAmount * 0.8F;
        this.rightLeg.xRot = legSwing;
        this.leftLeg.xRot = -legSwing;
        
        float armSwing = Mth.cos(limbSwing + (float)Math.PI) * limbSwingAmount;
        this.rightArm.xRot = armSwing * 0.6F;
        this.leftArm.xRot = -armSwing * 0.6F;
        
        // Анимация атаки (поверх базовой)
        this.attackTime = entity.getAttackAnim(this.partialTick);
        if (this.attackTime > 0.01f) {
            System.out.println(this.attackTime);
            this.setupAttackAnimation(entity, ageInTicks);
        }
    }

    protected ModelPart getArm(HumanoidArm side) {
        return side == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    private HumanoidArm getAttackArm(Rebro entity) {
        HumanoidArm humanoidarm = entity.getMainArm();
        return entity.swingingArm == InteractionHand.MAIN_HAND ? humanoidarm : humanoidarm.getOpposite();
    }

    protected void setupAttackAnimation(Rebro livingEntity, float ageInTicks) {
        // System.out.println("preattack");
        if (!(this.attackTime <= 0.0F)) {
            System.out.println("attack");
            HumanoidArm humanoidarm = this.getAttackArm(livingEntity);
            ModelPart modelpart = this.getArm(humanoidarm);
            float f = this.attackTime;
            this.body.yRot = Mth.sin(Mth.sqrt(f) * ((float)Math.PI * 2F)) * 0.2F;
            if (humanoidarm == HumanoidArm.LEFT) {
                ModelPart var10000 = this.body;
                var10000.yRot *= -1.0F;
            }

            this.rightArm.z = Mth.sin(this.body.yRot) * 5.0F;
            this.rightArm.x = -Mth.cos(this.body.yRot) * 5.0F;
            this.leftArm.z = -Mth.sin(this.body.yRot) * 5.0F;
            this.leftArm.x = Mth.cos(this.body.yRot) * 5.0F;
            this.rightArm.yRot += this.body.yRot;
            this.leftArm.yRot += this.body.yRot;
            this.leftArm.xRot += this.body.yRot;
            f = 1.0F - this.attackTime;
            f *= f;
            f *= f;
            f = 1.0F - f;
            float f1 = Mth.sin(f * (float)Math.PI);
            float f2 = Mth.sin(this.attackTime * (float)Math.PI) * -(this.head.xRot - 0.7F) * 0.75F;
            modelpart.xRot -= f1 * 1.2F + f2;
            modelpart.yRot += this.body.yRot * 2.0F;
            modelpart.zRot += Mth.sin(this.attackTime * (float)Math.PI) * -0.4F;
        }
    }
}
