package com.pain.nfms.t0;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
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
        PartDefinition root = mesh.getRoot();
        
        // Главная часть предмета
        PartDefinition main = root.addOrReplaceChild("main", 
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -4.0F, -2.0F, 8.0F, 8.0F, 4.0F),
            PartPose.offset(0.0F, 16.0F, 0.0F)
        );
        
        // Ручка/держатель
        main.addOrReplaceChild("handle",
            CubeListBuilder.create()
                .texOffs(0, 12)
                .addBox(-1.0F, 4.0F, -1.0F, 2.0F, 6.0F, 2.0F),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );
        
        // Деталь сверху
        main.addOrReplaceChild("top",
            CubeListBuilder.create()
                .texOffs(0, 20)
                .addBox(-3.0F, -6.0F, -2.5F, 6.0F, 3.0F, 5.0F),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );
        
        // Дополнительные элементы (шипы, украшения и т.д.)
        main.addOrReplaceChild("spike_left",
            CubeListBuilder.create()
                .texOffs(22, 0)
                .addBox(-6.0F, -2.0F, 0.0F, 2.0F, 4.0F, 2.0F),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );
        
        main.addOrReplaceChild("spike_right",
            CubeListBuilder.create()
                .texOffs(22, 0)
                .addBox(4.0F, -2.0F, 0.0F, 2.0F, 4.0F, 2.0F),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );
        
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack poseStack, @Nonnull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }
}
