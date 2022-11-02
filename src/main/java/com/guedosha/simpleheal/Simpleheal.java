package com.guedosha.simpleheal;

import org.bukkit.plugin.java.JavaPlugin;

public final class Simpleheal extends JavaPlugin {

    private static Simpleheal plugin;

    @Override
    public void onEnable() {
        getCommand("heal").setExecutor(new HealCommand());
    }

    public static Simpleheal getPlugin() {
        return plugin;
    }

}
