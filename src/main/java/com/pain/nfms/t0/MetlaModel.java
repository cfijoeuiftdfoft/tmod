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
    private final ModelPart v0;
    private final ModelPart v1;
    private final ModelPart v2;
    private final ModelPart v3;
    private final ModelPart v4;

    public MetlaModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root.getChild("root");
        this.stick = this.root.getChild("stick");
        this.v0 = this.stick.getChild("v0");
        this.v1 = this.stick.getChild("v1");
        this.v2 = this.stick.getChild("v2");
        this.v3 = this.stick.getChild("v3");
        this.v4 = this.stick.getChild("v4");

        // this.v2.visible = false;
        // this.v3.visible = false;
        this.v1.visible = false;
    }

    @SuppressWarnings("null")
    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rroot = mesh.getRoot();
        
        PartDefinition root = rroot.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition stick = root.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 26).addBox(-48.0F, -2.5F, -2.5F, 96.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        // float r180 = (float)Math.toRadians(180.0F);
        stick.addOrReplaceChild("v0", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-10.0F, -3.5F, -3.5F, 20.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(48.0F + 10.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        stick.addOrReplaceChild("v1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8.5F, -4.0F, -4.0F, 17.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(48.0F + 8.5F + 3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        // float r45 = (float)Math.toRadians(45);
        // float rn45 = (float)Math.toRadians(-45);
        stick.addOrReplaceChild("v2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -7.5F, -7.5F, 14.0F, 15.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(48.0F + 20.0F + 7.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        stick.addOrReplaceChild("v3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.5F, -5.5F, 6.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(48.0F + 20.0F - 3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        stick.addOrReplaceChild("v4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-21.0F, -9.5F, -9.5F, 42.0F, 19.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(48.0F + 20.0F + 14.0F + 21.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        
        int xy = 128;
        return LayerDefinition.create(mesh, xy, xy);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
