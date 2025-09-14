package me.onethecrazy.graphicqol.mixin.client;

import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    // ---
    // For Low Fire
    // ---
    @Redirect(method="renderFireOverlay",
            at= @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V")
    )
    private static void onTranslateFireOverlay(MatrixStack instance, float x, float y, float z){
        double yOffset = (double) GraphicQoLClient.clientConfig.playerConfig.generalConfig.lowFireShift / 10;

        instance.translate(x, y + yOffset, z);
    }
}
