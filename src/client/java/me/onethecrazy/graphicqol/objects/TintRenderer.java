package me.onethecrazy.graphicqol.objects;

import me.onethecrazy.graphicqol.GraphicQoL;
import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class TintRenderer {
    private static final Identifier TINT_ID = Identifier.of(GraphicQoL.MOD_ID, "global_tint");

    public static void init(){
        HudElementRegistry.addLast(TINT_ID, TintRenderer::renderTint);
    }

    // Overlay a quad on the whole screen
    public static void renderTint(DrawContext ctx, RenderTickCounter tickCounter) {
        int w = ctx.getScaledWindowWidth();
        int h = ctx.getScaledWindowHeight();

        var configColor = GraphicQoLClient.clientConfig.worldConfig.lightConfig.colorTint;

        ctx.fill(0, 0, w, h, configColor);
    }
}
