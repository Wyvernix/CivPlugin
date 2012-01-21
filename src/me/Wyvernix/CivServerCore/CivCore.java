package me.Wyvernix.CivServerCore;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;


@SuppressWarnings("deprecation")
public class CivCore extends JavaPlugin {
	//BroadcastS
	public static int tid = 0;
	public static int currentLine = 0;
	public static int running = 1;
	public static long interval = 300;
	//BroadcastE
	
	public static CivCore plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final MyBlockListener blockListener = new MyBlockListener(this);
	public final PlayerListener onPlayerJoin = new PlayerListener(this);
	public final PlayerListener playerListener = new PlayerListener(this);
	
	
//STUPID \/
	public void write(String root, Object x) {
		Configuration CONFIG = load();
		CONFIG.setProperty(root, x);
		CONFIG.save();
	}

	public String read(String root) {
		Configuration CONFIG = load();
		return CONFIG.getString(root);
	}
	public boolean readBool(String root) {
		Configuration CONFIG = load();
		return CONFIG.getBoolean(root, false);
	}

	public Configuration load() {
		try {
			Configuration CONFIG = new Configuration(this.file);
			CONFIG.load();
			return CONFIG;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean canSet(Player p) {
		if (p == null) {
			return true;
		}
		if (!readBool("consoleOnly")) return p.isOp();
		return false;
	}
//STILL STUPID /\
	
	static String mainDirectory = "plugins/CivServer";
	File file = new File(mainDirectory + File.separator + "config.yml"); //ALSO STUPID
	File broadcast = new File(mainDirectory + File.separator + "broadcast.txt");
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " is now disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, this.blockListener, Event.Priority.High, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListener, Event.Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, this.onPlayerJoin, Event.Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_CHAT, this.playerListener, Event.Priority.Low, this);
		pm.registerEvent(Event.Type.PLAYER_LOGIN, this.playerListener, Event.Priority.Low, this);
		//BroadcastS
		new File(mainDirectory).mkdir();
		
		if (!this.broadcast.exists()) {
			try {
				this.broadcast.createNewFile();
				this.logger.info("[CivCore]:Broadcast: Generating broadcast file");
			} catch (Exception ex) {
			ex.printStackTrace();
			}
		}		
		
		tid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				try {
					me.Wyvernix.CivServerCore.Broadcast.broadcastMessages("plugins/CivServer/messages.txt");
				} catch (IOException e){
					//Catch Exception
				}
			}
		}, 0, interval * 20);
		//BroadcastE
		//CommandsS
		getCommand("civserver").setExecutor(new CommandExecutor() {
			public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
				if (args.length > 0) {
					return false;
				}
				
				if (sender instanceof Player) {				
				Player player = (Player) sender;
				if(commandLabel.equalsIgnoreCase("civserver")) {
					player.sendMessage(ChatColor.AQUA + "Welcome to The Civilizations Server.");
				} else {
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
			} else {
				sender.sendMessage("Hey there server, you are " + ChatColor.RED + "NOT" + ChatColor.WHITE + " a player.");
			}
			return true;
			}
		});
		//CommandsE
		PluginDescriptionFile pdffile = this.getDescription();
		this.logger.info(pdffile.getName() + " v" + pdffile.getVersion() + " is enabled.");
	}
	
}
