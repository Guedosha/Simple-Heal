package com.guedosha.simpleheal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) { return false; }
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
                target.sendMessage("You have been healed");
                target.setHealth(target.getMaxHealth());
            }
        } else {
            p.sendMessage("Usage: /heal ; /heal <target>");
        }
        return true;
    }
}
