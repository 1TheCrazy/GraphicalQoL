package me.onethecrazy.graphicqol;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;


public class ModMenuIntegration implements ModMenuApi {


    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // No IntelliJ, this cannot be replaced with a method reference
        return ConfigScreen::getNewConfigScreen;
    }
}
