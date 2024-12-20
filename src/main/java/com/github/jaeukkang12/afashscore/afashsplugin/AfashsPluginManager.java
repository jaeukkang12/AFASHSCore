package com.github.jaeukkang12.afashscore.afashsplugin;

import com.github.jaeukkang12.afashscore.afashsplugin.impl.AfashsPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class AfashsPluginManager {
    private static final Map<JavaPlugin, Set<AfashsPlugin>> starlyPlugins = new HashMap<>();

    public static void registerStarlyPlugin(JavaPlugin plugin, Class<? extends AfashsPlugin> clazz) {
        AfashsPlugin instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("StarlyPlugin 객체를 생성하는 도중 오류가 발생했습니다.");
        }

        try {
            instance.onRegister();
        } catch (NoSuchMethodError e) {
            throw new IllegalArgumentException("StarlyPlugin를 상속받지 않은 객체입니다.");
        }

        Set<AfashsPlugin> classes = starlyPlugins.get(plugin);
        classes.add(instance);

        starlyPlugins.put(plugin, classes);
    }

    public static void registerStarlyPlugin(JavaPlugin plugin, Set<Class<? extends AfashsPlugin>> classes) {
        classes.forEach(clazz -> {
            try {
                registerStarlyPlugin(plugin, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void unRegisterAllPlugin() {
        starlyPlugins.keySet().forEach(AfashsPluginManager::unRegisterStarlyPlugin);
    }

    public static void unRegisterStarlyPlugin(JavaPlugin plugin) {
        Set<AfashsPlugin> instances = starlyPlugins.get(plugin);
        instances.forEach(AfashsPlugin::onUnRegister);

        starlyPlugins.remove(plugin);
    }
}

