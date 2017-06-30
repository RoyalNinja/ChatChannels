package com.RoyalNinja.ChatChannels.Handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.RoyalNinja.ChatChannels.Main;

public class Channel {
	
	static List<Channel> channels = new ArrayList<Channel>();
	
	Boolean isDefault;
	String name, prefix, suffix, colour, playerColour, iconId, iconDisplay, permission;
	List<String> iconLore;
	
	public Channel(String name, String prefix, String suffix, String colour, String playerColour, String iconId, String iconDisplay, List<String> iconLore, String permission, Boolean isDefault) {
		this.name = name;
		this.prefix = prefix;
		this.suffix = suffix;
		this.colour = colour;
		this.playerColour = playerColour;
		this.iconId = iconId;
		this.iconDisplay = iconDisplay;
		this.permission = permission;
		this.iconLore = iconLore;	
		this.isDefault = isDefault;	
	}

	/*
	 * Getters
	 */
	public String getName() {
		return name;
	}
	public String getPrefix() {
		return prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public String getColour() {
		return colour;
	}
	public String getPlayerColour() {
		return playerColour;
	}
	public String getIconID() {
		return iconId;
	}
	public String getIconDisplay() {
		return iconDisplay;
	}
	public String getPermission() {
		return permission;
	}
	public List<String> getLore() {
		return iconLore;
	}
	public Boolean isDefault() {
		return isDefault;
	}

	public static List<Channel> getAllChannels() {
		return channels;
	}
	
	/*
	 * Setters
	 */
	public void setName(String name) {
		this.name = name;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public void setPlayerColour(String playerColour) {
		this.playerColour = playerColour;
	}
	public void setIconID(String iconId) {
		this.iconId = iconId;
	}
	public void setIconDisplay(String iconDisplay) {
		this.iconDisplay = iconDisplay;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public void setLore(List<String> lore) {
		this.iconLore = lore;
	}
	public void setDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	
	/*
	 * Global
	 */
	
	public void saveToConfig() {

		FileConfiguration config = Main.plugin.getConfig();
		
		config.set("channels." + name + ".prefix", prefix);
		config.set("channels." + name + ".suffix", suffix);
		config.set("channels." + name + ".colour", colour);
		config.set("channels." + name + ".player-colour", playerColour);
		config.set("channels." + name + ".icon.id", iconId);
		config.set("channels." + name + ".icon.display", iconDisplay);
		config.set("channels." + name + ".icon.lore", iconLore);
		config.set("channels." + name + ".permission", permission);
		config.set("channels." + name + ".default", isDefault);
		Main.plugin.saveConfig();
	}
	
	public void registerChannel() {
		channels.add(this);
	}
	
	public static void registerAllChannelsFromConfig() {
		FileConfiguration config = Main.plugin.getConfig();
		
		List<String> configChannels = config.getStringList("channels.all-channels");
		
		for (String channel : configChannels) {
			String prefix = config.getString("channels." + channel + ".prefix");
			String suffix = config.getString("channels." + channel + ".suffix");
			String colour = config.getString("channels." + channel + ".colour");
			String playerColour = config.getString("channels." + channel + ".player-colour");
			String iconId = config.getString("channels." + channel + ".icon.id");
			String iconDisplay = config.getString("channels." + channel + ".icon.display");
			String permission = config.getString("channels." + channel + ".permission");
			List<String> iconLore = config.getStringList("channels." + channel + ".icon.lore");
			Boolean isDefault = config.getBoolean("channels." + channel + ".default");
			
			Channel newChannel = new Channel(channel, prefix, suffix, colour, playerColour, iconId, iconDisplay, iconLore, permission, isDefault);
			newChannel.registerChannel();
		}
	}
	
}
