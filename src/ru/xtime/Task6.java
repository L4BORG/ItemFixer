package ru.xtime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Task6 implements Runnable {
	public void run() {
	    String message = ChatColor.translateAlternateColorCodes('&', "&6[ItemFixer] &cНайдено новое обновление! &7// &cNew update found");
	    String message2 = ChatColor.translateAlternateColorCodes('&', "&6[ItemFixer] &chttp://rubukkit.org/threads/119485/");
	    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    console.sendMessage(message);
	    console.sendMessage(message2);
		for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.hasPermission("itemfixer.checkupdate")) {
				p.sendMessage(ChatColor.GOLD + "[ItemFixer]" + ChatColor.RED +"Найдено новое обновление!");
				p.sendMessage(ChatColor.GOLD + "[ItemFixer]" + ChatColor.RED +"http://rubukkit.org/threads/119485/");
				}
		}
	}
}