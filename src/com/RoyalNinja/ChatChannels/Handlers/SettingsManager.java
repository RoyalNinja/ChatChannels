package com.RoyalNinja.ChatChannels.Handlers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {
	
	static SettingsManager instance = new SettingsManager();
	  Plugin p;
	  
	  
	  FileConfiguration ChannelData;
	  File ChannelDataFile;
	  
	  public static SettingsManager getInstance() {
	    return instance;
	  }
	  
	  public void setup(Plugin p) {
	    if (!p.getDataFolder().exists()) {
	      p.getDataFolder().mkdir();
	    }
	    
	    ChannelDataFile = new File(p.getDataFolder(), "ChannelData.yml");
	    

	    if (!ChannelDataFile.exists()) {
	        try {
	          this.ChannelDataFile.createNewFile();
	        }
	        catch (IOException e) {
	          Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create ChannelData.yml!");
	        }
	    }
	    
	    ChannelData = YamlConfiguration.loadConfiguration(this.ChannelDataFile);
	    
	  }
	  

	  public FileConfiguration getChannelData() {
		return ChannelData;
	  }
	  

	  public void saveChannelData() {
		    try {
		    	ChannelData.save(ChannelDataFile);
		    }
		    catch (IOException e) {
		      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save ChannelData.yml!");
		    }
	  }
}
