package com.compressedanimals.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.util.math.random.Random;

/**
 * Chooses which compressed tier replaces a natural vanilla animal spawn.
 * Tier 1 is the common replacement, tier 2 is deliberately common, and tier 3
 * is uncommon but not vanishingly rare.
 */
public final class CompressedSpawnReplacer {
    private static final int TIER1_PERCENT = 65;
    private static final int TIER2_PERCENT = 25;

    private CompressedSpawnReplacer() {
    }

    public static EntityType<?> choose(EntityType<?> type, Random random) {
        if (type == EntityType.COW) {
            return pick(random, ModEntities.COMPRESSED_COW_1, ModEntities.COMPRESSED_COW_2, ModEntities.COMPRESSED_COW_3);
        }
        if (type == EntityType.SHEEP) {
            return pick(random, ModEntities.COMPRESSED_SHEEP_1, ModEntities.COMPRESSED_SHEEP_2, ModEntities.COMPRESSED_SHEEP_3);
        }
        if (type == EntityType.PIG) {
            return pick(random, ModEntities.COMPRESSED_PIG_1, ModEntities.COMPRESSED_PIG_2, ModEntities.COMPRESSED_PIG_3);
        }
        if (type == EntityType.CHICKEN) {
            return pick(random, ModEntities.COMPRESSED_CHICKEN_1, ModEntities.COMPRESSED_CHICKEN_2, ModEntities.COMPRESSED_CHICKEN_3);
        }
        return type;
    }

    private static EntityType<?> pick(
            Random random,
            EntityType<?> tier1,
            EntityType<?> tier2,
            EntityType<?> tier3
    ) {
        int roll = random.nextInt(100);
        if (roll < TIER1_PERCENT) {
            return tier1;
        }
        if (roll < TIER1_PERCENT + TIER2_PERCENT) {
            return tier2;
        }
        return tier3;
    }
}
