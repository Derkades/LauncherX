package com.robinmc.launcherx;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {
	
	private static Main plugin;
	
	private static double UPWARD_VELOCITY;
	private static double FORWARD_VELOCITY;
	private static Material LAUNCHER_BOTTOM_MATERIAL;
	private static Material LAUNCHER_PLATE_MATERIAL;
	
	public static Main getPlugin(){
		return plugin;
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("launcherx").setExecutor(new ReloadCommand());
		
		super.saveDefaultConfig();
		
		//Initialise values from config.
		if (!loadValuesFromConfig()){
			getLogger().log(Level.WARNING, "Launcher bottom block material or launcher plate material is incorrect.");
		}
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
	
	public boolean loadValuesFromConfig(){
		UPWARD_VELOCITY = getConfig().getDouble("upward-velocity");
		FORWARD_VELOCITY = getConfig().getDouble("forward-velocity");
		LAUNCHER_BOTTOM_MATERIAL = Material.getMaterial(getConfig().getString("launcher-bottom-material"));
		LAUNCHER_PLATE_MATERIAL = Material.getMaterial(getConfig().getString("launcher-plate-material"));
		
		return LAUNCHER_BOTTOM_MATERIAL != null && LAUNCHER_PLATE_MATERIAL != null;
	}
	
	public void launch(Player player, double upwardVelocity, double forwardVelocity){
		player.setVelocity(player.getLocation().getDirection().multiply(forwardVelocity));
		player.setVelocity(new Vector(player.getVelocity().getX(), upwardVelocity, player.getVelocity().getZ()));
	}
	
}
