package com.robinmc.launcherx.hooks.ncp;

import org.bukkit.entity.Player;

public class NoCheatPlusDisabled implements NoCheatPlus {

	@Override
	public void exempt(Player player) {}

	@Override
	public void unexempt(Player player) {}

}
