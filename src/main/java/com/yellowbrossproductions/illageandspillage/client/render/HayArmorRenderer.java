package com.yellowbrossproductions.illageandspillage.client.render;

import com.yellowbrossproductions.illageandspillage.client.model.HayArmorModel;
import com.yellowbrossproductions.illageandspillage.entities.HayArmorEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HayArmorRenderer extends MobRenderer<HayArmorEntity, HayArmorModel<HayArmorEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("illageandspillage", "textures/entity/preserver.png");

    public HayArmorRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HayArmorModel<>(renderManagerIn.bakeLayer(HayArmorModel.LAYER_LOCATION)), 0.0F);
    }

    public ResourceLocation getTextureLocation(HayArmorEntity p_110775_1_) {
        return TEXTURE;
    }
}
