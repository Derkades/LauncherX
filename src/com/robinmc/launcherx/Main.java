package com.robinmc.launcherx;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.robinmc.launcherx.utils.Config;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	private static Plugin plugin;
	
	private static double UPWARD_VELOCITY;
	private static double FORWARD_VELOCITY;
	private static Material LAUNCHER_BOTTOM_MATERIAL;
	private static Material LAUNCHER_PLATE_MATERIAL;
	
	public static Plugin getPlugin(){
		return plugin;
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("launcherx").setExecutor(new ReloadCommand());
		
		//Initialise values from config.
		if (!loadValuesFromConfig()){
			getLogger().log(Level.WARNING, "Launcher bottom block material or launcher plate material is incorrect.");
		}
		
		Config.reloadConfig();
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location location = player.getLocation();
		Material block = location.getBlock().getRelative(0, -1, 0).getType();
		Material plate = location.getBlock().getType();
		  
		if (block == LAUNCHER_BOTTOM_MATERIAL && plate == LAUNCHER_PLATE_MATERIAL) {
			launch(player, UPWARD_VELOCITY, FORWARD_VELOCITY);
		}
	}
	
	public static boolean loadValuesFromConfig(){
		UPWARD_VELOCITY = Config.getConfig().getDouble("upward-velocity");
		FORWARD_VELOCITY = Config.getConfig().getDouble("forward-velocity");
		LAUNCHER_BOTTOM_MATERIAL = Material.getMaterial(Config.getConfig().getString("launcher-bottom-material"));
		LAUNCHER_PLATE_MATERIAL = Material.getMaterial(Config.getConfig().getString("launcher-plate-material"));
		
		return LAUNCHER_BOTTOM_MATERIAL != null && LAUNCHER_PLATE_MATERIAL != null;
	}
	
	public static void launch(Player player, double upwardVelocity, double forwardVelocity){
		player.setVelocity(player.getLocation().getDirection().multiply(forwardVelocity));
		player.setVelocity(new Vector(player.getVelocity().getX(), upwardVelocity, player.getVelocity().getZ()));
	}
	
	public static class ReloadCommand implements CommandExecutor {
		
		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
			if (args.length == 1 && (args[1].equalsIgnoreCase("rl") || args[1].equalsIgnoreCase("reload"))){
				Config.reloadConfig();
				
				//Re-initialise values from config, because it has been updated.
				if (loadValuesFromConfig()){
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

}
