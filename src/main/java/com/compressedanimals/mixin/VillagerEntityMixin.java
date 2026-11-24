package com.compressedanimals.mixin;

import com.compressedanimals.registry.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LargeEntitySpawnHelper;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

/**
 * Gives the villager golem-summon reaction a 10% chance to produce the compressed
 * tier-1 iron golem instead of a vanilla iron golem.
 */
@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Redirect(
            method = "summonGolem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LargeEntitySpawnHelper;trySpawnAt(Lnet/minecraft/entity/EntityType;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;IIILnet/minecraft/entity/LargeEntitySpawnHelper$Requirements;)Ljava/util/Optional;"
            )
    )
    private static Optional<MobEntity> compressedAnimals$maybeSpawnCompressedGolem(
            EntityType<? extends MobEntity> type,
            SpawnReason reason,
            ServerWorld world,
            BlockPos pos,
            int attempts,
            int offsetY,
            int offsetY2,
            LargeEntitySpawnHelper.Requirements requirements
    ) {
        boolean compressed = type == EntityType.IRON_GOLEM && world.getRandom().nextFloat() < 0.10F;

        @SuppressWarnings("unchecked")
        EntityType<MobEntity> chosen = (EntityType<MobEntity>) (compressed
                ? ModEntities.COMPRESSED_IRON_GOLEM
                : type);
        return LargeEntitySpawnHelper.trySpawnAt(chosen, reason, world, pos, attempts, offsetY, offsetY2, requirements);
    }
}
