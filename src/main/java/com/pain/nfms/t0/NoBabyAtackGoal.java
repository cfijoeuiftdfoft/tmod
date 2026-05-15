package com.pain.nfms.t0;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;

public class NoBabyAtackGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    public NoBabyAtackGoal(Mob mob, Class<T> targetClass, boolean checkSight) {
        super(mob, targetClass, checkSight);
    }

        @Override
        public boolean canUse() {
        // Сначала находим ближайшую цель через super
        if (!super.canUse()) {
            return false;
        }
        
        // Проверяем, является ли найденная цель ребенком-зомби
        if (this.target != null && this.target instanceof Villager zombie && zombie.isBaby()) {
            return true;
        }
        
        return false;
    }

    @Override
    protected boolean canAttack(@Nullable LivingEntity target, @Nonnull TargetingConditions conditions) {
        // Базовая проверка (дистанция, жив ли и т.д.)
        if (!super.canAttack(target, conditions)) {
            return false;
        }
        
        // Проверяем, что цель - ребенок зомби
        return target instanceof Villager zombie && zombie.isBaby();
    }
}
