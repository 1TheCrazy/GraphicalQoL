package me.onethecrazy.graphicqol.mixin.client;

import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    // ---
    // For Fullbright
    // ---
    @Inject(method="getNightVisionStrength", at = @At("HEAD"), cancellable = true)
    private static void onGetNightVisionStrength(LivingEntity entity, float tickProgress, CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(1f);
    }

    // ---
    // For Damage Head Tilt
    // ---
    @Inject(method="tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void onGetNightVisionStrength(MatrixStack matrices, float tickProgress, CallbackInfo ci){
        if(GraphicQoLClient.clientConfig.playerConfig.generalConfig.noHeadTilt)
            ci.cancel();
    }
}
