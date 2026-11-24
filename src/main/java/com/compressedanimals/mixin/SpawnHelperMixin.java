package com.compressedanimals.mixin;

import com.compressedanimals.registry.CompressedSpawnReplacer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * Replaces the vanilla cow/sheep/pig/chicken spawn entries with compressed
 * versions before the spawn is validated. Because the replacement happens here,
 * only those four animals are affected; horses, mules and all other mobs keep
 * their normal spawning.
 */
@Mixin(SpawnHelper.class)
public final class SpawnHelperMixin {
    private SpawnHelperMixin() {
    }

    @Inject(method = "pickRandomSpawnEntry", at = @At("RETURN"), cancellable = true)
    private static void compressedAnimals$replaceVanillaAnimal(
            ServerWorld world,
            StructureAccessor structureAccessor,
            ChunkGenerator chunkGenerator,
            SpawnGroup spawnGroup,
            Random random,
            BlockPos pos,
            CallbackInfoReturnable<Optional<SpawnSettings.SpawnEntry>> cir
    ) {
        Optional<SpawnSettings.SpawnEntry> result = cir.getReturnValue();
        if (result == null || result.isEmpty()) {
            return;
        }

        SpawnSettings.SpawnEntry entry = result.get();
        EntityType<?> replacement = CompressedSpawnReplacer.choose(entry.type, random);
        if (replacement == entry.type) {
            return;
        }

        cir.setReturnValue(Optional.of(new SpawnSettings.SpawnEntry(
                replacement,
                entry.getWeight().getValue(),
                entry.minGroupSize,
                entry.maxGroupSize
        )));
    }
}
