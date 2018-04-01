package com.robinmc.launcherx.hooks.aac;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.konsolas.aac.api.PlayerViolationEvent;

public class AACListener implements Listener {
	
	public List<UUID> playersInAir = new ArrayList<>();
	
	@EventHandler
	public void onPlayerViolation(PlayerViolationEvent event) {
		if (playersInAir.contains(event.getPlayer().getUniqueId())) {
			event.setCancelled(true);
		}
	}

}
