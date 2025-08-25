package me.onethecrazy.graphicqol.objects.config;

public class EffectConfig {
    // Default values
    public static final boolean DEFAULT_DISABLE_BLINDESS = false;
    public static final boolean DEFAULT_DISABLE_DARKNESS = false;

    // Instance values
    public boolean disableBlindness;
    public boolean disableDarkness;

    public EffectConfig(){
        disableBlindness = DEFAULT_DISABLE_BLINDESS;
        disableDarkness = DEFAULT_DISABLE_DARKNESS;
    }
}
