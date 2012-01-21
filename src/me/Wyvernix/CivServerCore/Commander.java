package me.Wyvernix.CivServerCore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commander {

	public static void commandhub(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length > 0) {
			return;
		}
		
		if (sender instanceof Player) {				
			Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("civserver")) {
			player.sendMessage(ChatColor.AQUA + "Welcome to The Civilizations Server.");
			if (commandLabel.equalsIgnoreCase("cs")) {
			player.sendMessage("abbvr?");
		}
		}
		//if(commandLabel.equalsIgnoreCase("broadcaststop")) {
			//if(running == 1) {
				//Bukkit.getServer().getScheduler().cancelTask(tid);
				//Player player = (Player) sender;
				//player.sendMessage("Cancelled broadcasts");
			//} else {
			//	Player player = (Player) sender;
			//	player.sendMessage("They arn't running!");
			//}
		//} else if (commandLabel.equalsIgnoreCase("startbroadcast"))
		//	if(running == 1) {
		//		Player player = (Player) sender;
		//		player.sendMessage("They are still running!");
		//	} else {
				//start broadcast
				//running = 0
		//} 
		}
	}
}
