package com.compressedanimals.registry;

import com.compressedanimals.entity.CompressedAnimal;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;

/**
 * Replaces vanilla cows, sheep, pigs and chickens that already exist in a world
 * with their tier-1 compressed counterparts. Natural spawning is handled by
 * {@code SpawnHelperMixin}, but that only affects new spawns; this listener also
 * converts old persisted animals when their chunk is loaded.
 */
public final class VanillaAnimalConverter {
    private VanillaAnimalConverter() {
    }

    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof AnimalEntity animal) || animal instanceof CompressedAnimal) {
                return;
            }
            if (animal.hasVehicle() || animal.hasPassengers()) {
                return;
            }

            EntityType<? extends AnimalEntity> replacementType = replacementType(animal);
            if (replacementType == null) {
                return;
            }

            AnimalEntity replacement = replacementType.create(world);
            if (replacement == null) {
                return;
            }

            replacement.refreshPositionAndAngles(
                    animal.getX(), animal.getY(), animal.getZ(),
                    animal.getYaw(), animal.getPitch()
            );
            replacement.setVelocity(animal.getVelocity());
            replacement.setBaby(animal.isBaby());
            replacement.setInvulnerable(animal.isInvulnerable());
            for (String tag : animal.getCommandTags()) {
                replacement.addCommandTag(tag);
            }
            if (animal.hasCustomName()) {
                replacement.setCustomName(animal.getCustomName());
                replacement.setCustomNameVisible(animal.isCustomNameVisible());
            }
            if (animal.isPersistent()) {
                replacement.setPersistent();
            }
            if (animal.isLeashed() && animal.getLeashHolder() != null) {
                replacement.attachLeash(animal.getLeashHolder(), true);
            }
            if (animal instanceof SheepEntity oldSheep && replacement instanceof SheepEntity newSheep) {
                newSheep.setColor(oldSheep.getColor());
                newSheep.setSheared(oldSheep.isSheared());
            }

            world.spawnEntity(replacement);
            animal.discard();
        });
    }

    private static EntityType<? extends AnimalEntity> replacementType(AnimalEntity animal) {
        // Exact class checks intentionally leave modded subclasses (mooshrooms,
        // modded variants, etc.) and other passive animals untouched.
        if (animal.getClass() == CowEntity.class) {
            return ModEntities.COMPRESSED_COW_1;
        }
        if (animal.getClass() == SheepEntity.class) {
            return ModEntities.COMPRESSED_SHEEP_1;
        }
        if (animal.getClass() == PigEntity.class) {
            return ModEntities.COMPRESSED_PIG_1;
        }
        if (animal.getClass() == ChickenEntity.class) {
            return ModEntities.COMPRESSED_CHICKEN_1;
        }
        return null;
    }
}
