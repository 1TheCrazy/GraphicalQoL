package me.onethecrazy.graphicqol;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.DoubleSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import me.onethecrazy.graphicqol.objects.config.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.client.gui.hud.InGameHud;
public class ConfigScreen {
    public static Screen getNewConfigScreen(Screen parent) {
        return
        YetAnotherConfigLib.createBuilder()
                .title(Text.literal("GraphicQoL Configuration"))
                .category(
                        ConfigCategory.createBuilder()
                                .name(Text.translatable("gui.graphical-qol.config.category.world"))
                                .group(
                                        OptionGroup.createBuilder()
                                                .name(Text.translatable("gui.graphical-qol.config.title.fog"))
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_nether_fog"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_nether_fog")))
                                                                .binding(
                                                                        FogConfig.DEFAULT_NETHER_FOG,
                                                                        () -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableNetherFog, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableNetherFog = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_overworld_fog"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_overworld_fog")))
                                                                .binding(
                                                                        FogConfig.DEFAULT_OVERWORLD_FOG,
                                                                        () -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableOverworldFog, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableOverworldFog = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_end_fog"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_end_fog")))
                                                                .binding(
                                                                        FogConfig.DEFAULT_OVERWORLD_FOG,
                                                                        () -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableEndFog, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableEndFog = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_underwater_fog"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_underwater_fog")))
                                                                .binding(
                                                                        FogConfig.DEFAULT_UNDERWATER_FOG,
                                                                        () -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableUnderwaterFog, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableUnderwaterFog = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_lava_fog"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_lava_fog")))
                                                                .binding(
                                                                        FogConfig.DEFAULT_LAVA_FOG,
                                                                        () -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableLavaFog, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disableLavaFog = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_powder_snow_fog"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_powder_snow_fog")))
                                                                .binding(
                                                                        FogConfig.DEFAULT_POWDER_SNOW_FOG,
                                                                        () -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disablePowderSnowFog, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.worldConfig.fogConfig.disablePowderSnowFog = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .build()
                                )
                                .group(
                                        OptionGroup.createBuilder()
                                                .name(Text.translatable("gui.graphical-qol.config.title.light"))
                                                .option(
                                                        Option.<Integer>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.gamma_correction"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.gamma_correction")))
                                                                .binding(
                                                                        LightConfig.DEFAULT_GAMMA_CORRECTION,
                                                                        () -> GraphicQoLClient.clientConfig.worldConfig.lightConfig.gammaCorrection, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.worldConfig.lightConfig.gammaCorrection = v // setter
                                                                )
                                                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                                        .range(-750, 1500)
                                                                        .step(10)
                                                                        .formatValue(v -> Text.literal(String.valueOf(v) + "%"))
                                                                )
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .category(
                        ConfigCategory.createBuilder()
                                .name(Text.translatable("gui.graphical-qol.config.category.player"))
                                .group(
                                        OptionGroup.createBuilder()
                                                .name(Text.translatable("gui.graphical-qol.config.title.effects"))
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_blindness"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_blindness")))
                                                                .binding(
                                                                        EffectConfig.DEFAULT_DISABLE_BLINDESS,
                                                                        () -> GraphicQoLClient.clientConfig.playerConfig.effectConfig.disableBlindness, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.playerConfig.effectConfig.disableBlindness = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.disable_darkness"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.disable_darkness")))
                                                                .binding(
                                                                        EffectConfig.DEFAULT_DISABLE_DARKNESS,
                                                                        () -> GraphicQoLClient.clientConfig.playerConfig.effectConfig.disableDarkness, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.playerConfig.effectConfig.disableDarkness = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .build()
                                )
                                .group(
                                        OptionGroup.createBuilder()
                                                .name(Text.translatable("gui.graphical-qol.config.title.particles"))
                                                .option(
                                                        Option.<Integer>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.particle_multiplier"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.particle_multiplier")))
                                                                .binding(
                                                                        ParticleConfig.DEFAULT_PARTICLE_AMOUNT,
                                                                        () -> GraphicQoLClient.clientConfig.playerConfig.particleConfig.particleAmount, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.playerConfig.particleConfig.particleAmount = v // setter
                                                                )
                                                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                                        .range(0, 100)
                                                                        .step(1)
                                                                        .formatValue(v -> Text.literal("×" + v ))
                                                                )
                                                                .build()
                                                )
                                                .build()
                                )
                                .group(
                                        OptionGroup.createBuilder()
                                                .name(Text.translatable("gui.graphical-qol.config.title.player_general"))
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.no_head_tilt"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.no_head_tilt")))
                                                                .binding(
                                                                        GeneralPlayerConfig.DEFAULT_HEAD_TILT,
                                                                        () -> GraphicQoLClient.clientConfig.playerConfig.generalConfig.noHeadTilt, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.playerConfig.generalConfig.noHeadTilt = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Boolean>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.no_pumpkin_blur"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.no_pumpkin_blur")))
                                                                .binding(
                                                                        GeneralPlayerConfig.DEFAULT_PUMPKIN_BLUR,
                                                                        () -> GraphicQoLClient.clientConfig.playerConfig.generalConfig.noPumpkinBlur, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.playerConfig.generalConfig.noPumpkinBlur = v // setter
                                                                )
                                                                .controller(TickBoxControllerBuilder::create)
                                                                .build()
                                                )
                                                .option(
                                                        Option.<Integer>createBuilder()
                                                                .name(Text.translatable("gui.graphical-qol.config.low_fire_shift"))
                                                                .description(OptionDescription.of(Text.translatable("gui.graphical-qol.config.description.low_fire_shift")))
                                                                .binding(
                                                                        GeneralPlayerConfig.DEFAULT_LOW_FIRE_SHIFT,
                                                                        () -> GraphicQoLClient.clientConfig.playerConfig.generalConfig.lowFireShift, // getter
                                                                        v  -> GraphicQoLClient.clientConfig.playerConfig.generalConfig.lowFireShift = v // setter
                                                                )
                                                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                                        .range(-10, 10)
                                                                        .step(1)
                                                                        .formatValue(v -> Text.of(String.format("%.1f", v / 10.0)))
                                                                )
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .save(GraphicQoLClient::saveConfig)
                .build()
                .generateScreen(parent);
    }
}
