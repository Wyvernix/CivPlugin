package me.Wyvernix.CivServerBase;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
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
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("civserver")) {
			player.sendMessage(ChatColor.AQUA + "Yes, this is The Civilizations Server.");
		
		} else if (commandLabel.equalsIgnoreCase("cs")) {
			player.sendMessage(ChatColor.AQUA + "[ABBR] Yes, this is The Civilizations Server.");
		}
		return true;
	}
}
