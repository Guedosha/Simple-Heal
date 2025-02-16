package com.guedosha.simpleheal.Versions;

import com.guedosha.simpleheal.ReloadHandler;
import com.guedosha.simpleheal.Simpleheal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

public class HealCommand_1_16 implements CommandExecutor {

    Plugin plugin = Simpleheal.getPlugin(Simpleheal.class);
    ConsoleCommandSender logger = Bukkit.getConsoleSender();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Configuration config = new ReloadHandler().getConfig();
        if (sender instanceof Player p) {
            if (!p.hasPermission("simpleheal.use")) { p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no-permission").replace("%caster%", p.getName()).replace("%target%", ""))); return true; }
            if (args.length == 0) {
                heal(p, false);
            } else if (args.length == 1) {
                String target = args[0];
                Player t = Bukkit.getPlayerExact(target);

                if (t==null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-offline").replace("%target%", target).replace("%caster%", p.getName())));
                } else {
                    heal(p, t);
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.usage").replace("%caster%", p.getName()).replace("%target%", p.getName())));
            }
        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1) {
                String target = args[0];
                Player t = Bukkit.getPlayerExact(target);

                if (t==null) logger.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.player-offline").replace("%target%", target).replace("%caster%", "Console")));
                else heal(t, true);
            } else logger.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("messages.usage").replace("%caster%", "").replace("%target%", "Console")));
        }
        return true;
    }

    public void heal(Player caster, Player target) {
        Configuration config = new ReloadHandler().getConfig();
        String targetHealed = ChatColor.translateAlternateColorCodes( '&',config.getString("messages.target-healed")).replace("%target%", target.getName()).replace("%caster%", caster.getName());
        String casterHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-healed")).replace("%target%", target.getName()).replace("%caster%", caster.getName());

        if (config.getBoolean("remove-effects")) cure(target);
        target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        target.sendMessage(targetHealed);
        if (!caster.getName().equals(target.getName())) caster.sendMessage(casterHealed);
    }

    public void heal(Player target, boolean consoleSender) {
        Configuration config = new ReloadHandler().getConfig();

        if (consoleSender) {
            String targetHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-healed")).replace("%target%", target.getName()).replace("%caster%", "Console");
            String casterHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.caster-healed")).replace("%target%", target.getName()).replace("%caster%", "Console");

            if (config.getBoolean("remove-effects")) cure(target);
            target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            target.sendMessage(targetHealed);
            logger.sendMessage(casterHealed);
        } else {
            String targetHealed = ChatColor.translateAlternateColorCodes('&', config.getString("messages.target-healed")).replace("%target%", target.getName()).replace("%caster%", target.getName());

            if (config.getBoolean("remove-effects")) cure(target);
            target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            target.sendMessage(targetHealed);
        }
    }

    public void cure(Player target) {
        for (PotionEffect effect : target.getActivePotionEffects()) {
            target.removePotionEffect(effect.getType());
        }
    }
}
