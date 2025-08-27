package me.onethecrazy.graphicqol.mixin.client;

import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {

    @Inject(method = "getFogColor", at = @At("RETURN"), cancellable = true)
    public void onApplyFog(Camera camera, float tickProgress, ClientWorld world, int viewDistance, float skyDarkness, boolean thick, CallbackInfoReturnable<Vector4f> cir){
        var dimension = world.getRegistryKey();
        var fogConfig= GraphicQoLClient.clientConfig.worldConfig.fogConfig;
        var effectConfig = GraphicQoLClient.clientConfig.playerConfig.effectConfig;
        var player = MinecraftClient.getInstance().player;
        // Can safely assert this here
        assert player != null;

        // --- Check effect fogs ---

        // Blindness
        if(player.hasStatusEffect(StatusEffects.BLINDNESS) && effectConfig.disableBlindness){
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }

        // Darkness (only disables the fog, light effect is disabled elsewhere)
        if(player.hasStatusEffect(StatusEffects.DARKNESS) && effectConfig.disableDarkness){
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }

        // Return here in order for fog override that were targeted for submission/environment to not overwrite effect fog overwrites (since effect fogs are generally way denser than submission/environmental fogs)
        if((player.hasStatusEffect(StatusEffects.BLINDNESS) && !effectConfig.disableBlindness) || (player.hasStatusEffect(StatusEffects.DARKNESS) && !effectConfig.disableDarkness))
            return;

        // --- Check submission fogs ---

        // Water
        if(camera.getSubmersionType() == CameraSubmersionType.WATER && fogConfig.disableUnderwaterFog){
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }

        // Powder Snow
        if(camera.getSubmersionType() == CameraSubmersionType.POWDER_SNOW && fogConfig.disablePowderSnowFog){
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }

        // Lava
        if(camera.getSubmersionType() == CameraSubmersionType.LAVA && fogConfig.disableLavaFog){
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }

        // Return here in order for fog override that were targeted for environment to not overwrite submission fog overwrites (since submission fogs are generally way denser than environmental fogs)
        if((camera.getSubmersionType() == CameraSubmersionType.WATER && !fogConfig.disableUnderwaterFog) || (camera.getSubmersionType() == CameraSubmersionType.LAVA && !fogConfig.disableLavaFog) || (camera.getSubmersionType() == CameraSubmersionType.POWDER_SNOW && !fogConfig.disablePowderSnowFog))
            return;

        // --- Check dimension fogs ---

        // Overworld
        if(dimension == World.OVERWORLD && fogConfig.disableOverworldFog) {
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }

        // Nether
        if(dimension == World.NETHER && fogConfig.disableNetherFog) {
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }

        // End
        if(dimension == World.END && fogConfig.disableEndFog) {
            cir.setReturnValue(new Vector4f(0, 0, 0, 0));
            return;
        }
    }
}
