package com.guedosha.simpleheal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HealCommand implements CommandExecutor {

    Plugin plugin = Simpleheal.getPlugin(Simpleheal.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (!p.hasPermission("Simpleheal.heal")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to use that command");
                return true;
            }

            if (args.length == 0) {
                p.sendMessage(ChatColor.GOLD + "You have been healed");
                p.setHealth(p.getMaxHealth());
            } else if (args.length == 1) {
                String playerName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);
                if (target == null) {
                    p.sendMessage(ChatColor.RED + "That player isn't online");
                } else {
                    p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + target.getName() + ChatColor.GOLD + " has been healed");
                    target.sendMessage(ChatColor.GOLD + "You have been healed");
                    target.setHealth(target.getMaxHealth());
                }
            } else {
                p.sendMessage(ChatColor.RED + "Usage: /heal or /heal <target>");
            }
        } else {
            if (args.length == 0) {
                plugin.getLogger().info(ChatColor.RED + "Usage: /heal <target>");
            } else if (args.length == 1) {
                String playerNameConsole = args[0];
                Player t = Bukkit.getServer().getPlayerExact(playerNameConsole);
                if (t == null) {
                    plugin.getLogger().info(ChatColor.RED + "That player isn't online");
                } else {
                    t.setHealth(20);
                    t.sendMessage(ChatColor.GOLD + "You have been healed");
                    plugin.getLogger().info(ChatColor.YELLOW + "" + ChatColor.BOLD + t.getName() + ChatColor.GOLD + " has been healed");
                }
            } else {
                plugin.getLogger().info(ChatColor.RED + "Usage: /heal <target>");
            }
        }
        return true;
    }
}
