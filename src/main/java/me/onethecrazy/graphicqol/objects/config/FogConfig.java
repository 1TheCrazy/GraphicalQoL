package me.onethecrazy.graphicqol.objects.config;

public class FogConfig {
    // Defaults
    public static final boolean DEFAULT_NETHER_FOG = false;
    public static final boolean DEFAULT_OVERWORLD_FOG = false;
    public static final boolean DEFAULT_END_FOG = false;
    public static final boolean DEFAULT_UNDERWATER_FOG = false;

    // Instance values
    public boolean disableNetherFog;
    public boolean disableOverworldFog;
    public boolean disableEndFog;
    public boolean disableUnderwaterFog;

    public FogConfig () {
        disableNetherFog = DEFAULT_NETHER_FOG;
        disableOverworldFog = DEFAULT_OVERWORLD_FOG;
        disableEndFog = DEFAULT_END_FOG;
        disableUnderwaterFog = DEFAULT_UNDERWATER_FOG;
    }
}
