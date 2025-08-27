package me.onethecrazy.graphicqol.mixin.client;

import com.mojang.blaze3d.buffers.Std140Builder;
import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {

    // Disables change in light when you receive the darkness effect
    @Inject(method = "getDarkness", at=@At("RETURN"), cancellable = true)
    void onGetDarkness(LivingEntity entity, float factor, float tickProgress, CallbackInfoReturnable<Float> cir){
        if(GraphicQoLClient.clientConfig.playerConfig.effectConfig.disableDarkness)
            cir.setReturnValue(0f);
    }

    // --- Gamma Adjustments ---

    @Redirect(
            method = "update(F)V",
            at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F")
    )
    private float tweakEffectiveGamma(float a, float b) {
        float base = Math.max(a, b);

        float mult = GraphicQoLClient.clientConfig.worldConfig.lightConfig.gammaCorrection / 100;

        return base * mult;
    }

    /*
    // Redirect night vision check to here
    @Redirect(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;hasStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z"
            )
    )
    private boolean onHasNightVisionCheck(ClientPlayerEntity instance, RegistryEntry effect) {
        if (effect == StatusEffects.NIGHT_VISION)
            return true;

        return MinecraftClient.getInstance().player.hasStatusEffect(effect);
    }

    // Redirect Night vision strength check to here;
    @Redirect(
            method = "update(F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/GameRenderer;getNightVisionStrength(Lnet/minecraft/entity/LivingEntity;F)F"
            )
    )
    private float onGetNightVisionStrength(LivingEntity entity, float tickDelta) {
        return GraphicQoLClient.clientConfig.worldConfig.lightConfig.gammaCorrection / 100f - 1;
    }*/
}
