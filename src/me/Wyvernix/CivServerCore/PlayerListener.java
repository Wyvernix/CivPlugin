package me.Wyvernix.CivServerCore;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;


public class PlayerListener extends org.bukkit.event.player.PlayerListener {
	
	HashMap<Player, String> login = new HashMap<Player, String>();
	public static CivCore plugin;
	public PlayerListener(CivCore instance) {
		plugin = instance;
	}
	
	 public void onPlayerLogin(PlayerLoginEvent event){
			Player p = event.getPlayer();
			String name = p.getName();
			File file=new File(p.getWorld().getName() +"/players/" + name + ".dat");
					  boolean exists = file.exists();
					  if (!exists) {/* new player! */
						  if(login.containsKey(p)) return;
						  login.put(p,"true");
						  } else {
							  if(login.containsKey(p)) login.remove(p);
							  }
					  }
		public void onPlayerJoin(PlayerJoinEvent event){
			Player p = event.getPlayer();
			String isNewPlayer = login.remove(p);
			if (isNewPlayer == null) {/* Player not found in hashmap, aborting*/} else {//new player!
				System.out.println("[WelcomeNewPlayers] " + p.getName() + " has joined for the first time");
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					/* send message to all except new player */
			        if (p != player) player.sendMessage(ChatColor.GRAY + "[" + p.getName() + "]" + ChatColor.AQUA + " is new. Be sure to say hello!");
			      }
				/* send message to server */
				System.out.println(plugin.read("sendto.others").replace("%name", p.getName()).replaceAll("&[0-9a-fA-F]", ""));
				/* send message to new player */
				p.sendMessage("Welcome to The Civilizations Server, " + p.getDisplayName() + ". Be sure to resd the rules.");
				}
			}
		}

	


