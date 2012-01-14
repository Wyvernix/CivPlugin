package me.Wyvernix.CivServerBase;

import java.util.logging.Logger;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CivBase extends JavaPlugin {
	public static int tid = 0;
	public static int running = 1;
	
	public static CivBase plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final MyBlockListener blockListener = new MyBlockListener(this);
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " is now disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, this.blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListener, Event.Priority.Normal, this);
		PluginDescriptionFile pdffile = this.getDescription();
		
		
		
		
		
		this.logger.info(pdffile.getName() + " V" + pdffile.getVersion() + " is enabled.");
	}		
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("stopbroadcast")) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.AQUA + "Yes, this is The Civilizations Server.");
		
		} else if (commandLabel.equalsIgnoreCase("cs")) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.AQUA + "[ABBR] Yes, this is The Civilizations Server.");
		}
		return true;
	}	
}
