package me.Wyvernix.CivServerBase;


import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerListener extends org.bukkit.event.player.PlayerListener {

  private File playerDirectory;

  public boolean isNewPlayer(Player player) {
    final File playerData = new File(playerDirectory + File.separator + player.getName() + ".dat");
    return !playerData.exists();
  }

  public void onPlayerJoin(PlayerJoinEvent event) {
    if (isNewPlayer(event.getPlayer())) {
    	Player player = Bukkit.getPlayer("Shady1765");
    	//Player player = Bukkit.getOnlinePlayers()
    	//if (player.isOp() == true) {
    		player.sendMessage(ChatColor.LIGHT_PURPLE + "A new player, " + ChatColor.GRAY + "[" + player.getName() + "]" + ChatColor.LIGHT_PURPLE + ", has joined the game.");
    	
    	//}
    }
  }

}
