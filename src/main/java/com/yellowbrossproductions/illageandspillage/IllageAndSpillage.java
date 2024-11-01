package com.yellowbrossproductions.illageandspillage;

import com.mojang.logging.LogUtils;
import com.yellowbrossproductions.illageandspillage.capability.IWebbed;
import com.yellowbrossproductions.illageandspillage.config.Config;
import com.yellowbrossproductions.illageandspillage.init.ModEntityTypes;
import com.yellowbrossproductions.illageandspillage.packet.PacketHandler;
import com.yellowbrossproductions.illageandspillage.particle.ParticleRegisterer;
import com.yellowbrossproductions.illageandspillage.util.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

import java.util.Locale;

@Mod(IllageAndSpillage.MOD_ID)
public class IllageAndSpillage {
    public static final String MOD_ID = "illageandspillage";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ServerProxy PROXY;

    public IllageAndSpillage() {
        PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
//        BlockRegisterer.blockInit();
        ItemRegisterer.itemInit();
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        EffectRegisterer.EFFECTS.register(modEventBus);
        IllageAndSpillageSoundEvents.SOUND_EVENTS.register(modEventBus);
        PotionRegisterer.POTIONS.register(modEventBus);
        ParticleRegisterer.PARTICLE_TYPES.register(modEventBus);
        CreativeTabRegisterer.TABS.register(modEventBus);
        Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("illageandspillage-client.toml").toString());
        Config.loadConfig(Config.common_config, FMLPaths.CONFIGDIR.get().resolve("illageandspillage-common.toml").toString());
        PROXY.init(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerCapabilities);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        SpawnPlacements.register(ModEntityTypes.Igniter.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Magispeller.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Faker.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Dispenser.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Illashooter.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Crashager.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.BossRandomizer.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Twittollager.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Spiritcaller.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.MobSpirit.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.IllagerSoul.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Imp.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.SpiritHand.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Crocofang.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
//        SpawnPlacements.register(ModEntityTypes.Devastator.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Absorber.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Freakager.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Ragno.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
        SpawnPlacements.register(ModEntityTypes.OldMagispeller.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(ModEntityTypes.Kaboomer.get(), Type.ON_GROUND, Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        PacketHandler.init();
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IWebbed.class);
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation("illageandspillage", name.toLowerCase(Locale.ROOT));
    }

    @EventBusSubscriber(bus = Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onAttribute(EntityAttributeCreationEvent event) {
            ModEntityTypes.onAttribute(event);
        }
    }
}