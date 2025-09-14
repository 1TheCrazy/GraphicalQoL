package me.onethecrazy.graphicqol.mixin.client;

import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    // ---
    // For Pumpkin Blur
    // ---
    @Inject(method="renderOverlay", at=@At("HEAD"), cancellable = true)
    private void onRenderOverlay(DrawContext context, Identifier texture, float opacity, CallbackInfo ci){
        if(texture.getPath().endsWith("pumpkinblur.png") && GraphicQoLClient.clientConfig.playerConfig.generalConfig.noPumpkinBlur)
            ci.cancel();
    }
}
