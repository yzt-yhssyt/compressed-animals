package com.compressedanimals.registry;

import com.compressedanimals.CompressedAnimals;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModItems {
    public static final Item COMPRESSED_COW_SPAWN_EGG_1 = spawnEgg(
            "compressed_cow_spawn_egg_1", ModEntities.COMPRESSED_COW_1, 0x6B4A2F, 0xC9A66B);
    public static final Item COMPRESSED_COW_SPAWN_EGG_2 = spawnEgg(
            "compressed_cow_spawn_egg_2", ModEntities.COMPRESSED_COW_2, 0x3B2A1A, 0xFFD700);
    public static final Item COMPRESSED_COW_SPAWN_EGG_3 = spawnEgg(
            "compressed_cow_spawn_egg_3", ModEntities.COMPRESSED_COW_3, 0x111111, 0xFFAA00);

    public static final Item COMPRESSED_SHEEP_SPAWN_EGG_1 = spawnEgg(
            "compressed_sheep_spawn_egg_1", ModEntities.COMPRESSED_SHEEP_1, 0xE6E6E6, 0x8C8C8C);
    public static final Item COMPRESSED_SHEEP_SPAWN_EGG_2 = spawnEgg(
            "compressed_sheep_spawn_egg_2", ModEntities.COMPRESSED_SHEEP_2, 0xD9D9D9, 0xFFD700);
    public static final Item COMPRESSED_SHEEP_SPAWN_EGG_3 = spawnEgg(
            "compressed_sheep_spawn_egg_3", ModEntities.COMPRESSED_SHEEP_3, 0x9A9A9A, 0xFFAA00);

    public static final Item COMPRESSED_PIG_SPAWN_EGG_1 = spawnEgg(
            "compressed_pig_spawn_egg_1", ModEntities.COMPRESSED_PIG_1, 0xF0A5A2, 0x8B4513);
    public static final Item COMPRESSED_PIG_SPAWN_EGG_2 = spawnEgg(
            "compressed_pig_spawn_egg_2", ModEntities.COMPRESSED_PIG_2, 0xF0A5A2, 0xFFD700);
    public static final Item COMPRESSED_PIG_SPAWN_EGG_3 = spawnEgg(
            "compressed_pig_spawn_egg_3", ModEntities.COMPRESSED_PIG_3, 0xB06A68, 0xFFAA00);

    public static final Item COMPRESSED_CHICKEN_SPAWN_EGG_1 = spawnEgg(
            "compressed_chicken_spawn_egg_1", ModEntities.COMPRESSED_CHICKEN_1, 0xE7E7E7, 0xD4A017);
    public static final Item COMPRESSED_CHICKEN_SPAWN_EGG_2 = spawnEgg(
            "compressed_chicken_spawn_egg_2", ModEntities.COMPRESSED_CHICKEN_2, 0xE7E7E7, 0xFFD700);
    public static final Item COMPRESSED_CHICKEN_SPAWN_EGG_3 = spawnEgg(
            "compressed_chicken_spawn_egg_3", ModEntities.COMPRESSED_CHICKEN_3, 0xB0B0B0, 0xFFAA00);

    private ModItems() {
    }

    private static Item spawnEgg(String name, EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor) {
        return Registry.register(
                Registries.ITEM,
                CompressedAnimals.id(name),
                new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Settings()));
    }

    public static void register() {
        // Static initialisation above performs the registrations.
    }
}
