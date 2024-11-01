package com.yellowbrossproductions.illageandspillage.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class DamageTypesRegisterer {
    public static final ResourceKey<DamageType> MUTATION = register("mutation");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("illageandspillage", name));
    }
}