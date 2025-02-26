package com.guedosha.simpleheal.Util;

import com.guedosha.simpleheal.Simpleheal;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigUpdater {
    private final int currentVersion = 2;
    Plugin plugin = Simpleheal.getPlugin(Simpleheal.class);
    Configuration c = plugin.getConfig();

    private boolean remove_effects;

    private String no_permission;
    private String usage;
    private String player_offline;
    private String target_healed;
    private String caster_healed;

    public ConfigUpdater() {
        if (c.getInt("config-version")==1) {
            remove_effects=c.getBoolean("remove-effects");

            no_permission=c.getString("messages.no-permission");
            usage=c.getString("messages.usage");
            player_offline=c.getString("messages.player-offline");
            target_healed=c.getString("messages.target-healed");
            caster_healed=c.getString("messages.caster-healed");

            File newConfig = new File(plugin.getDataFolder()+"/config.yml");
            if (newConfig.delete()) {
                plugin.getConfig().options().copyDefaults();
                plugin.saveDefaultConfig();
                plugin.reloadConfig();

                c=plugin.getConfig();

                c.set("remove-effects",remove_effects);
                c.set("messages.no-permission",no_permission);
                c.set("messages.usage",usage);
                c.set("messages.player-offline",player_offline);
                c.set("messages.target-healed",target_healed);
                c.set("messages.caster-healed",caster_healed);

                c.set("config-version",2);

                plugin.saveConfig();
                plugin.reloadConfig();
                new ReloadHandler().reloadConfig();
            } else {
                plugin.getLogger().severe("Failed to update config to latest version. If you have the config open in a text editor, try closing it then restarting the server");
            }
        }
    }
}
