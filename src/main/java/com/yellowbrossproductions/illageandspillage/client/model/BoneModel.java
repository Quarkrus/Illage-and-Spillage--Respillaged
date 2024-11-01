package com.yellowbrossproductions.illageandspillage.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BoneModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("illageandspillage", "bone"), "main");
    private final ModelPart bone;

    public BoneModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(1.5F, -0.5F, 3.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(-6.5F, -0.5F, -5.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(14, 0).addBox(2.5F, -0.5F, 7.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 0).addBox(-0.5F, -0.5F, 2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(-1.5F, -0.5F, 1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 5).addBox(-2.5F, -0.5F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 2).addBox(-4.5F, -0.5F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 2).addBox(-1.5F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -1.0F));

        bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 9).addBox(-1.0F, -0.5F, 0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, -3.0F, 0.0F, -1.5708F, 0.0F));

        bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 7).addBox(-0.5F, -0.5F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -5.0F, 0.0F, 3.1416F, 0.0F));

        bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 2).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 5.0F, 0.0F, 1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.bone.yRot = ageInTicks * 45.0F * 0.017453292F;
		this.bone.xScale = 0.75F;
		this.bone.yScale = 0.75F;
		this.bone.zScale = 0.75F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}