package com.robinmc.launcherx.hooks.ncp;

import org.bukkit.entity.Player;

import fr.neatmonster.nocheatplus.hooks.NCPExemptionManager;

public class NoCheatPlusEnabled implements NoCheatPlus {

	@Override
	public void exempt(Player player) {
		NCPExemptionManager.exemptPermanently(player);
	}

	@Override
	public void unexempt(Player player) {
		NCPExemptionManager.unexempt(player);
	}

}
