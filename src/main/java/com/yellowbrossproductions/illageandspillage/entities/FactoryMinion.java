package com.yellowbrossproductions.illageandspillage.entities;

import net.minecraft.world.entity.LivingEntity;

public interface FactoryMinion {
    FactoryEntity getOwner();

    void setOwner(FactoryEntity entity);
}