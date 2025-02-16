package com.guedosha.simpleheal;

import com.guedosha.simpleheal.Versions.HealCommand_1_16;
import com.guedosha.simpleheal.Versions.HealCommand_1_8;
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

        String version = Bukkit.getVersion();
        System.out.println(version);


        if (version.contains("MC: 1.8")) {
            getCommand("heal").setExecutor(new HealCommand_1_8());
        } else if (version.contains("MC: 1.12.2") || version.contains("MC: 1.16") || version.contains("MC: 1.17") || version.contains("MC: 1.18") || version.contains("MC: 1.19") || version.contains("MC: 1.20") || version.contains("MC: 1.21")) {
            getCommand("heal").setExecutor(new HealCommand_1_16());
        } else {
            getLogger().severe("Unsuported version. Shutting down plugin");
            this.setEnabled(false);
        }

        getCommand("reload").setExecutor(new ReloadHandler());
    }

}
