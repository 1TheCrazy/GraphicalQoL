package me.onethecrazy.graphicqol.objects.config;

public class WorldConfig {
    public FogConfig fogConfig;
    public LightConfig lightConfig;
    public WeatherConfig weatherConfig;

    public WorldConfig () {
        fogConfig = new FogConfig();
        lightConfig = new LightConfig();
        weatherConfig = new WeatherConfig();
    }
}
