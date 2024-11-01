package com.yellowbrossproductions.illageandspillage.events;

import com.yellowbrossproductions.illageandspillage.config.IllageAndSpillageConfig;
import com.yellowbrossproductions.illageandspillage.entities.CameraShakeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public enum ClientEventHandler {
    INSTANCE;

    @SubscribeEvent
    public void onSetupCamera(ViewportEvent.ComputeCameraAngles event) {
        Player player = Minecraft.getInstance().player;
        float delta = Minecraft.getInstance().getFrameTime();
        float ticksExistedDelta = (float) player.tickCount + delta;
        if (IllageAndSpillageConfig.cameraShakesAllowed.get()) {
            float shakeAmplitude = 0.0F;

            for (CameraShakeEntity cameraShake : player.level().getEntitiesOfClass(CameraShakeEntity.class, player.getBoundingBox().inflate(100.0))) {
                if (cameraShake.distanceTo(player) < cameraShake.getRadius()) {
                    shakeAmplitude += cameraShake.getShakeAmount(player, delta);
                }
            }

            if (shakeAmplitude > 1.0F) {
                shakeAmplitude = 1.0F;
            }

            event.setPitch((float) ((double) event.getPitch() + (double) shakeAmplitude * Math.cos((ticksExistedDelta * 3.0F + 2.0F)) * 25.0));
            event.setYaw((float) ((double) event.getYaw() + (double) shakeAmplitude * Math.cos((ticksExistedDelta * 5.0F + 1.0F)) * 25.0));
            event.setRoll((float) ((double) event.getRoll() + (double) shakeAmplitude * Math.cos((ticksExistedDelta * 4.0F)) * 25.0));
        }

    }
}
