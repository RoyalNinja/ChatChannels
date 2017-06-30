package com.RoyalNinja.ChatChannels.Handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {

	SettingsManager settings = SettingsManager.getInstance();
	
	public void setPlayerChannel(Player p, Channel ch) {
		settings.getChannelData().set(p.getName() + ".channel", ch.getName());
		settings.saveChannelData();
	}
	
	public Channel getPlayerChannel(Player p) {
		for (Channel ch : Channel.getAllChannels()) {
			if (settings.getChannelData().getString(p.getName() + ".channel").equals(ch.getName())) 
				return ch;
		}
		return null;
	}
	
	public List<Player> getAllOnlinePlayersInChannel(Channel ch) {
		List<Player> players = new ArrayList<Player>();
		for (Player online : Bukkit.getServer().getOnlinePlayers()) {
			if (settings.getChannelData().getString(online.getName() + ".channel").equals(ch.getName())) {
				players.add(online);
			}
		}
		return players;
	}
	
}
