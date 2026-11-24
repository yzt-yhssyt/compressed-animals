package com.compressedanimals.registry;

import com.compressedanimals.mixin.MobEntityAccessor;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;

/**
 * Makes the common undead hostile mobs hunt nearby animals. The goal uses a
 * lower priority than their player/villager goals, so players are still the
 * primary target; animals are attacked when no better target is around.
 */
public final class HostileAnimalTargets {
    private HostileAnimalTargets() {
    }

    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof ZombieEntity) && !(entity instanceof AbstractSkeletonEntity)) {
                return;
            }
            if (!(entity instanceof MobEntity mob)) {
                return;
            }
            ((MobEntityAccessor) mob).compressedAnimals$getTargetSelector().add(
                    4,
                    new ActiveTargetGoal<>(
                            mob,
                            AnimalEntity.class,
                            true,
                            HostileAnimalTargets::canTarget
                    )
            );
        });
    }

    private static boolean canTarget(LivingEntity entity) {
        return !(entity instanceof TameableEntity tameable && tameable.isTamed());
    }
}
