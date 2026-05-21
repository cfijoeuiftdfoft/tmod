package com.pain.nfms.t0;

// import java.text.AttributedCharacterIterator.Attribute;

import net.minecraft.world.entity.EntityType;
// import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
// import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
// import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
// import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
// import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;

public class Rebro extends Monster {
    public Rebro(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
                // 0: Плавание (обязательно, чтобы не тонул)
        this.goalSelector.addGoal(0, new FloatGoal(this));
        
        // 1: Атака (нужна чтобы двигался К цели)
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
        
        // 2: Случайное движение (БЕЗ этого не будет ходить!)
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.8));
        
        // 4: Случайно оглядывается
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        
        // Цели для атаки (target goals)
        // Атакует того, кто ударил
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(0, new NoBabyAtackGoal<>(this, Villager.class, true));
    }

    @SuppressWarnings("null")
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 67.0).add(Attributes.MOVEMENT_SPEED, 0.5).add(Attributes.ATTACK_DAMAGE, 0.1).add(Attributes.FOLLOW_RANGE, 35.0);
    }
}
