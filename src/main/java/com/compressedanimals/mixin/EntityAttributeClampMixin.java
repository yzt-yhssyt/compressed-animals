package com.compressedanimals.mixin;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Vanilla caps max health at 1024 through ClampedEntityAttribute. The requested
 * tier-2 animals and the compressed iron golem are far above that, so let only the
 * max-health attribute keep large values.
 */
@Mixin(ClampedEntityAttribute.class)
public class EntityAttributeClampMixin {
    @Inject(method = "clamp", at = @At("HEAD"), cancellable = true)
    private void compressedAnimals$allowLargeMaxHealth(double value, CallbackInfoReturnable<Double> cir) {
        if ((Object) this == EntityAttributes.GENERIC_MAX_HEALTH.value()) {
            cir.setReturnValue(value);
        }
    }
}
