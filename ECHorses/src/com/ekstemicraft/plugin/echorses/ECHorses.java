package com.ekstemicraft.plugin.echorses;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public final class ECHorses extends JavaPlugin{

	public static HashMap<String, ArrayList<Block>> hashmap = new HashMap<String, ArrayList<Block>>();
	
	
	@Override
	public void onEnable() {
		//Enable message
		getLogger().info("Enabling ECHorses");
		
		
		
		
		//Enabling/registering Tags Listener
		PluginManager pm = Bukkit.getServer().getPluginManager();
				pm.registerEvents(new ECHorsesListeners(null), this);
					
	}
	
	@Override
	public void onDisable() {
		//Disable message
		getLogger().info("Disabling ECHorses");
		hashmap.clear();

	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("horseunclaim")){  //Player or console executes command
		String player = sender.getName();
	
			hashmap.put(player, null);
			sender.sendMessage(ChatColor.AQUA + "[ECHorses] " + ChatColor.GREEN + "Left click the horse to unclaim the horse.");
			//adding later? sender.sendMessage(ChatColor.AQUA + "[ECHorses] " + ChatColor.RED + "NOTE: Unclaiming a horse will remove its tamed status!");
			//Adding later? sender.sendMessage(ChatColor.AQUA + "[ECHorses] " + ChatColor.RED + "If you dont want to unclaim your horse, please execute /horseunclaim again!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("huc")){  //Player or console executes command
			
			String player = sender.getName();
				hashmap.put(player, null);
				sender.sendMessage(ChatColor.AQUA + "[ECHorses] " + ChatColor.GREEN + "Left click the horse to unclaim the horse.");
				//Adding later? sender.sendMessage(ChatColor.AQUA + "[ECHorses] " + ChatColor.RED + "NOTE: Unclaiming a horse will remove its tamed status!");
				//Adding later? sender.sendMessage(ChatColor.AQUA + "[ECHorses] " + ChatColor.RED + "If you dont want to unclaim your horse, please execute /horseunclaim again!");
				return true;
			}
	return false;
	}		
}	