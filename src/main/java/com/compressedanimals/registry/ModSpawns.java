package com.compressedanimals.registry;

import com.compressedanimals.mixin.SpawnRestrictionAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;

/**
 * Registers valid natural-spawn placements for all compressed animal types.
 *
 * <p>The actual vanilla-to-compressed replacement happens in
 * {@code SpawnHelperMixin}, which swaps the vanilla cow/sheep/pig/chicken spawn
 * entry before the spawn position is checked. This means horses, mules and
 * every other animal keep their vanilla spawning untouched.</p>
 */
public final class ModSpawns {
    private ModSpawns() {
    }

    public static void register() {
        registerRestriction(ModEntities.COMPRESSED_COW_1);
        registerRestriction(ModEntities.COMPRESSED_COW_2);
        registerRestriction(ModEntities.COMPRESSED_COW_3);
        registerRestriction(ModEntities.COMPRESSED_SHEEP_1);
        registerRestriction(ModEntities.COMPRESSED_SHEEP_2);
        registerRestriction(ModEntities.COMPRESSED_SHEEP_3);
        registerRestriction(ModEntities.COMPRESSED_PIG_1);
        registerRestriction(ModEntities.COMPRESSED_PIG_2);
        registerRestriction(ModEntities.COMPRESSED_PIG_3);
        registerRestriction(ModEntities.COMPRESSED_CHICKEN_1);
        registerRestriction(ModEntities.COMPRESSED_CHICKEN_2);
        registerRestriction(ModEntities.COMPRESSED_CHICKEN_3);
    }

    private static <T extends AnimalEntity> void registerRestriction(EntityType<T> type) {
        SpawnRestrictionAccessor.callRegister(
                type,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) ->
                        AnimalEntity.isValidNaturalSpawn(entityType, world, reason, pos, random)
        );
    }
}
