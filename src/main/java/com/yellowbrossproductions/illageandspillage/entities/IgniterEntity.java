package com.yellowbrossproductions.illageandspillage.entities;

import com.yellowbrossproductions.illageandspillage.entities.projectile.IgniterFireballEntity;
import com.yellowbrossproductions.illageandspillage.util.EntityUtil;
import com.yellowbrossproductions.illageandspillage.util.IllageAndSpillageSoundEvents;
import java.util.EnumSet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;

public class IgniterEntity extends AbstractIllager {
    private static final EntityDataAccessor<Boolean> ATTACKING;
    private static final EntityDataAccessor<Boolean> TORCH_BURNT_OUT;
    private int shootTicks;
    private int fireballsShot;
    private int fireballTimer;
    private int cooldownTicks;

    public IgniterEntity(EntityType<? extends AbstractIllager> p_i48556_1_, Level p_i48556_2_) {
        super(p_i48556_1_, p_i48556_2_);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new ShootFireballsGoal());
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.8, 1.0, (p_234199_0_) -> this.shouldRunLikeCrazy()));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, IronGolem.class, 8.0F, 0.8, 1.0, (p_234199_0_) -> this.shouldRunLikeCrazy()));
        this.goalSelector.addGoal(2, new Raider.HoldGroundAttackGoal(this, 10.0F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Sheep.class, 10, false, false, (p_234199_0_) -> p_234199_0_ instanceof Sheep && ((Sheep)p_234199_0_).getColor() == DyeColor.PINK));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Raider.class, 10, false, false, Entity::isOnFire));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3499999940395355).add(Attributes.MAX_HEALTH, 24.0).add(Attributes.ATTACK_DAMAGE, 5.0).add(Attributes.FOLLOW_RANGE, 32.0);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(TORCH_BURNT_OUT, false);
    }

    public boolean canAttack(LivingEntity p_186270_) {
        return (!(p_186270_ instanceof Player) || this.level().getDifficulty() != Difficulty.PEACEFUL) && p_186270_.canBeSeenAsEnemy();
    }

    public void applyRaidBuffs(int p_213660_1_, boolean p_213660_2_) {
    }

    public void tick() {
        super.tick();
        if (this.isAlive()) {
            if (this.isAttacking()) {
                ++this.shootTicks;
            } else {
                this.shootTicks = 0;
            }

            if (this.fireballsShot > 0) {
                if (!this.isAttacking()) {
                    ++this.fireballTimer;
                    if (this.fireballTimer > 20) {
                        this.fireballTimer = 0;
                        --this.fireballsShot;
                    }
                } else {
                    this.fireballTimer = 0;
                }
            }

            if (this.fireballsShot > 25) {
                this.cooldownTicks = 300;
                this.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 0.8F);
                this.fireballsShot = 0;
            }

            --this.cooldownTicks;
            if (this.cooldownTicks < 0) {
                this.cooldownTicks = 0;
            }

            if (this.shootTicks >= 4) {
                this.playSound(SoundEvents.DISPENSER_LAUNCH, 1.0F, 1.0F);
                if (this.getTarget() != null) {
                    if (this.isTorchBurntOut()) {
                        this.shootSnowball(this.getTarget());
                    } else {
                        this.playSound(SoundEvents.FIRECHARGE_USE, 1.0F, 1.0F);
                        this.shootFireball(this.getTarget());
                        ++this.fireballsShot;
                    }
                }

                this.shootTicks = 0;
            }

            if (this.getTarget() instanceof Raider && EntityUtil.isMobNotOnOtherTeam(this.getTarget(), this)) {
                if (!this.level().isClientSide) {
                    this.setTorchBurntOut(true);
                }

                if (!this.getTarget().isOnFire()) {
                    this.setTarget(null);
                }
            } else if (!this.level().isClientSide) {
                this.setTorchBurntOut(false);
            }
        }

    }

    public void shootSnowball(LivingEntity p_82196_1_) {
        Snowball snowballentity = new Snowball(this.level(), this);
        double d0 = p_82196_1_.getEyeY() - 1.100000023841858;
        double d1 = p_82196_1_.getX() - this.getX();
        double d2 = d0 - snowballentity.getY();
        double d3 = p_82196_1_.getZ() - this.getZ();
        float f = (float)(Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224);
        snowballentity.shoot(d1, d2 + (double)f, d3, 1.6F, 12.0F);
        this.level().addFreshEntity(snowballentity);
    }

    public void shootFireball(LivingEntity target) {
        double d0 = this.distanceToSqr(target);
        double d1 = target.getX() - this.getX();
        double d2 = target.getY(0.5) - this.getY(0.5);
        double d3 = target.getZ() - this.getZ();
        float f = (float)(Math.sqrt(Math.sqrt(d0)) * 0.5);
        IgniterFireballEntity fireballentity = new IgniterFireballEntity(this.level(), this, d1 + this.getRandom().nextGaussian() * (double)f, d2, d3 + this.getRandom().nextGaussian() * (double)f);
        fireballentity.setPos(fireballentity.getX(), this.getY(0.5) + 0.5, fireballentity.getZ());
        this.level().addFreshEntity(fireballentity);
    }

    public SoundEvent getCelebrateSound() {
        return IllageAndSpillageSoundEvents.ENTITY_IGNITER_CELEBRATE.get();
    }

    protected SoundEvent getAmbientSound() {
        return IllageAndSpillageSoundEvents.ENTITY_IGNITER_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return IllageAndSpillageSoundEvents.ENTITY_IGNITER_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return IllageAndSpillageSoundEvents.ENTITY_IGNITER_DEATH.get();
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isTorchBurntOut() {
        return this.entityData.get(TORCH_BURNT_OUT);
    }

    public void setTorchBurntOut(boolean burnt) {
        this.entityData.set(TORCH_BURNT_OUT, burnt);
    }

    public boolean shouldRunLikeCrazy() {
        return this.cooldownTicks > 0;
    }

    public boolean doHurtTarget(Entity p_70652_1_) {
        return false;
    }

    @Override
    public boolean killedEntity(ServerLevel level, LivingEntity entity) {
        if (entity instanceof Sheep && ((Sheep)entity).getColor() == DyeColor.PINK && this.getTarget() == entity) {
            this.playSound(this.getCelebrateSound(), 1.0F, 1.0F);
        }
        return super.killedEntity(level, entity);
    }

    static {
        ATTACKING = SynchedEntityData.defineId(IgniterEntity.class, EntityDataSerializers.BOOLEAN);
        TORCH_BURNT_OUT = SynchedEntityData.defineId(IgniterEntity.class, EntityDataSerializers.BOOLEAN);
    }

    class ShootFireballsGoal extends Goal {
        public ShootFireballsGoal() {
            this.setFlags(EnumSet.of(Flag.JUMP, Flag.LOOK, Flag.MOVE));
        }

        public boolean canUse() {
            return IgniterEntity.this.getTarget() != null && IgniterEntity.this.distanceToSqr(IgniterEntity.this.getTarget()) < 90.0 && IgniterEntity.this.hasLineOfSight(IgniterEntity.this.getTarget()) && !IgniterEntity.this.shouldRunLikeCrazy();
        }

        public void start() {
            IgniterEntity.this.setAttacking(true);
            IgniterEntity.this.playSound(SoundEvents.LEVER_CLICK, 1.0F, 0.6F);
        }

        public boolean canContinueToUse() {
            return IgniterEntity.this.getTarget() != null && IgniterEntity.this.distanceToSqr(IgniterEntity.this.getTarget()) < 90.0 && IgniterEntity.this.getTarget().isAlive() && IgniterEntity.this.hasLineOfSight(IgniterEntity.this.getTarget()) && !IgniterEntity.this.shouldRunLikeCrazy();
        }

        public void tick() {
            IgniterEntity.this.getNavigation().stop();
            if (IgniterEntity.this.getTarget() != null) {
                IgniterEntity.this.getLookControl().setLookAt(IgniterEntity.this.getTarget(), 30.0F, 30.0F);
            }

            IgniterEntity.this.navigation.stop();
        }

        public void stop() {
            IgniterEntity.this.setAttacking(false);
            IgniterEntity.this.playSound(SoundEvents.LEVER_CLICK, 1.0F, 0.5F);
        }
    }
}
