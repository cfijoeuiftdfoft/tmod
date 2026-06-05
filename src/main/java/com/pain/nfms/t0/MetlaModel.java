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

    public MetlaModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rroot = mesh.getRoot();
        
        PartDefinition root = rroot.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition stick = root.addOrReplaceChild("stick", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -32.0F, -3.0F, 6.0F, 64.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        stick.addOrReplaceChild("v0", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -10.0F, -3.0F, 20.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, 0.0F));
        
        return LayerDefinition.create(mesh, 16, 16);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
