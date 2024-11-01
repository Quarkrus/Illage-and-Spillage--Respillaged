package com.yellowbrossproductions.illageandspillage.entities;

import com.yellowbrossproductions.illageandspillage.config.IllageAndSpillageConfig;
import com.yellowbrossproductions.illageandspillage.util.EffectRegisterer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;

import java.util.List;

public class HayArmorEntity extends PathfinderMob implements IllagerAttack {
    private LivingEntity owner;

    public HayArmorEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.0).add(Attributes.ATTACK_DAMAGE, 0.0).add(Attributes.MAX_HEALTH, 2.0).add(Attributes.FOLLOW_RANGE, 0.0);
    }

    public void addAdditionalSaveData(CompoundTag p_21819_) {
        super.addAdditionalSaveData(p_21819_);
    }

    public void readAdditionalSaveData(CompoundTag p_21815_) {
        super.readAdditionalSaveData(p_21815_);
    }

    protected boolean canRide(Entity p_20339_) {
        return false;
    }

    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.FELL_OUT_OF_WORLD) || source.is(DamageTypes.GENERIC_KILL)) {
            return super.hurt(source, amount);
        }

        return false;
    }

    public void die(DamageSource p_21014_) {
        this.deathTime = 19;
        super.die(p_21014_);
    }

    public void tick() {
        this.setInvulnerable(true);
        this.noPhysics = true;
        this.setNoGravity(true);
        if (this.owner == null && this.tickCount > 5) {
            List<Raider> list = this.level().getEntitiesOfClass(Raider.class, this.getBoundingBox().inflate(2.0), (predicate) -> predicate.isAlive() && !(predicate instanceof PreserverEntity) && (double) predicate.getBbWidth() < 1.0 && (double) predicate.getBbHeight() < 2.5 && !predicate.hasEffect(EffectRegisterer.PRESERVED.get()) && !(IllageAndSpillageConfig.preserver_cannotProtect.get()).contains(predicate.getEncodeId()));
            if (!list.isEmpty()) {
                this.owner = list.get(this.random.nextInt(list.size()));
            }
        }

        if (this.tickCount > 10) {
            if (this.owner == null && !this.level().isClientSide) {
                this.discard();
            }

            if (this.owner != null && !this.owner.isAlive() && !this.level().isClientSide) {
                this.discard();
            }
        }

        super.tick();
        if (this.owner != null && this.owner.isAlive()) {
//            this.owner.addEffect(new MobEffectInstance(EffectRegisterer.PRESERVED.get(), 20, 0, false, false));
            this.setPos(this.owner.getX(), this.owner.getY(), this.owner.getZ());
            this.setYRot(this.owner.getYHeadRot());
            this.setYBodyRot(this.owner.getYHeadRot());
            this.yBodyRot = this.owner.getYHeadRot();
            this.xOld = this.owner.xOld;
            this.yOld = this.owner.yOld;
            this.zOld = this.owner.zOld;
        }

    }

    public void aiStep() {
        if (this.tickCount < 10 && this.level().isClientSide) {
            for (int i = 0; i < 10; ++i) {
                double d0 = (-0.5 + this.random.nextGaussian()) / 4.0;
                double d1 = (-0.5 + this.random.nextGaussian()) / 4.0;
                double d2 = (-0.5 + this.random.nextGaussian()) / 4.0;
                this.level().addParticle(ParticleTypes.EXPLOSION, this.getRandomX(1.0), this.getRandomY() + 1.0, this.getRandomZ(1.0), d0, d1, d2);
            }
        }

        super.aiStep();
    }

    public boolean shouldRenderAtSqrDistance(double distance) {
        return distance < 1024.0;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent getDeathSound() {
        return null;
    }

    public boolean canCollideWith(Entity entity) {
        return false;
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean isPickable() {
        return false;
    }

    protected void doPush(Entity entityIn) {
    }

    public void push(Entity entityIn) {
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }
}
