package com.yellowbrossproductions.illageandspillage.events;

import com.yellowbrossproductions.illageandspillage.config.IllageAndSpillageConfig;
import com.yellowbrossproductions.illageandspillage.entities.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "illageandspillage", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NightmareEvents {
//    private static float previousYaw;
//    private static float previousPitch;

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        LivingEntity victim = event.getEntity();
        Entity attacker = event.getSource().getEntity();
        if (IllageAndSpillageConfig.nightmare_mode.get() && (attacker instanceof MagispellerEntity || attacker instanceof FakeMagispellerEntity || attacker instanceof IllashooterEntity || attacker instanceof CrashagerEntity || attacker instanceof KaboomerEntity)) {
            event.setAmount((float) (event.getAmount() * IllageAndSpillageConfig.magi_damage_multiplier.get()));
        }
        if (IllageAndSpillageConfig.nightmare_mode.get() && (attacker instanceof FreakagerEntity || attacker instanceof EyesoreEntity || attacker instanceof FunnyboneEntity)) {
            event.setAmount((float) (event.getAmount() * IllageAndSpillageConfig.freaky_damage_multiplier.get()));
        }
        if (IllageAndSpillageConfig.nightmare_mode.get() && attacker instanceof RagnoEntity) {
            event.setAmount((float) (event.getAmount() * (IllageAndSpillageConfig.ragno_damage_multiplier.get())));
        }
        if (IllageAndSpillageConfig.nightmare_mode.get() && (attacker instanceof SpiritcallerEntity || attacker instanceof MobSpiritEntity || attacker instanceof IllagerSoulEntity)) {
            event.setAmount((float) (event.getAmount() * IllageAndSpillageConfig.spiri_damage_multiplier.get()));
        }
        if (IllageAndSpillageConfig.nightmare_mode.get() && attacker instanceof KaboomerEntity && victim.getUseItem().getItem() instanceof ShieldItem) {
            victim.getUseItem().shrink(victim.getUseItem().getCount());
        }
    }

//    @SubscribeEvent
//    public static void onEffectWearOff(MobEffectEvent event) {
//        if (event.getEffectInstance() != null && event.getEffectInstance().getEffect() instanceof RapidFireEffect && event.getEffectInstance().getDuration() == 0) {
//            LivingEntity entity = event.getEntity();
//            ItemStack crossbow = null;
//            if (entity.getMainHandItem().getItem() == ItemRegisterer.MAGI_CROSSBOW_ITEM.get()) {
//                crossbow = entity.getMainHandItem();
//            } else if (entity.getOffhandItem().getItem() == ItemRegisterer.MAGI_CROSSBOW_ITEM.get()) {
//                crossbow = entity.getOffhandItem();
//            }
//            if (entity instanceof Player && crossbow != null) {
//                CrossbowItem.onCrossbowShot(event.getEntity().level(), entity, crossbow);
//            }
//        }
//    }

//    @SubscribeEvent
//    public static void onClientTick(TickEvent.ClientTickEvent event) {
//        Minecraft mc = Minecraft.getInstance();
//        Player player = mc.player;
//        if (player != null && player.hasEffect(EffectRegisterer.CHANNELING.get())) {
//            player.setYRot(previousYaw);
//            player.setXRot(previousPitch);
//        } else if (player != null) {
//            previousYaw = player.getYRot();
//            previousPitch = player.getXRot();
//        }
//    }

//    @SubscribeEvent
//    public static void onJoinLevel(EntityJoinLevelEvent event) {
//        Entity entity = event.getEntity();
//        if (IllageAndSpillageConfig.nightmare_mode.get() && entity instanceof SpiritcallerEntity) {
//            ItemStack head = new ItemStack(Items.NETHERITE_HELMET);
//            head.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 3);
//            head.enchant(Enchantments.UNBREAKING, 255);
//            head.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
//            chest.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 3);
//            chest.enchant(Enchantments.UNBREAKING, 255);
//            chest.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack legs = new ItemStack(Items.NETHERITE_LEGGINGS);
//            legs.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 3);
//            legs.enchant(Enchantments.UNBREAKING, 255);
//            legs.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack feet = new ItemStack(Items.NETHERITE_BOOTS);
//            feet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 3);
//            feet.enchant(Enchantments.UNBREAKING, 255);
//            feet.enchant(Enchantments.VANISHING_CURSE, 1);
//            entity.setItemSlot(EquipmentSlot.HEAD, head);
//            entity.setItemSlot(EquipmentSlot.CHEST, chest);
//            entity.setItemSlot(EquipmentSlot.LEGS, legs);
//            entity.setItemSlot(EquipmentSlot.FEET, feet);
//        } else if (IllageAndSpillageConfig.nightmare_mode.get() && entity instanceof FreakagerEntity) {
//            ItemStack head = new ItemStack(Items.NETHERITE_HELMET);
//            head.enchant(Enchantments.PROJECTILE_PROTECTION, 7);
//            head.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            head.enchant(Enchantments.UNBREAKING, 255);
//            head.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
//            chest.enchant(Enchantments.PROJECTILE_PROTECTION, 7);
//            chest.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            chest.enchant(Enchantments.UNBREAKING, 255);
//            chest.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack legs = new ItemStack(Items.NETHERITE_LEGGINGS);
//            legs.enchant(Enchantments.PROJECTILE_PROTECTION, 7);
//            legs.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            legs.enchant(Enchantments.UNBREAKING, 255);
//            legs.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack feet = new ItemStack(Items.NETHERITE_BOOTS);
//            feet.enchant(Enchantments.PROJECTILE_PROTECTION, 7);
//            feet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            feet.enchant(Enchantments.UNBREAKING, 255);
//            feet.enchant(Enchantments.VANISHING_CURSE, 1);
//            entity.setItemSlot(EquipmentSlot.HEAD, head);
//            entity.setItemSlot(EquipmentSlot.CHEST, chest);
//            entity.setItemSlot(EquipmentSlot.LEGS, legs);
//            entity.setItemSlot(EquipmentSlot.FEET, feet);
//        } else if (IllageAndSpillageConfig.nightmare_mode.get() && entity instanceof RagnoEntity) {
//            ItemStack head = new ItemStack(Items.NETHERITE_HELMET);
//            head.enchant(Enchantments.UNBREAKING, 255);
//            head.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
//            chest.enchant(Enchantments.UNBREAKING, 255);
//            chest.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack legs = new ItemStack(Items.NETHERITE_LEGGINGS);
//            legs.enchant(Enchantments.UNBREAKING, 255);
//            legs.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack feet = new ItemStack(Items.NETHERITE_BOOTS);
//            feet.enchant(Enchantments.UNBREAKING, 255);
//            feet.enchant(Enchantments.VANISHING_CURSE, 1);
//            entity.setItemSlot(EquipmentSlot.HEAD, head);
//            entity.setItemSlot(EquipmentSlot.CHEST, chest);
//            entity.setItemSlot(EquipmentSlot.LEGS, legs);
//            entity.setItemSlot(EquipmentSlot.FEET, feet);
//        } else if (IllageAndSpillageConfig.nightmare_mode.get() && entity instanceof MagispellerEntity) {
//            ItemStack head = new ItemStack(Items.NETHERITE_HELMET);
//            head.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            head.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
//            head.enchant(Enchantments.UNBREAKING, 255);
//            head.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack chest = new ItemStack(Items.NETHERITE_CHESTPLATE);
//            chest.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            chest.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
//            chest.enchant(Enchantments.UNBREAKING, 255);
//            chest.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack legs = new ItemStack(Items.NETHERITE_LEGGINGS);
//            legs.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            legs.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
//            legs.enchant(Enchantments.UNBREAKING, 255);
//            legs.enchant(Enchantments.VANISHING_CURSE, 1);
//            ItemStack feet = new ItemStack(Items.NETHERITE_BOOTS);
//            feet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 2);
//            feet.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
//            feet.enchant(Enchantments.FALL_PROTECTION, 4);
//            feet.enchant(Enchantments.UNBREAKING, 255);
//            feet.enchant(Enchantments.VANISHING_CURSE, 1);
//            entity.setItemSlot(EquipmentSlot.HEAD, head);
//            entity.setItemSlot(EquipmentSlot.CHEST, chest);
//            entity.setItemSlot(EquipmentSlot.LEGS, legs);
//            entity.setItemSlot(EquipmentSlot.FEET, feet);
//        } else if (IllageAndSpillageConfig.nightmare_mode.get() && entity instanceof DispenserEntity) {
//            Objects.requireNonNull(((DispenserEntity) entity).getAttribute(Attributes.MAX_HEALTH)).setBaseValue(30.0);
//            ((DispenserEntity) entity).heal(50);
//        }
//        if (entity instanceof MagispellerEntity) {
//            entity.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemRegisterer.MAGI_AXE_ITEM.get()));
//            entity.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(ItemRegisterer.MAGI_CROSSBOW_ITEM.get()));
//        }
}

//    @SubscribeEvent
//    public static void misconductionAttack4(PlayerInteractEvent.RightClickEmpty event) {
//        Player player = event.getEntity();
//        if (player.getPersistentData().getInt("BeamCooldown") <= 0 && player.level().isClientSide && player.getMainHandItem().isEmpty() && player.hasEffect(EffectRegisterer.MODIFIED_MISCONDUCTION.get())) {
//            player.playSound(IllageAndSpillageSoundEvents.ENTITY_SPIRITCALLER_LASER.get(), 2.0F, 1.0F);
//            PacketHandler.CHANNEL.sendToServer(new MisconductionLaserAttackPacket());
//            player.getPersistentData().putInt("BeamCooldown", 680);
//        }
//    }