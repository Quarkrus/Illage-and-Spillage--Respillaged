package com.yellowbrossproductions.illageandspillage.entities.projectile;

import com.yellowbrossproductions.illageandspillage.entities.IllagerAttack;
import com.yellowbrossproductions.illageandspillage.packet.PacketHandler;
import com.yellowbrossproductions.illageandspillage.packet.ParticlePacket;
import com.yellowbrossproductions.illageandspillage.util.EntityUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import java.util.Iterator;
import java.util.List;

public class OldAxeEntity extends PathfinderMob implements IllagerAttack {
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    public Mob shooter = null;

    public OldAxeEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.0).add(Attributes.MAX_HEALTH, 2.0).add(Attributes.ATTACK_DAMAGE, 0.0).add(Attributes.FOLLOW_RANGE, 32.0);
    }

    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource bullcrap) {
        if (!this.level().isClientSide) {
            this.discard();
        }

        return false;
    }

    public boolean canBeAffected(MobEffectInstance p_21197_) {
        return false;
    }

    public void tick() {
        this.setInvulnerable(true);
        this.noPhysics = true;
        Mob attacker = this.shooter != null ? this.shooter : this;
        List<Entity> list = this.level().getEntities(this, new AABB(this.getX() - 0.4, this.getY() - 0.4, this.getZ() - 0.4, this.getX() + 0.4, this.getY() + 0.4, this.getZ() + 0.4), Entity::isAlive);

        for (Entity entity : list) {
            if (entity instanceof LivingEntity living) {
                if (EntityUtil.canHurtThisMob(living, attacker) && entity.isAlive() && !entity.isInvulnerable() && !entity.isSpectator()) {
                    living.hurt(this.damageSources().thrown(entity, this), 8.0F);
                    living.invulnerableTime = 0;
                    EntityUtil.disableShield(living, 200);
                    if (!this.level().isClientSide) {
                        this.discard();
                    }
                }
            }
        }

        this.makeParticles();
        this.setDeltaMovement(this.accelerationX, this.accelerationY, this.accelerationZ);
        if (this.tickCount >= 100 && !this.level().isClientSide) {
            this.discard();
        }

        super.tick();
        this.setYRot(this.getYHeadRot());
        this.yBodyRot = this.getYRot();
    }

    public void setAcceleration(double x, double y, double z) {
        this.accelerationX = x;
        this.accelerationY = y;
        this.accelerationZ = z;
    }

    public void makeParticles() {
        if (!this.level().isClientSide) {
            Iterator<ServerPlayer> var1 = ((ServerLevel) this.level()).players().iterator();

            while (true) {
                ServerPlayer serverPlayer;
                do {
                    if (!var1.hasNext()) {
                        return;
                    }

                    serverPlayer = var1.next();
                } while (!(serverPlayer.distanceToSqr(this) < 4096.0));

                ParticlePacket packet = new ParticlePacket();

                for (int i = 0; i < 1; ++i) {
                    double d0 = -0.5 + this.random.nextGaussian();
                    double d1 = -0.5 + this.random.nextGaussian();
                    double d2 = -0.5 + this.random.nextGaussian();
                    packet.queueParticle(ParticleTypes.CRIT, false, new Vec3(this.getRandomX(1.0), this.getRandomY(), this.getRandomZ(1.0)), new Vec3(d0, d1, d2));
                }

                ServerPlayer finalServerPlayer = serverPlayer;
                PacketHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> finalServerPlayer), packet);
            }
        }
    }

    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        Vec3 vector3d = (new Vec3(x, y, z)).normalize().add(this.random.nextGaussian() * 0.007499999832361937 * (double) inaccuracy, this.random.nextGaussian() * 0.007499999832361937 * (double) inaccuracy, this.random.nextGaussian() * 0.007499999832361937 * (double) inaccuracy).scale((double) velocity);
        this.setDeltaMovement(vector3d);
        float f = (float) vector3d.horizontalDistanceSqr();
        this.setYHeadRot((float) (Mth.atan2(vector3d.x, vector3d.z) * 57.2957763671875));
        this.setYRot((float) (Mth.atan2(vector3d.x, vector3d.z) * 57.2957763671875));
        this.setXRot((float) (Mth.atan2(vector3d.y, (double) f) * 57.2957763671875));
        this.setYRot(this.yRotO);
        this.setXRot(this.xRotO);
    }

    public void shootFromRotation(Entity p_234612_1_, float p_234612_2_, float p_234612_3_, float p_234612_4_, float p_234612_5_, float p_234612_6_) {
        float f = -Mth.sin(p_234612_3_ * 0.017453292F) * Mth.cos(p_234612_2_ * 0.017453292F);
        float f1 = -Mth.sin((p_234612_2_ + p_234612_4_) * 0.017453292F);
        float f2 = Mth.cos(p_234612_3_ * 0.017453292F) * Mth.cos(p_234612_2_ * 0.017453292F);
        this.shoot(f, f1, f2, p_234612_5_, p_234612_6_);
        Vec3 vector3d = p_234612_1_.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vector3d.x, p_234612_1_.onGround() ? 0.0 : vector3d.y, vector3d.z));
        this.accelerationX = this.getDeltaMovement().x;
        this.accelerationY = this.getDeltaMovement().y;
        this.accelerationZ = this.getDeltaMovement().z;
    }

    public void setShooter(Mob shooter) {
        this.shooter = shooter;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent getDeathSound() {
        return null;
    }

    public boolean hurt(DamageSource source, float amount) {
        return source.is(DamageTypes.GENERIC_KILL) && super.hurt(source, amount);
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
}