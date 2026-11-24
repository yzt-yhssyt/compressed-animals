package com.compressedanimals.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.Identifier;

public class CompressedIronGolemRenderer extends IronGolemEntityRenderer {
    private static final Identifier TEXTURE = Identifier.of(
            "compressed_animals",
            "textures/entity/iron_golem/compressed_iron_golem.png"
    );

    public CompressedIronGolemRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(IronGolemEntity entity) {
        return TEXTURE;
    }
}
