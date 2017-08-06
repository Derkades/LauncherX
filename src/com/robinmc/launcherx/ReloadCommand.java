package com.robinmc.launcherx;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class ReloadCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if (args.length == 1 && (args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reload"))){
			Main.getPlugin().reloadConfig();
			
			//Re-initialise values from config, because it has been updated.
			if (Main.getPlugin().loadValuesFromConfig()){
				sender.sendMessage(ChatColor.DARK_AQUA + "The config has been reloaded.");
			} else {
				sender.sendMessage("Launcher bottom block material or launcher plate material is incorrect.");
			}
			
			return true;
		} else {
			return false;
		}
	}

}
