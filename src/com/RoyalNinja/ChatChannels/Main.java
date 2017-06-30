package com.RoyalNinja.ChatChannels;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.RoyalNinja.ChatChannels.Commands.ChannelCommands;
import com.RoyalNinja.ChatChannels.Handlers.Channel;
import com.RoyalNinja.ChatChannels.Handlers.SettingsManager;
import com.RoyalNinja.ChatChannels.Listeners.InventoryClick;
import com.RoyalNinja.ChatChannels.Listeners.PlayerChat;

public class Main extends JavaPlugin {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public static Plugin plugin;
	
	public void onEnable() {
		plugin = this;
		settings.setup(this);
		setupConfig();
		setupListeners();
		registerCommands();
		Channel.registerAllChannelsFromConfig();
	}
	
	public void registerCommands() {
    	getCommand("channel").setExecutor(new ChannelCommands());
    }
	void setupListeners() {
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
	}
	
	void setupConfig() {
		ArrayList<String> allChannels = new ArrayList<String>();
		ArrayList<String> englishLore = new ArrayList<String>();
		ArrayList<String> frenchLore = new ArrayList<String>();
		englishLore.add("&7Click to join the English Channel.");
		frenchLore.add("&7Click to join the French Channel.");
		allChannels.add("English");
		allChannels.add("French");
		getConfig().addDefault("channels.all-channels", allChannels);
		//English
		getConfig().addDefault("channels.english.prefix", "&7[&bE&7]");
		getConfig().addDefault("channels.english.suffix", "");
		getConfig().addDefault("channels.english.colour", "&7");
		getConfig().addDefault("channels.english.player-colour", "&e");
		getConfig().addDefault("channels.english.icon.id", "35:11");
		getConfig().addDefault("channels.english.icon.display", "&bEnglish Channel");
		getConfig().addDefault("channels.english.icon.lore", englishLore);
		getConfig().addDefault("channels.english.permission", "");
		getConfig().addDefault("channels.english.default", true);
		//French
		getConfig().addDefault("channels.french.prefix", "&7[&cF&7]");
		getConfig().addDefault("channels.french.suffix", "");
		getConfig().addDefault("channels.french.colour", "&7");
		getConfig().addDefault("channels.french.player-colour", "&e");
		getConfig().addDefault("channels.french.icon.id", "35:14");
		getConfig().addDefault("channels.french.icon.display", "&cFrench Channel");
		getConfig().addDefault("channels.french.icon.lore", frenchLore);
		getConfig().addDefault("channels.french.permission", "channel.french");	
		getConfig().addDefault("channels.french.default", false);
		//GUI
		getConfig().addDefault("channels.gui.size", 9);
		getConfig().addDefault("channels.gui.name", "&bChannels");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	
}
