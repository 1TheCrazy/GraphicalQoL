package me.onethecrazy.graphicqol.objects.config;

public class LightConfig {
    // Defaults
    public static final int DEFAULT_GAMMA_CORRECTION = 100;

    // Instance values
    public int gammaCorrection;

    public LightConfig(){
        gammaCorrection = DEFAULT_GAMMA_CORRECTION;
    }
}
