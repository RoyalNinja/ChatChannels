package com.RoyalNinja.ChatChannels.Handlers;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.RoyalNinja.ChatChannels.Main;

public class GUIHandler {

	static FileConfiguration config = Main.plugin.getConfig();
	
	public static Inventory getChannelGUI() {
		Inventory inv = Bukkit.createInventory(null, config.getInt("channels.gui.size"), ChatColor.translateAlternateColorCodes('&', config.getString("channels.gui.name")));
		
		for (Channel ch : Channel.getAllChannels()) {
			
			ItemStack item = parseItemStack(config.getString("channels." + ch.getName() + ".icon.id"));
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("channels." + ch.getName() + ".icon.display")));
			ArrayList<String> lore = new ArrayList<String>();
			for (String line : config.getStringList("channels." + ch.getName() + ".icon.lore")) {
				lore.add(ChatColor.translateAlternateColorCodes('&',line));
			}
			meta.setLore(lore);
			item.setItemMeta(meta);
			inv.addItem(item);
		}
		
		return inv;
	}
	
	public static String getGUIName() {
		return config.getString("channels.gui.name");
	}
	
	@SuppressWarnings("deprecation")
	private static ItemStack parseItemStack(String id) {
		String[] ids = id.split(":");
		
		ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(ids[0])));
		item.setDurability((short) Integer.parseInt(ids[1])); 
		
		return item;
	}
	
}
