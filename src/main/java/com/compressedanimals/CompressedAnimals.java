package com.compressedanimals;

import com.compressedanimals.registry.HostileAnimalTargets;
import com.compressedanimals.registry.ModEntities;
import com.compressedanimals.registry.ModItemGroups;
import com.compressedanimals.registry.ModItems;
import com.compressedanimals.registry.ModSpawns;
import com.compressedanimals.registry.VanillaAnimalConverter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CompressedAnimals implements ModInitializer {
    public static final String MOD_ID = "compressed_animals";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        ModEntities.register();
        ModSpawns.register();
        HostileAnimalTargets.register();
        VanillaAnimalConverter.register();
        ModItems.register();
        ModItemGroups.register();
        LOGGER.info("Compressed Animals loaded: 13 entity types, 12 spawn eggs and 1 villager-summoned golem");
    }
}
