package me.Wyvernix.CivServerBase;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class MyBlockListener extends BlockListener {
	
	public static CivBase plugin;
	public static Material[] placedBlacklist = {Material.BEDROCK};
	public static Material[] destroyedBlacklist = {Material.BEDROCK};
	
	public MyBlockListener(CivBase instance) {
		plugin = instance;
	}
	
	public void onBlockPlace (BlockPlaceEvent event) {
		Material block = event.getBlock().getType(); 
		Player player = event.getPlayer();
		for(Material blockedBlock : placedBlacklist) {
			if(blockedBlock == block)
				player.sendMessage(ChatColor.YELLOW + "I'm afraid i can't let you do that " + player.getDisplayName());
				player.sendMessage(ChatColor.YELLOW + "Only operators can place that block.");
				event.setCancelled(true);
		}
	}
	
	public void onBlockBreak (BlockBreakEvent event) {
		Material block = event.getBlock().getType(); 
		Player player = event.getPlayer();
		for(Material blockedBlock : destroyedBlacklist) {
			if(blockedBlock == block)
				player.sendMessage(ChatColor.YELLOW + "I'm afraid i can't let you do that " + player.getDisplayName());
				player.sendMessage(ChatColor.YELLOW + "Only operators can destroy that block.");
				event.setCancelled(true);
		}
	}	
}
