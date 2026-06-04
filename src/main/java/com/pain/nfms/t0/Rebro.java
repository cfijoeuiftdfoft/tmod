package com.pain.nfms.t0;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;

// import java.text.AttributedCharacterIterator.Attribute;

import net.minecraft.world.entity.EntityType;
// import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
// import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
// import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
// import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
// import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Wolf;

public class Rebro extends TamableAnimal {
    public Rebro(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isFood(@Nonnull ItemStack fitem) {
        return true;
    }

    public void aiStep() {
        this.updateSwingTime();
        super.aiStep();
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        Wolf wolf = (Wolf)EntityType.WOLF.create(level);
        if (wolf != null && otherParent instanceof Wolf) {
            if (this.isTame()) {
                wolf.setOwnerUUID(this.getOwnerUUID());
                wolf.setTame(true, true);
            }
        }

        return wolf;
    }

    @Override
    public boolean canMate(@Nullable Animal other) {
        return true;
    }

//     public void swing(@Nonnull InteractionHand hand, boolean updateSelf) {
//         super.swing(hand, updateSelf);
//         System.out.println("swing");
//         System.out.println(this.swingTime);
//         System.out.println(this.swinging);
//         ItemStack stack = this.getItemInHand(hand);
//         if (stack.isEmpty() || !stack.onEntitySwing(this, hand)) {
//             System.out.println("swing2");
//             if (!this.swinging || this.swingTime >= this.getCurrentSwingDuration() / 2 || this.swingTime < 0) {
//                 System.out.println("swing3");
//                 this.swingTime = -1;
//                 this.swinging = true;
//                 this.swingingArm = hand;
//                 if (this.level() instanceof ServerLevel) {
//                 ClientboundAnimatePacket clientboundanimatepacket = new ClientboundAnimatePacket(this, hand == InteractionHand.MAIN_HAND ? 0 : 3);
//                 ServerChunkCache serverchunkcache = ((ServerLevel)this.level()).getChunkSource();
//                 if (updateSelf) {
//                     serverchunkcache.broadcastAndSend(this, clientboundanimatepacket);
//                 } else {
//                     serverchunkcache.broadcast(this, clientboundanimatepacket);
//                 }
//                 }
//             }

//         }
//    }

    @Override
    public boolean doHurtTarget(@Nonnull Entity target) {
        System.out.println("dohurttarget");
        boolean hurt = super.doHurtTarget(target);
        System.out.println(getCurrentSwingDuration());
        // if (hurt) {
        //     System.out.println("dht2");
        //     this.swing(InteractionHand.MAIN_HAND);
        // }
        return hurt;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreedGoal(this, 1.0f, Wolf.class));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(0, new NoBabyAtackGoal<>(this, Villager.class, true));
    }

    @SuppressWarnings("null")
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 67.0).add(Attributes.MOVEMENT_SPEED, 0.25F).add(Attributes.ATTACK_DAMAGE, 16000000.0F).add(Attributes.FOLLOW_RANGE, 10.0F);
    }
}
