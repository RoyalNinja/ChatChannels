package com.RoyalNinja.ChatChannels.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.RoyalNinja.ChatChannels.Handlers.Channel;
import com.RoyalNinja.ChatChannels.Handlers.PlayerManager;
import com.RoyalNinja.ChatChannels.Handlers.SettingsManager;

import net.md_5.bungee.api.ChatColor;

public class PlayerChat implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		e.setCancelled(true);
		
		PlayerManager pm = new PlayerManager();
		
		if (settings.getChannelData().getString(p.getName() + ".channel") == null) {
			for (Channel ch : Channel.getAllChannels()) {
				if (ch.isDefault()) {
					pm.setPlayerChannel(p, ch);
				}
			}
		}
		Channel playerChannel = pm.getPlayerChannel(p);
		
		for (Player inChannel : pm.getAllOnlinePlayersInChannel(playerChannel)) {
			if (p.getCustomName() == null) {
				inChannel.sendMessage(ChatColor.translateAlternateColorCodes('&', playerChannel.getPrefix() + " " + playerChannel.getPlayerColour() + p.getName() + playerChannel.getColour() + "> " + e.getMessage() + " " + playerChannel.getSuffix()));		
			}else {
				inChannel.sendMessage(ChatColor.translateAlternateColorCodes('&', playerChannel.getPrefix() + " " + playerChannel.getPlayerColour() + p.getCustomName() + playerChannel.getColour() + "> " + e.getMessage() + " " + playerChannel.getSuffix()));	
			}	
		}
		
	}

}
