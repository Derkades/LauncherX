package com.robinmc.launcherx.hooks.ncp;

import org.bukkit.entity.Player;

public interface NoCheatPlus {
	
	public void exempt(Player player);
	
	public void unexempt(Player player);

}
