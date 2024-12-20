package com.github.jaeukkang12.afashscore;

import com.github.jaeukkang12.afashscore.afashsplugin.AfashsPluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AfashsCore extends JavaPlugin {

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {
        AfashsPluginManager.unRegisterAllPlugin();
    }
}
