package com.compressedanimals.mixin;

import com.compressedanimals.entity.CompressedAnimal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Gives tier 3 animals longer arms: extend their attack box so they can hit a
 * target without the target having to stand inside their (already huge) body.
 */
@Mixin(MobEntity.class)
public abstract class Tier3AttackReachMixin {
    @Inject(method = "getAttackBox", at = @At("RETURN"), cancellable = true)
    private void compressedAnimals$extendTier3AttackBox(CallbackInfoReturnable<Box> cir) {
        if ((Object) this instanceof CompressedAnimal animal && animal.getTier() == 3) {
            cir.setReturnValue(cir.getReturnValue().expand(4.0D, 1.5D, 4.0D));
        }
    }
}
