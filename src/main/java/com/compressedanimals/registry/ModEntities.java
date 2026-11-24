package com.compressedanimals.registry;

import com.compressedanimals.CompressedAnimals;
import com.compressedanimals.entity.CompressedChickenEntity;
import com.compressedanimals.entity.CompressedCowEntity;
import com.compressedanimals.entity.CompressedIronGolemEntity;
import com.compressedanimals.entity.CompressedPigEntity;
import com.compressedanimals.entity.CompressedSheepEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModEntities {
    public static final EntityType<CompressedCowEntity> COMPRESSED_COW_1 = register(
            "compressed_cow_1",
            EntityType.Builder.<CompressedCowEntity>create(CompressedCowEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.4F));
    public static final EntityType<CompressedCowEntity> COMPRESSED_COW_2 = register(
            "compressed_cow_2",
            EntityType.Builder.<CompressedCowEntity>create(CompressedCowEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.4F));
    public static final EntityType<CompressedCowEntity> COMPRESSED_COW_3 = register(
            "compressed_cow_3",
            EntityType.Builder.<CompressedCowEntity>create(CompressedCowEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.4F));

    public static final EntityType<CompressedSheepEntity> COMPRESSED_SHEEP_1 = register(
            "compressed_sheep_1",
            EntityType.Builder.<CompressedSheepEntity>create(CompressedSheepEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.3F));
    public static final EntityType<CompressedSheepEntity> COMPRESSED_SHEEP_2 = register(
            "compressed_sheep_2",
            EntityType.Builder.<CompressedSheepEntity>create(CompressedSheepEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.3F));
    public static final EntityType<CompressedSheepEntity> COMPRESSED_SHEEP_3 = register(
            "compressed_sheep_3",
            EntityType.Builder.<CompressedSheepEntity>create(CompressedSheepEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 1.3F));

    public static final EntityType<CompressedPigEntity> COMPRESSED_PIG_1 = register(
            "compressed_pig_1",
            EntityType.Builder.<CompressedPigEntity>create(CompressedPigEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 0.9F));
    public static final EntityType<CompressedPigEntity> COMPRESSED_PIG_2 = register(
            "compressed_pig_2",
            EntityType.Builder.<CompressedPigEntity>create(CompressedPigEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 0.9F));
    public static final EntityType<CompressedPigEntity> COMPRESSED_PIG_3 = register(
            "compressed_pig_3",
            EntityType.Builder.<CompressedPigEntity>create(CompressedPigEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.9F, 0.9F));

    public static final EntityType<CompressedChickenEntity> COMPRESSED_CHICKEN_1 = register(
            "compressed_chicken_1",
            EntityType.Builder.<CompressedChickenEntity>create(CompressedChickenEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.4F, 0.7F));
    public static final EntityType<CompressedChickenEntity> COMPRESSED_CHICKEN_2 = register(
            "compressed_chicken_2",
            EntityType.Builder.<CompressedChickenEntity>create(CompressedChickenEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.4F, 0.7F));
    public static final EntityType<CompressedChickenEntity> COMPRESSED_CHICKEN_3 = register(
            "compressed_chicken_3",
            EntityType.Builder.<CompressedChickenEntity>create(CompressedChickenEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.4F, 0.7F));

    public static final EntityType<CompressedIronGolemEntity> COMPRESSED_IRON_GOLEM = register(
            "compressed_iron_golem",
            EntityType.Builder.<CompressedIronGolemEntity>create(CompressedIronGolemEntity::new, SpawnGroup.MISC)
                    .dimensions(1.4F, 2.7F));

    private ModEntities() {
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, CompressedAnimals.id(name), builder.build(name));
    }

    public static void register() {
        FabricDefaultAttributeRegistry.register(COMPRESSED_COW_1, CompressedCowEntity.createAttributes(1));
        FabricDefaultAttributeRegistry.register(COMPRESSED_COW_2, CompressedCowEntity.createAttributes(2));
        FabricDefaultAttributeRegistry.register(COMPRESSED_COW_3, CompressedCowEntity.createAttributes(3));
        FabricDefaultAttributeRegistry.register(COMPRESSED_SHEEP_1, CompressedSheepEntity.createAttributes(1));
        FabricDefaultAttributeRegistry.register(COMPRESSED_SHEEP_2, CompressedSheepEntity.createAttributes(2));
        FabricDefaultAttributeRegistry.register(COMPRESSED_SHEEP_3, CompressedSheepEntity.createAttributes(3));
        FabricDefaultAttributeRegistry.register(COMPRESSED_PIG_1, CompressedPigEntity.createAttributes(1));
        FabricDefaultAttributeRegistry.register(COMPRESSED_PIG_2, CompressedPigEntity.createAttributes(2));
        FabricDefaultAttributeRegistry.register(COMPRESSED_PIG_3, CompressedPigEntity.createAttributes(3));
        FabricDefaultAttributeRegistry.register(COMPRESSED_CHICKEN_1, CompressedChickenEntity.createAttributes(1));
        FabricDefaultAttributeRegistry.register(COMPRESSED_CHICKEN_2, CompressedChickenEntity.createAttributes(2));
        FabricDefaultAttributeRegistry.register(COMPRESSED_CHICKEN_3, CompressedChickenEntity.createAttributes(3));
        FabricDefaultAttributeRegistry.register(COMPRESSED_IRON_GOLEM, CompressedIronGolemEntity.createAttributes());
    }
}
