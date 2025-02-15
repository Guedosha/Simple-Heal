package com.guedosha.simpleheal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Simpleheal extends JavaPlugin {

    private static Simpleheal plugin;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[SimpleHeal] &fSuccessfully Loaded Config!"));

        getCommand("heal").setExecutor(new HealCommand());
        getCommand("reload").setExecutor(new ReloadHandler());
    }

}
