package me.Wyvernix.CivServerBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Reader extends CivBase {
	public static int currentLine = 0;
	
	public void run() {
		try {
			opList("plugins/Broadcast/messages.txt");
		} catch (IOException e) {
		}
	}
	
	public static void opList(String fileName) throws IOException {
		FileInputStream fs;
		fs = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));
		for(int i = 0; i < currentLine; ++i)
			br.readLine();
		String line = br.readLine();
		line = line.replaceAll("%f", ChatColor.WHITE + "");
		line = line.replaceAll("%e", ChatColor.YELLOW + "");
		line = line.replaceAll("%d", ChatColor.LIGHT_PURPLE + "");
		line = line.replaceAll("%a", ChatColor.GREEN + "");
		Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "[Broadcast] " + ChatColor.WHITE + line);
		LineNumberReader lnr = new LineNumberReader(new FileReader(new File(fileName)));		
		lnr.skip(Long.MAX_VALUE);
		int lastLine = lnr.getLineNumber();
		if(currentLine + 1 == lastLine + 1) {
			currentLine = 0;
		} else {
			currentLine++;
		}
	}
}
