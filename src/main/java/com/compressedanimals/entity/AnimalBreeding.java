package com.compressedanimals.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public final class AnimalBreeding {
    private static final String COMPRESSED_FOODS = "compressed_foods";

    private AnimalBreeding() {
    }

    public static boolean isCompressed(ItemStack stack, String itemPath) {
        Item item = Registries.ITEM.get(Identifier.of(COMPRESSED_FOODS, itemPath));
        return item != Items.AIR && stack.isOf(item);
    }

    public static boolean isAnyCompressed(ItemStack stack, String... itemPaths) {
        for (String itemPath : itemPaths) {
            if (isCompressed(stack, itemPath)) {
                return true;
            }
        }
        return false;
    }
}
