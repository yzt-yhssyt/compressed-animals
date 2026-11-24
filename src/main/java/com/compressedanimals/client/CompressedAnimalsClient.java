package com.compressedanimals.client;

import com.compressedanimals.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;

public final class CompressedAnimalsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.COMPRESSED_COW_1, CowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_COW_2, CowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_COW_3, CowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_SHEEP_1, SheepEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_SHEEP_2, SheepEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_SHEEP_3, SheepEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_PIG_1, PigEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_PIG_2, PigEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_PIG_3, PigEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_CHICKEN_1, ChickenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_CHICKEN_2, ChickenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_CHICKEN_3, ChickenEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.COMPRESSED_IRON_GOLEM, CompressedIronGolemRenderer::new);
    }
}
