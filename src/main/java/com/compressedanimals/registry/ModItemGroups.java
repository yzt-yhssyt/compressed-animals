package com.compressedanimals.registry;

import com.compressedanimals.CompressedAnimals;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public final class ModItemGroups {
    private ModItemGroups() {
    }

    public static void register() {
        Registry.register(
                Registries.ITEM_GROUP,
                CompressedAnimals.id("compressed_animals"),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemGroup.compressed_animals"))
                        .icon(() -> new ItemStack(ModItems.COMPRESSED_COW_SPAWN_EGG_3))
                        .entries((context, entries) -> {
                            entries.add(ModItems.COMPRESSED_COW_SPAWN_EGG_1);
                            entries.add(ModItems.COMPRESSED_COW_SPAWN_EGG_2);
                            entries.add(ModItems.COMPRESSED_COW_SPAWN_EGG_3);
                            entries.add(ModItems.COMPRESSED_SHEEP_SPAWN_EGG_1);
                            entries.add(ModItems.COMPRESSED_SHEEP_SPAWN_EGG_2);
                            entries.add(ModItems.COMPRESSED_SHEEP_SPAWN_EGG_3);
                            entries.add(ModItems.COMPRESSED_PIG_SPAWN_EGG_1);
                            entries.add(ModItems.COMPRESSED_PIG_SPAWN_EGG_2);
                            entries.add(ModItems.COMPRESSED_PIG_SPAWN_EGG_3);
                            entries.add(ModItems.COMPRESSED_CHICKEN_SPAWN_EGG_1);
                            entries.add(ModItems.COMPRESSED_CHICKEN_SPAWN_EGG_2);
                            entries.add(ModItems.COMPRESSED_CHICKEN_SPAWN_EGG_3);
                        })
                        .build());
    }
}
