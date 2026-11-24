package com.compressedanimals.entity;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;

/**
 * Applies the per-entity health roll requested for compressed animals.
 * Tier 1 varies by +/-25 HP, tier 2 by +/-520 HP, tier 3 by +/-1000 HP.
 * Tier 3 also gets a random attack damage in the 5-20 range.
 */
public final class AnimalHealth {
    private AnimalHealth() {
    }

    public static void randomize(MobEntity entity, int tier, double baseHealth) {
        int spread = switch (tier) {
            case 3 -> 1000;
            case 2 -> 520;
            default -> 25;
        };
        int offset = entity.getRandom().nextInt(spread * 2 + 1) - spread;
        double health = Math.max(1.0D, baseHealth + offset);

        EntityAttributeInstance maxHealth = entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(health);
        }
        entity.setHealth((float) health);

        if (tier == 3) {
            EntityAttributeInstance attackDamage = entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            if (attackDamage != null) {
                attackDamage.setBaseValue(5.0D + entity.getRandom().nextInt(16));
            }
        }
    }
}
