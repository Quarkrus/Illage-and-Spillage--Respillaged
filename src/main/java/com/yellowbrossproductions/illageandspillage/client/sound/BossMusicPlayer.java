package com.yellowbrossproductions.illageandspillage.client.sound;

import com.yellowbrossproductions.illageandspillage.config.IllageAndSpillageConfig;
import com.yellowbrossproductions.illageandspillage.entities.FreakagerEntity;
import com.yellowbrossproductions.illageandspillage.entities.MagispellerEntity;
import com.yellowbrossproductions.illageandspillage.entities.RagnoEntity;
import com.yellowbrossproductions.illageandspillage.entities.SpiritcallerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;

public class BossMusicPlayer {
    public static BossMusicSound bossMusic;

    public static void playBossMusic(Raider entity) {
        if (IllageAndSpillageConfig.boss_music.get()) {
            if (entity instanceof SpiritcallerEntity) {
                doSpiritcallerMusic((SpiritcallerEntity) entity);
            } else if (entity instanceof FreakagerEntity) {
                doFreakagerMusic((FreakagerEntity) entity);
            } else if (entity instanceof RagnoEntity) {
                doRagnoMusic((RagnoEntity) entity);
            } else if (entity instanceof MagispellerEntity) {
                doMagispellerMusic((MagispellerEntity) entity);
            }
        }
    }

    public static void doRagnoMusic(RagnoEntity entity) {
        SoundEvent soundEvent = entity.getTransMusic();
        if (soundEvent != null && entity.isAlive()) {
            Player player = Minecraft.getInstance().player;
            if (bossMusic != null) {
                float f2 = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.RECORDS);
                if (f2 <= 0.0F) {
                    bossMusic = null;
                } else if (bossMusic.getBoss() == entity && !entity.canPlayerHearMusic(player)) {
                    bossMusic.setBoss(null);
                } else if (bossMusic.getBoss() == null && bossMusic.getSoundEvent() == soundEvent) {
                    bossMusic.setBoss(entity);
                }
            } else if (entity.canPlayerHearMusic(player)) {
                bossMusic = new BossMusicSound(soundEvent, entity);
            }

            if (bossMusic != null && !Minecraft.getInstance().getSoundManager().isActive(bossMusic)) {
                Minecraft.getInstance().getSoundManager().play(bossMusic);
            }
        }
    }

    private static void doSpiritcallerMusic(SpiritcallerEntity entity) {
        SoundEvent soundEvent = entity.getBossMusic();
        if (soundEvent != null && entity.isAlive()) {
            Player player = Minecraft.getInstance().player;
            if (bossMusic != null) {
                float f2 = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.RECORDS);
                if (f2 <= 0.0F) {
                    bossMusic = null;
                } else if (bossMusic.getBoss() == entity && !entity.canPlayerHearMusic(player)) {
                    bossMusic.setBoss(null);
                } else if (bossMusic.getBoss() == null && bossMusic.getSoundEvent() == soundEvent) {
                    bossMusic.setBoss(entity);
                }
            } else if (entity.canPlayerHearMusic(player)) {
                bossMusic = new BossMusicSound(soundEvent, entity);
            }

            if (bossMusic != null && !Minecraft.getInstance().getSoundManager().isActive(bossMusic)) {
                Minecraft.getInstance().getSoundManager().play(bossMusic);
            }
        }
    }

    private static void doFreakagerMusic(FreakagerEntity entity) {
        SoundEvent soundEvent = entity.getBossMusic();
        if (soundEvent != null && entity.isAlive()) {
            Player player = Minecraft.getInstance().player;
            if (bossMusic != null) {
                float f2 = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.RECORDS);
                if (f2 <= 0.0F) {
                    bossMusic = null;
                } else if (bossMusic.getBoss() == entity && !entity.canPlayerHearMusic(player)) {
                    bossMusic.setBoss(null);
                } else if (bossMusic.getBoss() == null && bossMusic.getSoundEvent() == soundEvent) {
                    bossMusic.setBoss(entity);
                }
            } else if (entity.canPlayerHearMusic(player)) {
                bossMusic = new BossMusicSound(soundEvent, entity);
            }

            if (bossMusic != null && !Minecraft.getInstance().getSoundManager().isActive(bossMusic)) {
                Minecraft.getInstance().getSoundManager().play(bossMusic);
            }
        }
    }

    private static void doMagispellerMusic(MagispellerEntity entity) {
        SoundEvent soundEvent = entity.getBossMusic();
        if (soundEvent != null && entity.isAlive()) {
            Player player = Minecraft.getInstance().player;
            if (bossMusic != null) {
                float f2 = Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.RECORDS);
                if (f2 <= 0.0F) {
                    bossMusic = null;
                } else if (bossMusic.getBoss() == entity && !entity.canPlayerHearMusic(player)) {
                    bossMusic.setBoss(null);
                } else if (bossMusic.getBoss() == null && bossMusic.getSoundEvent() == soundEvent) {
                    bossMusic.setBoss(entity);
                }
            } else if (entity.canPlayerHearMusic(player)) {
                bossMusic = new BossMusicSound(soundEvent, entity);
            }

            if (bossMusic != null && !Minecraft.getInstance().getSoundManager().isActive(bossMusic)) {
                Minecraft.getInstance().getSoundManager().play(bossMusic);
            }
        }
    }

    public static void stopBossMusic(Raider entity) {
        if (IllageAndSpillageConfig.boss_music.get()) {
            if (bossMusic != null && bossMusic.getBoss() == entity) {
                bossMusic.setBoss(null);
            }

        }
    }
}
