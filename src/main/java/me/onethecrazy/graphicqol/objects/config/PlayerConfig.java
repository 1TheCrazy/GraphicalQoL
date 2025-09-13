package me.onethecrazy.graphicqol.objects.config;

public class PlayerConfig {

    public EffectConfig effectConfig;
    public ParticleConfig particleConfig;
    public GeneralPlayerConfig generalConfig;

    public PlayerConfig() {
        effectConfig = new EffectConfig();
        particleConfig = new ParticleConfig();
        generalConfig = new GeneralPlayerConfig();
    }
}