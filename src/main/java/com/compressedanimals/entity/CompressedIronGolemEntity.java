package com.compressedanimals.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

/**
 * Tier-1 compressed iron golem.
 *
 * <p>It has no building pattern and no spawn egg: the only way to obtain one is the
 * 10% chance roll in the villager golem-summon reaction (see VillagerEntityMixin).</p>
 */
public class CompressedIronGolemEntity extends IronGolemEntity implements CompressedAnimal {
    private static final int MIN_IRON_BLOCKS = 8;
    private static final int MAX_IRON_BLOCKS = 16;

    public CompressedIronGolemEntity(EntityType<? extends IronGolemEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return IronGolemEntity.createIronGolemAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3600.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 90.0D);
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    protected void dropLoot(DamageSource source, boolean causedByPlayer) {
        int count = MIN_IRON_BLOCKS + this.random.nextInt(MAX_IRON_BLOCKS - MIN_IRON_BLOCKS + 1);
        this.dropStack(new ItemStack(Items.IRON_BLOCK, count));
    }
}
