package me.onethecrazy.graphicqol.mixin.client;

import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.client.render.WeatherRendering;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WeatherRendering.class)
public class WeatherRenderingMixin {
    // ---
    // For Weather Particles
    // ---
    @ModifyArg(
            method = "renderPieces(Lnet/minecraft/client/render/VertexConsumer;Ljava/util/List;Lnet/minecraft/util/math/Vec3d;FIF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumer;color(I)Lnet/minecraft/client/render/VertexConsumer;"
            ),
            index = 0
    )
    private int redirectColor(int argb) {
        float configIntensity = GraphicQoLClient.clientConfig.worldConfig.weatherConfig.weatherOpacity / 10f;

        return ColorHelper.getWhite(configIntensity);
    }
}
