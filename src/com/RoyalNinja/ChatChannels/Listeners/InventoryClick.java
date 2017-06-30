package com.RoyalNinja.ChatChannels.Listeners;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.RoyalNinja.ChatChannels.Main;
import com.RoyalNinja.ChatChannels.Handlers.Channel;
import com.RoyalNinja.ChatChannels.Handlers.GUIHandler;
import com.RoyalNinja.ChatChannels.Handlers.PlayerManager;

import net.md_5.bungee.api.ChatColor;

public class InventoryClick implements Listener {
	
	FileConfiguration config = Main.plugin.getConfig();
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)) return;
		
		ItemStack current = e.getCurrentItem();
		
		if (current.hasItemMeta() && current.getItemMeta().hasDisplayName()) {
			if (e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', GUIHandler.getGUIName()))) {
				e.setCancelled(true);
				
				for (Channel ch : Channel.getAllChannels()) {
					
					if (ChatColor.translateAlternateColorCodes('&', ch.getIconDisplay()).equals(current.getItemMeta().getDisplayName())) {
						
						PlayerManager pm = new PlayerManager();
						
						if (!p.hasPermission(ch.getPermission())) {
							p.sendMessage(ChatColor.RED + "You do not have permission to join this channel.");
							return;
						}
						p.closeInventory();
						pm.setPlayerChannel(p, ch);
						p.sendMessage(ChatColor.GOLD + "Your channel has been switched to " + ChatColor.AQUA + ch.getName());
						
						return;
					}
					
				}
				
			}
		}

	}

}
