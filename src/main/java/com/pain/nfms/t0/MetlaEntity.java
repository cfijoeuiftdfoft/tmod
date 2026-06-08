package com.pain.nfms.t0;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
// import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MetlaEntity extends VehicleEntity implements Leashable {
    private Status status;
    private Status oldStatus;
    private float outOfControlTicks;
    @Nullable
    private Leashable.LeashData leashData;

    public MetlaEntity(EntityType<? extends VehicleEntity> entityType, Level level) {
        super(entityType, level);
    }

    public void setLeashData(@Nullable Leashable.LeashData leashData) {
        this.leashData = leashData;
    }

    protected void addAdditionalSaveData(@Nonnull CompoundTag compound) {
        this.writeLeashData(compound, this.leashData);
    }

    protected void readAdditionalSaveData(@Nonnull CompoundTag compound) {
        this.leashData = this.readLeashData(compound);
    }

    @Nullable
    public Leashable.LeashData getLeashData() {
        return this.leashData;
    }

    public Item getDropItem() {
        return TItems.METLA.get();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 100.0F).add(Attributes.MOVEMENT_SPEED, 0.5F);
    }

    public InteractionResult interact(@Nonnull Player player, @Nonnull InteractionHand hand) {
        InteractionResult interactionresult = super.interact(player, hand);
        if (interactionresult != InteractionResult.PASS) {
            return interactionresult;
        } else if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else {
            if (!this.level().isClientSide) {
                return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
            } else {
                return InteractionResult.SUCCESS;
            }
        }
    }

    public void tick() {
        this.oldStatus = this.status;
        this.status = this.getStatus();
        if (this.status != MetlaEntity.Status.UNDER_WATER && this.status != MetlaEntity.Status.UNDER_FLOWING_WATER) {
            this.outOfControlTicks = 0.0F;
        } else {
            ++this.outOfControlTicks;
        }

        if (!this.level().isClientSide && this.outOfControlTicks >= 60.0F) {
            this.ejectPassengers();
        }

        if (this.getHurtTime() > 0) {
            this.setHurtTime(this.getHurtTime() - 1);
        }

        if (this.getDamage() > 0.0F) {
            this.setDamage(this.getDamage() - 1.0F);
        }

        super.tick();
        this.tickLerp();
        if (this.isControlledByLocalInstance()) {
            if (!(this.getFirstPassenger() instanceof Player)) {
                this.setPaddleState(false, false);
            }

            this.floatBoat();
            if (this.level().isClientSide) {
                this.controlBoat();
                this.level().sendPacketToServer(new ServerboundPaddleBoatPacket(this.getPaddleState(0), this.getPaddleState(1)));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }

        this.tickBubbleColumn();

        for(int i = 0; i <= 1; ++i) {
            if (this.getPaddleState(i)) {
                if (!this.isSilent() && (double)(this.paddlePositions[i] % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && (double)((this.paddlePositions[i] + ((float)Math.PI / 8F)) % ((float)Math.PI * 2F)) >= (double)((float)Math.PI / 4F)) {
                SoundEvent soundevent = this.getPaddleSound();
                if (soundevent != null) {
                    Vec3 vec3 = this.getViewVector(1.0F);
                    double d0 = i == 1 ? -vec3.z : vec3.z;
                    double d1 = i == 1 ? vec3.x : -vec3.x;
                    this.level().playSound((Player)null, this.getX() + d0, this.getY(), this.getZ() + d1, soundevent, this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
                }
                }

                this.paddlePositions[i] += ((float)Math.PI / 8F);
            } else {
                this.paddlePositions[i] = 0.0F;
            }
        }

        this.checkInsideBlocks();
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate((double)0.2F, (double)-0.01F, (double)0.2F), EntitySelector.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

            for(Entity entity : list) {
                if (!entity.hasPassenger(this)) {
                if (flag && this.getPassengers().size() < this.getMaxPassengers() && !entity.isPassenger() && this.hasEnoughSpaceFor(entity) && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
                    entity.startRiding(this);
                } else {
                    this.push(entity);
                }
                }
            }
        }

    }

    public static enum Status {
        // IN_WATER,
        // UNDER_WATER,
        // UNDER_FLOWING_WATER,
        ON_LAND,
        IN_AIR;

        private Status() {
        }
   }
}
