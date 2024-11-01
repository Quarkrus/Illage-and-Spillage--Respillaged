package com.yellowbrossproductions.illageandspillage.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleRegisterer {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, "illageandspillage");

    public static final RegistryObject<SimpleParticleType> MUTATION_PARTICLES = PARTICLE_TYPES.register("mutation_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MUTATION_PARTICLES2 = PARTICLE_TYPES.register("mutation_particles2", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> MUTATION_DRIP_PARTICLES = PARTICLE_TYPES.register("mutation_drip_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> BLOOD_PARTICLES = PARTICLE_TYPES.register("blood_particles", () -> new SimpleParticleType(true));
}