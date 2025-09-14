package me.onethecrazy.graphicqol.objects.config;



public class LightConfig {
    // Defaults
    public static final int DEFAULT_GAMMA_CORRECTION = 100;
    public static final int DEFAULT_COLOR_TINT = 0x00000000;

    // Instance values
    public int gammaCorrection;
    public int colorTint;

    public LightConfig(){
        gammaCorrection = DEFAULT_GAMMA_CORRECTION;
        colorTint = DEFAULT_COLOR_TINT;
    }
}
