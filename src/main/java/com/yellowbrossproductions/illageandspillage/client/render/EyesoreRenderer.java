package com.yellowbrossproductions.illageandspillage.client.render;

import com.yellowbrossproductions.illageandspillage.client.model.EyesoreModel;
import com.yellowbrossproductions.illageandspillage.entities.EyesoreEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EyesoreRenderer extends MobRenderer<EyesoreEntity, EyesoreModel<EyesoreEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("illageandspillage", "textures/entity/freakager/eyesore.png");

    public EyesoreRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new EyesoreModel<>(renderManagerIn.bakeLayer(EyesoreModel.LAYER_LOCATION)), 0.5F);
    }

    public ResourceLocation getTextureLocation(EyesoreEntity p_110775_1_) {
        return TEXTURE;
    }
}
