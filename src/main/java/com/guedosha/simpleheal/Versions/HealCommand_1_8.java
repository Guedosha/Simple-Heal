package com.guedosha.simpleheal.Versions;

import com.guedosha.simpleheal.Util.ReloadHandler;
import org.bukkit.ChatColor;

import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;


public class HealCommand_1_8 extends HealCommand{

    public void heal(Player caster, Player target) {
        Configuration config = new ReloadHandler().getConfig();
        String targetHealed = ChatColor.translateAlternateColorCodes( '&',config.getString("messages.target-healed")).replace("%target%", target.getName()).replace("%caster%", caster.getName());
        String casterHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-healed")).replace("%target%", target.getName()).replace("%caster%", caster.getName());

        if (config.getBoolean("remove-effects")) cure(target);
        target.setHealth(target.getMaxHealth());
        target.sendMessage(targetHealed);
        if (!caster.getName().equals(target.getName())) caster.sendMessage(casterHealed);
    }

    public void heal(Player target, boolean consoleSender) {
        Configuration config = new ReloadHandler().getConfig();

        if (consoleSender) {
            String targetHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-healed")).replace("%target%", target.getName()).replace("%caster%", "Console");
            String casterHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-healed")).replace("%target%", target.getName()).replace("%caster%", "Console");

            if (config.getBoolean("remove-effects")) cure(target);
            target.setHealth(target.getMaxHealth());
            target.sendMessage(targetHealed);
            logger.sendMessage(casterHealed);
        } else {
            String targetHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-healed")).replace("%target%", target.getName()).replace("%caster%", target.getName());

            if (config.getBoolean("remove-effects")) cure(target);
            target.setHealth(target.getMaxHealth());
            target.sendMessage(targetHealed);
        }
    }
}
