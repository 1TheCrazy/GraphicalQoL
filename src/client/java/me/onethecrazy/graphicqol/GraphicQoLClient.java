package me.onethecrazy.graphicqol;

import me.onethecrazy.graphicqol.objects.config.Config;
import me.onethecrazy.graphicqol.util.FileUtil;
import net.fabricmc.api.ClientModInitializer;

public class GraphicQoLClient implements ClientModInitializer {
	public static Config clientConfig;

	@Override
	public void onInitializeClient() {
		// Create Paths (only really needed on first startup)
		FileUtil.createPaths();

		// Load Config
		clientConfig = FileUtil.loadConfig();
	}

	public static void saveConfig(){
		FileUtil.writeConfig(clientConfig);
	}
}