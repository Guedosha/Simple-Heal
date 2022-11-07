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
        //checks if the sender is a player or not
        if (sender instanceof Player p) {
            if (args.length == 0) {
                p.sendMessage("You have been healed");
                p.setHealth(p.getMaxHealth());
            } else if (args.length == 1) {
                String playerName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);
                if (target == null) {
                    p.sendMessage("That Player Isn't Online");
                } else {
                    p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has been healed");
                    target.sendMessage(ChatColor.GOLD + "You have been healed");
                    target.setHealth(target.getMaxHealth());
                }
            } else {
                p.sendMessage("Usage: /heal ; /heal <target>");
            }
        //else, meaning if the sender isn't a player IE the console or a command block
        } else {
            if (args.length == 0) {
                plugin.getLogger().info("Usage: /heal <target>");
            } else if (args.length == 1) {
                String playerNameConsole = args[0];
                Player t = Bukkit.getServer().getPlayerExact(playerNameConsole);

                if (t == null) {
                    plugin.getLogger().info("That Player is not Online");
                } else {
                    t.setHealth(20);
                    t.sendMessage(ChatColor.GOLD + "You have been healed");
                    plugin.getLogger().info(t.getName() + " Has been healed");
                }
            } else {
                plugin.getLogger().info("Usage: /heal <target>");
            }
        }
        return true;
    }
}
