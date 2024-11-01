package com.yellowbrossproductions.illageandspillage.events;

import com.yellowbrossproductions.illageandspillage.particle.ParticleRegisterer;
import com.yellowbrossproductions.illageandspillage.particle.custom.BloodParticles;
import com.yellowbrossproductions.illageandspillage.particle.custom.MutationDripParticles;
import com.yellowbrossproductions.illageandspillage.particle.custom.MutationParticles;
import com.yellowbrossproductions.illageandspillage.particle.custom.MutationParticles2;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "illageandspillage", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusSubscriber {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleRegisterer.MUTATION_PARTICLES.get(), MutationParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegisterer.MUTATION_PARTICLES2.get(), MutationParticles2.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegisterer.MUTATION_DRIP_PARTICLES.get(), MutationDripParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegisterer.BLOOD_PARTICLES.get(), BloodParticles.Provider::new);
    }
}