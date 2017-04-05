package com.robinmc.launcherx.utils;

import org.bukkit.configuration.file.FileConfiguration;

import com.robinmc.launcherx.Main;

public class Config {
	
	private static FileConfiguration config = null;
	
	public static FileConfiguration getConfig(){
		if (config == null)
			config = Main.getPlugin().getConfig();
		
		return config;
	}
	
	public static void reloadConfig(){
		Main.getPlugin().saveDefaultConfig();
		Main.getPlugin().reloadConfig();
		config = Main.getPlugin().getConfig();
	}

}
