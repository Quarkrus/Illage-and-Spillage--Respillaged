package com.yellowbrossproductions.illageandspillage.events;

import com.yellowbrossproductions.illageandspillage.gui.overlay.JumpscareOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "illageandspillage", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventBusSubscriber {
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> JumpscareOverlay.JUMPSCARE_OVERLAY.clientTick());
    }
}