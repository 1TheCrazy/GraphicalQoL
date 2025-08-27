package me.onethecrazy.graphicqol.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.onethecrazy.graphicqol.ConfigScreen;
import me.onethecrazy.graphicqol.ModMenuIntegration;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class Commands {
    public static LiteralArgumentBuilder<FabricClientCommandSource> GRAPHICQOL_COMMAND;

    public static void initializeCommands(){
        GRAPHICQOL_COMMAND = ClientCommandManager.literal("graphicqol")
                        .executes(context -> {
                            var client = MinecraftClient.getInstance();

                            client.send(() -> {
                                Screen parent = client.currentScreen;
                                client.setScreen(ConfigScreen.getNewConfigScreen(parent));
                            });

                            return 1;
                        });
    }
}
