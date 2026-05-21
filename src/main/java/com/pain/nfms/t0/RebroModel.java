package com.pain.nfms.t0;

import javax.annotation.Nonnull;

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
// import net.minecraft.world.entity.LivingEntity;
// import net.minecraft.world.entity.animal.allay.Allay;

public class RebroModel extends HierarchicalModel<Rebro> {
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart right_wing;
    private final ModelPart left_wing;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    // private final ModelPart right_hip;
    // private final ModelPart left_hip;
    // private final ModelPart right_shin;
    // private final ModelPart left_shin;

    public float swimAmount;

    public RebroModel(ModelPart root) {
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
        this.body = this.root.getChild("body");
        this.right_arm = this.body.getChild("right_arm");
        this.left_arm = this.body.getChild("left_arm");
        this.right_wing = this.body.getChild("right_wing");
        this.right_wing.visible = false;
        this.left_wing = this.body.getChild("left_wing");
        this.left_wing.visible = false;
        this.right_leg = this.body.getChild("right_leg");
        this.left_leg = this.body.getChild("left_leg");
        // this.left_hip = this.body.getChild("left_hip");
        // this.right_hip = this.body.getChild("right_hip");
        // this.left_shin = this.left_hip.getChild("left_shin");
        // this.right_shin = this.right_hip.getChild("right_shin");
    }

    public ModelPart root() {
      return this.root;
   }

   @SuppressWarnings("null")
    public static LayerDefinition createBodyLayer() {
        float yOffset = 0.0F;
        // float rn30 = (float)Math.toRadians(-30);
        float rn15 = (float)Math.toRadians(-15);
        float r15 = (float)Math.toRadians(15);
        // float r30 = (float)Math.toRadians(30);

        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition rroot = mesh.getRoot();

        PartDefinition root = rroot.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.99F + yOffset, 0.0F)); // head
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F + yOffset, 0.0F)); // body

        body.addOrReplaceChild("right_arm",  CubeListBuilder.create().texOffs(0, 0)         .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(-5.0F, 2.0F + yOffset, 0.0F)); // arms r
        body.addOrReplaceChild("left_arm",   CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offset(5.0F, 2.0F + yOffset, 0.0F));

        body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 0)         .addBox(0.0F, 1.0F, 0.0F, 0.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)),     PartPose.offsetAndRotation(0.0F, 0.0F + yOffset, 0.0F, r15, 0.0F, 0.0F)); // wings r
        body.addOrReplaceChild("left_wing",  CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 1.0F, 0.0F, 0.0F, 32.0F, 32.0F, new CubeDeformation(0.0F)),     PartPose.offsetAndRotation(0.0F, 0.0F + yOffset, 0.0F, rn15, 0.0F, 0.0F));
        // PartDefinition right_hip = body.addOrReplaceChild("right_hip", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
        // PartDefinition left_hip = body.addOrReplaceChild("left_hip", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

        // right_hip.addOrReplaceChild("right_shin", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 12.0F, 0));
        // left_hip.addOrReplaceChild("left_shin", CubeListBuilder.create().texOffs(16, 14).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 12.0F, 0));

        body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0)        .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F + yOffset, 0.0F)); // legs r
        body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F + yOffset, 0.0F));
        return LayerDefinition.create(mesh, 128, 128);
    }

    public void setupAnim(@Nonnull Rebro entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.zRot = (float) Math.sin(ageInTicks * 0.2f) * 0.1f;

        
    }
}
