package me.onethecrazy.graphicqol.mixin.client.particles;

import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Shadow protected abstract Particle spawnParticle(ParticleEffect parameters, boolean force, boolean canSpawnOnMinimal, double x, double y, double z, double velocityX, double velocityY, double velocityZ);

    @Unique private static final Random random = new Random();

    @Redirect(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;ZZDDDDDD)V",
        at=@At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/render/WorldRenderer;spawnParticle(Lnet/minecraft/particle/ParticleEffect;ZZDDDDDD)Lnet/minecraft/client/particle/Particle;"
        )
    )
    private Particle onAddParticle(WorldRenderer instance, ParticleEffect parameters, boolean force, boolean canSpawnOnMinimal, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        var n = GraphicQoLClient.clientConfig.playerConfig.particleConfig.particleAmount;

        Particle particle = null;

        for(int i = 0; i < n; i++){
            particle = spawnParticle(parameters, force, false, x + nextOffset(), y + nextOffset(), z + nextOffset(), velocityX * nextOffset(), velocityY * nextOffset(), velocityZ * nextOffset());
        }

        return particle;
    }

    // Random offset between -1 and 1
    @Unique private static double nextOffset(){
        return random.nextDouble() * 2 - 1;
    }
}
