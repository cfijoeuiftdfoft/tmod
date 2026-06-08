package com.pain.nfms.t0;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
// import com.mojang.math.Axis;
// import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class MetlaModel extends Model {
    private final ModelPart root;
    private final ModelPart stick;
    private final ModelPart s0;
    private final ModelPart r0;
    private final ModelPart r1;
    private final ModelPart v0;
    private final ModelPart v1;
    private final ModelPart v2;
    private final ModelPart v3;
    private final ModelPart debugv;
    private final ModelPart v4;
    private final ModelPart v5;
    private final ModelPart v6;
    private final ModelPart v7;
    private final ModelPart v8;
    private final ModelPart v9;
    private final ModelPart v10;
    private final ModelPart v11;
    private final ModelPart v12;
    private final ModelPart v13;
    private final ModelPart v14;
    private final ModelPart v15;

    public MetlaModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root.getChild("root");
        this.stick = this.root.getChild("stick");
        this.s0 = this.stick.getChild("s0");

        this.r0 = this.s0.getChild("r0");
        this.r1 = this.s0.getChild("r1");

        this.debugv = this.s0.getChild("debugv");

        this.v0 = this.s0.getChild("v0");
        this.v1 = this.s0.getChild("v1");
        this.v2 = this.s0.getChild("v2");
        this.v3 = this.s0.getChild("v3");

        this.v4 = this.s0.getChild("v4");
        this.v5 = this.s0.getChild("v5");
        this.v6 = this.s0.getChild("v6");
        this.v7 = this.s0.getChild("v7");

        this.v8 = this.s0.getChild("v8");
        this.v9 = this.s0.getChild("v9");
        this.v10 = this.s0.getChild("v10");
        this.v11 = this.s0.getChild("v11");

        this.v12 = this.s0.getChild("v12");
        this.v13 = this.s0.getChild("v13");
        this.v14 = this.s0.getChild("v14");
        this.v15 = this.s0.getChild("v15");

        // this.v1.visible = false;
        // this.v2.visible = false;
        // this.v3.visible = false;
        this.debugv.visible = false;
    }

    @SuppressWarnings("null")
    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rroot = mesh.getRoot();
        
        PartDefinition root = rroot.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition stick = root.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 0).addBox(-48.0F, -2.5F, -2.5F, 96.0F, 5.0F, 5.0F), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition s0 = stick.addOrReplaceChild("s0", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -4.0F, -4.0F, 20.0F, 8.0F, 8.0F), PartPose.offset(48.0F + 10.0F, 0.0F, 0.0F));
        s0.addOrReplaceChild("r0", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.5F, -4.5F, 2.0F, 9.0F, 9.0F), PartPose.offset(3.0F + 1.0F, 0.0F, 0.0F));
        s0.addOrReplaceChild("r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.5F, -4.5F, 2.0F, 9.0F, 9.0F), PartPose.offset(-3.0F - 1.0F, 0.0F, 0.0F));

        float rzl = (float)Math.toRadians(2);
        s0.addOrReplaceChild("debugv", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-30.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offset(9.0F, 1.0F, 1.0F));
        s0.addOrReplaceChild("v0", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 1.0F, 1.0F, 0.0F, -rzl, rzl));
        s0.addOrReplaceChild("v1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 1.0F, -1.0F, 0.0F, rzl, rzl));
        s0.addOrReplaceChild("v2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -1.0F, 1.0F, 0.0F, -rzl, -rzl));
        s0.addOrReplaceChild("v3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -1.0F, -1.0F, 0.0F, rzl, -rzl));

        float rsl = (float)Math.toRadians(5);
        s0.addOrReplaceChild("v4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 3.0F, 3.0F, 0.0F, -rsl, rsl));
        s0.addOrReplaceChild("v5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 3.0F, -3.0F, 0.0F, rsl, rsl));
        s0.addOrReplaceChild("v6", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -3.0F, 3.0F, 0.0F, -rsl, -rsl));
        s0.addOrReplaceChild("v7", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -3.0F, -3.0F, 0.0F, rsl, -rsl));

        s0.addOrReplaceChild("v8", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 1.0F, 3.0F, 0.0F, -rsl, rzl));
        s0.addOrReplaceChild("v9", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 1.0F, -3.0F, 0.0F, rsl, rzl));
        s0.addOrReplaceChild("v10", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -1.0F, 3.0F, 0.0F, -rsl, -rzl));
        s0.addOrReplaceChild("v11", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -1.0F, -3.0F, 0.0F, rsl, -rzl));

        s0.addOrReplaceChild("v12", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 3.0F, 1.0F, 0.0F, -rzl, rsl));
        s0.addOrReplaceChild("v13", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, 3.0F, -1.0F, 0.0F, rzl, rsl));
        s0.addOrReplaceChild("v14", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -3.0F, 1.0F, 0.0F, -rzl, -rsl));
        s0.addOrReplaceChild("v15", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 30.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(10.0F, -3.0F, -1.0F, 0.0F, rzl, -rsl));
        
        int xy = 128;
        return LayerDefinition.create(mesh, xy, xy);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
