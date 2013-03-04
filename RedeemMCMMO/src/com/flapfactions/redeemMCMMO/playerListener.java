package com.flapfactions.redeemMCMMO;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class playerListener implements Listener {
	
	public static RedeemMCMMO plugin;
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		plugin = RedeemMCMMO.instance;
		Player player = event.getPlayer();
		File playerDat = new File("world/players/"+ player.getName() + ".dat");
		if(!playerDat.exists()) {
			String playerName = player.getName();
			int startupAmount = plugin.getConfig().getInt("startupAmount");
			if(startupAmount == 0) {
				//do nothing if startupAmount is 0
			} else {
				plugin.getConfig().set(playerName + ".credits", startupAmount);
				plugin.saveConfig();
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		plugin = RedeemMCMMO.instance;
		Player player = event.getPlayer();
		int credits = plugin.getConfig().getInt(player.getName() + ".credits");
		if(credits == 0) {
			//do nothing!
		} else {
			if(plugin.getConfig().getBoolean("joinMessage") == true) {
			player.sendMessage(ChatColor.GREEN + "You have " + ChatColor.GOLD + credits + ChatColor.GREEN + " MCMMO credits.");
			player.sendMessage(ChatColor.GREEN + "Use " + ChatColor.GOLD + "/redeem <skill> <amount>" + ChatColor.GREEN + " to redeem them.");
			}
		}
	}

}
