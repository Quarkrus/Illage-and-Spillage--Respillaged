package com.yellowbrossproductions.illageandspillage.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class WebbedProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<IWebbed> WEBBED_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    private IWebbed instance = new WebbedCapability();
    private final LazyOptional<IWebbed> optional = LazyOptional.of(() -> instance);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == WEBBED_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("webbed", instance.isWebbed());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.setWebbed(nbt.getBoolean("webbed"));
    }
}