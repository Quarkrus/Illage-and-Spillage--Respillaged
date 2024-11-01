package com.yellowbrossproductions.illageandspillage.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegisterer {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "illageandspillage");

    public static final RegistryObject<CreativeModeTab> ILLAGE_AND_SPILLAGE_TAB = TABS.register("illageandspillage", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.illageandspillage.creative_tab"))
            .icon(ItemRegisterer.LOGO.get()::getDefaultInstance)
            .displayItems((displayParameters, output) -> {
                output.accept(ItemRegisterer.IGNITER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.TWITTOLLAGER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.PRESERVER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.ENGINEER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.ABSORBER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.CROCOFANG_SPAWN_EGG.get());
                output.accept(ItemRegisterer.SPIRITCALLER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.FREAKAGER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.RAGNO_SPAWN_EGG.get());
                output.accept(ItemRegisterer.FUNNYBONE_SPAWN_EGG.get());
                output.accept(ItemRegisterer.EYESORE_SPAWN_EGG.get());
                output.accept(ItemRegisterer.MAGISPELLER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.ILLASHOOTER_SPAWN_EGG.get());
                output.accept(ItemRegisterer.SPELLBOUND_BOOK.get());
                output.accept(ItemRegisterer.BAG_OF_HORRORS.get());
                output.accept(ItemRegisterer.TOTEM_OF_BANISHMENT.get());
                output.accept(ItemRegisterer.SPIRITCALLER_DISC.get());
                output.accept(ItemRegisterer.FREAKAGER_DISC.get());
                output.accept(ItemRegisterer.MAGISPELLER_DISC.get());
            })
            .build()
    );
}