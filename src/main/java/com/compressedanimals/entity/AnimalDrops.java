package com.compressedanimals.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public final class AnimalDrops {
    private static final String COMPRESSED_FOODS = "compressed_foods";

    private AnimalDrops() {
    }

    /**
     * Tier 1 animals can only drop tier 1 compressed meat. Tier 2 animals can
     * drop tier 1 meat normally and tier 2 meat at a deliberately low rate.
     */
    public static void dropCompressedMeat(LivingEntity entity, String meatBase, int tier) {
        if (tier == 1) {
            if (entity.getRandom().nextFloat() < 0.30F) {
                drop(entity, meatBase + "_1");
            }
            return;
        }

        if (tier >= 3) {
            // A tier 3 animal is a serious kill: always give both compressed meats.
            drop(entity, meatBase + "_1");
            drop(entity, meatBase + "_2");
            return;
        }

        if (entity.getRandom().nextFloat() < 0.30F) {
            drop(entity, meatBase + "_1");
        }
        if (entity.getRandom().nextFloat() < 0.05F) {
            drop(entity, meatBase + "_2");
        }
    }

    private static void drop(LivingEntity entity, String itemPath) {
        Item item = Registries.ITEM.get(Identifier.of(COMPRESSED_FOODS, itemPath));
        if (item != Items.AIR) {
            entity.dropStack(new ItemStack(item));
        }
    }
}
