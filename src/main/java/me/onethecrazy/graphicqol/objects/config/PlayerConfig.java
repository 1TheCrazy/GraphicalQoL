package me.onethecrazy.graphicqol.objects.config;

public class PlayerConfig {

    public EffectConfig effectConfig;
    public ParticleConfig particleConfig;

    public PlayerConfig() {
        effectConfig = new EffectConfig();
        particleConfig = new ParticleConfig();
    }
}