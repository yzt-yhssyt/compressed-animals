package com.compressedanimals.entity;

import com.compressedanimals.registry.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class CompressedPigEntity extends PigEntity implements CompressedAnimal {
    public CompressedPigEntity(EntityType<? extends PigEntity> entityType, World world) {
        super(entityType, world);
        if (!world.isClient) {
            AnimalHealth.randomize(this, getTier(), baseHealth(getTier()));
        }
    }

    private static double baseHealth(int tier) {
        return switch (tier) {
            case 3 -> 72900.0D;
            case 2 -> 7290.0D;
            default -> 270.0D;
        };
    }

    public static DefaultAttributeContainer.Builder createAttributes(int tier) {
        return PigEntity.createPigAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, baseHealth(tier))
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
                .add(EntityAttributes.GENERIC_SCALE, switch (tier) {
                    case 3 -> 4.0D;
                    case 2 -> 2.5D;
                    default -> 1.5D;
                });
    }

    @Override
    public int getTier() {
        EntityType<?> type = this.getType();
        if (type == ModEntities.COMPRESSED_PIG_3) {
            return 3;
        }
        return type == ModEntities.COMPRESSED_PIG_2 ? 2 : 1;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        if (getTier() == 3) {
            this.goalSelector.add(0, new MeleeAttackGoal(this, 1.0D, true));
            this.targetSelector.add(1, new RevengeGoal(this));
        }
    }

    @Override
    protected RegistryKey<LootTable> getLootTableId() {
        return EntityType.PIG.getLootTableId();
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        for (int i = 0; i < dropMultiplier(); i++) {
            super.dropLoot(source, causedByPlayer);
        }
        AnimalDrops.dropCompressedMeat(this, "raw_porkchop", getTier());
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if (getTier() >= 2) {
            // The requested tier-2 pig food: tier-2 compressed potato or carrot.
            return AnimalBreeding.isAnyCompressed(stack, "potato_2", "carrot_2");
        }
        return stack.isIn(ItemTags.PIG_FOOD)
                || AnimalBreeding.isAnyCompressed(stack, "potato_1", "carrot_1", "beetroot_1");
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        return other instanceof CompressedPigEntity
                && other.getType() == this.getType()
                && super.canBreedWith(other);
    }

    @Override
    public PigEntity createChild(ServerWorld world, PassiveEntity entity) {
        return switch (getTier()) {
            case 3 -> ModEntities.COMPRESSED_PIG_3.create(world);
            case 2 -> ModEntities.COMPRESSED_PIG_2.create(world);
            default -> ModEntities.COMPRESSED_PIG_1.create(world);
        };
    }
}
