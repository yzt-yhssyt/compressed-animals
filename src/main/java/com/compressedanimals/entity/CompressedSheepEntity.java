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
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class CompressedSheepEntity extends SheepEntity implements CompressedAnimal {
    public CompressedSheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world);
        if (!world.isClient) {
            AnimalHealth.randomize(this, getTier(), baseHealth(getTier()));
        }
    }

    private static double baseHealth(int tier) {
        return switch (tier) {
            case 3 -> 58320.0D;
            case 2 -> 5832.0D;
            default -> 216.0D;
        };
    }

    public static DefaultAttributeContainer.Builder createAttributes(int tier) {
        return SheepEntity.createSheepAttributes()
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
        if (type == ModEntities.COMPRESSED_SHEEP_3) {
            return 3;
        }
        return type == ModEntities.COMPRESSED_SHEEP_2 ? 2 : 1;
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
    public RegistryKey<LootTable> getLootTableId() {
        if (this.isSheared()) {
            return EntityType.SHEEP.getLootTableId();
        }
        return super.getLootTableId();
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        for (int i = 0; i < dropMultiplier(); i++) {
            super.dropLoot(source, causedByPlayer);
        }
        AnimalDrops.dropCompressedMeat(this, "raw_mutton", getTier());
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        if (getTier() >= 2) {
            return AnimalBreeding.isCompressed(stack, "bread_2");
        }
        return stack.isIn(ItemTags.SHEEP_FOOD) || AnimalBreeding.isCompressed(stack, "bread_1");
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        return other instanceof CompressedSheepEntity
                && other.getType() == this.getType()
                && super.canBreedWith(other);
    }

    @Override
    public SheepEntity createChild(ServerWorld world, PassiveEntity entity) {
        CompressedSheepEntity child = switch (getTier()) {
            case 3 -> ModEntities.COMPRESSED_SHEEP_3.create(world);
            case 2 -> ModEntities.COMPRESSED_SHEEP_2.create(world);
            default -> ModEntities.COMPRESSED_SHEEP_1.create(world);
        };
        if (child != null) {
            if (entity instanceof CompressedSheepEntity other && this.getRandom().nextBoolean()) {
                child.setColor(other.getColor());
            } else {
                child.setColor(this.getColor());
            }
        }
        return child;
    }
}
