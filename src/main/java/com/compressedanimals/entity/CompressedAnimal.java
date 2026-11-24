package com.compressedanimals.entity;

/**
 * Implemented by all compressed animal entities. The tier is encoded by the
 * entity type (one type per animal and tier), so it is always persisted by
 * vanilla entity saving with no extra NBT or mixin.
 */
public interface CompressedAnimal {
    int getTier();

    default int dropMultiplier() {
        return switch (getTier()) {
            case 3 -> 270;
            case 2 -> 27;
            default -> 6;
        };
    }
}
