package me.onethecrazy.graphicqol;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.SliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import me.onethecrazy.graphicqol.objects.config.EffectConfig;
import me.onethecrazy.graphicqol.objects.config.FogConfig;
import me.onethecrazy.graphicqol.objects.config.LightConfig;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.createBuilder()
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
                                                            () -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableNetherFog, // getter
                                                            v  -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableNetherFog = v // setter
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
                                                                () -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableOverworldFog, // getter
                                                                v  -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableOverworldFog = v // setter
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
                                                                () -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableEndFog, // getter
                                                                v  -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableEndFog = v // setter
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
                                                                () -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableUnderwaterFog, // getter
                                                                v  -> GraphicQoLClient.ClientConfig.worldConfig.fogConfig.disableUnderwaterFog = v // setter
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
                                                                () -> GraphicQoLClient.ClientConfig.worldConfig.lightConfig.gammaCorrection, // getter
                                                                v  -> GraphicQoLClient.ClientConfig.worldConfig.lightConfig.gammaCorrection = v // setter
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
                                                                () -> GraphicQoLClient.ClientConfig.playerConfig.effectConfig.disableBlindness, // getter
                                                                v  -> GraphicQoLClient.ClientConfig.playerConfig.effectConfig.disableBlindness = v // setter
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
                                                                () -> GraphicQoLClient.ClientConfig.playerConfig.effectConfig.disableDarkness, // getter
                                                                v  -> GraphicQoLClient.ClientConfig.playerConfig.effectConfig.disableDarkness = v // setter
                                                        )
                                                        .controller(TickBoxControllerBuilder::create)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
                )
                .build()
                .generateScreen(parent);
    }
}
