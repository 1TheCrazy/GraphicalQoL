package me.onethecrazy.graphicqol.objects.config;

public class Config {
    public WorldConfig worldConfig;
    public PlayerConfig playerConfig;

    public Config() {
        worldConfig = new WorldConfig();
        playerConfig = new PlayerConfig();
    }
}
