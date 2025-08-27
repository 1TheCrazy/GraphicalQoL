package me.onethecrazy.graphicqol;

import me.onethecrazy.graphicqol.commands.Commands;
import me.onethecrazy.graphicqol.objects.config.Config;
import me.onethecrazy.graphicqol.util.FileUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class GraphicQoLClient implements ClientModInitializer {

	public static Config clientConfig;

	@Override
	public void onInitializeClient() {
		// Create Paths (only really needed on first startup)
		FileUtil.createPaths();

		// Load Config
		clientConfig = FileUtil.loadConfig();

		// Init command
		registerCommand();
	}

	public static void registerCommand(){
		Commands.initializeCommands();

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(Commands.GRAPHICQOL_COMMAND);
		});
	}

	public static void saveConfig(){
		FileUtil.writeConfig(clientConfig);
	}
}