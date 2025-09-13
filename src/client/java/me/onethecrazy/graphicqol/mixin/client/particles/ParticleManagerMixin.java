package me.onethecrazy.graphicqol.mixin.client.particles;

import me.onethecrazy.graphicqol.GraphicQoLClient;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {
    @Shadow protected ClientWorld world;
    @Shadow public abstract void addParticle(Particle particle);

    @Unique private static final Random random = new Random();

    @Inject(method="addBlockBreakingParticles", at=@At("HEAD"), cancellable = true)
    private void onAddBlockBreakingParticles(BlockPos pos, Direction direction, CallbackInfo ci){
        for(int i = 0; i < GraphicQoLClient.clientConfig.playerConfig.particleConfig.particleAmount; i++){
            callAddBlockBreakingParticles(pos, direction);
        }

        // Prevent default call
        ci.cancel();
    }

    @Inject(method="addBlockBreakParticles", at=@At("HEAD"), cancellable = true)
    private void onAddBlockBreakingParticles(BlockPos pos, BlockState state, CallbackInfo ci){
        for(int i = 0; i < GraphicQoLClient.clientConfig.playerConfig.particleConfig.particleAmount; i++){
            callAddBlockBreakingParticles(pos, state);
        }

        // Prevent default call
        ci.cancel();
    }

    // Default implementation of ParticleManager#addBlockBreakingParticles
    @Unique private void callAddBlockBreakingParticles(BlockPos pos, Direction direction){
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getRenderType() != BlockRenderType.INVISIBLE && blockState.hasBlockBreakParticles()) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            float f = 0.1F;
            Box box = blockState.getOutlineShape(world, pos).getBoundingBox();
            double d = i + random.nextDouble() * (box.maxX - box.minX - 0.2F) + 0.1F + box.minX;
            double e = j + random.nextDouble() * (box.maxY - box.minY - 0.2F) + 0.1F + box.minY;
            double g = k + random.nextDouble() * (box.maxZ - box.minZ - 0.2F) + 0.1F + box.minZ;
            if (direction == Direction.DOWN) {
                e = j + box.minY - 0.1F;
            }

            if (direction == Direction.UP) {
                e = j + box.maxY + 0.1F;
            }

            if (direction == Direction.NORTH) {
                g = k + box.minZ - 0.1F;
            }

            if (direction == Direction.SOUTH) {
                g = k + box.maxZ + 0.1F;
            }

            if (direction == Direction.WEST) {
                d = i + box.minX - 0.1F;
            }

            if (direction == Direction.EAST) {
                d = i + box.maxX + 0.1F;
            }

            addParticle(new BlockDustParticle(world, d, e, g, 0.0, 0.0, 0.0, blockState, pos).move(0.2F).scale(0.6F));
        }
    }

    @Unique private void callAddBlockBreakingParticles(BlockPos pos, BlockState state){
        if (!state.isAir() && state.hasBlockBreakParticles()) {
            VoxelShape voxelShape = state.getOutlineShape(world, pos);
            double d = 0.25;
            voxelShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
                double dx = Math.min(1.0, maxX - minX);
                double e = Math.min(1.0, maxY - minY);
                double f = Math.min(1.0, maxZ - minZ);
                int i = Math.max(2, MathHelper.ceil(dx / 0.25));
                int j = Math.max(2, MathHelper.ceil(e / 0.25));
                int k = Math.max(2, MathHelper.ceil(f / 0.25));

                for (int l = 0; l < i; l++) {
                    for (int m = 0; m < j; m++) {
                        for (int n = 0; n < k; n++) {
                            double g = (l + 0.5) / i;
                            double h = (m + 0.5) / j;
                            double o = (n + 0.5) / k;
                            double p = g * dx + minX;
                            double q = h * e + minY;
                            double r = o * f + minZ;
                            addParticle(new BlockDustParticle(this.world, pos.getX() + p, pos.getY() + q, pos.getZ() + r, g - 0.5, h - 0.5, o - 0.5, state, pos));
                        }
                    }
                }
            });
        }
    }
}
