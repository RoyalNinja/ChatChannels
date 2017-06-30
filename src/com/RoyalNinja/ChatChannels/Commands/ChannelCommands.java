package com.RoyalNinja.ChatChannels.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.RoyalNinja.ChatChannels.Handlers.GUIHandler;

import net.md_5.bungee.api.ChatColor;

public class ChannelCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("channel")) {
			
			if (!sender.hasPermission("channel.select")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command.");
				return true;
			}
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Only players can select their chat channel.");
				return true;
			}
			if (args.length > 1) {
				sender.sendMessage(ChatColor.RED + "Invalid format! /channel");
				return true;
			}
			Player p = (Player) sender;
			
			p.openInventory(GUIHandler.getChannelGUI());
			
			return true;
			
		}
		
		return true;
	}

}
