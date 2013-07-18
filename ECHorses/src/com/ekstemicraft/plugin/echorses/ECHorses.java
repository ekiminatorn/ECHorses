package com.ekstemicraft.plugin.echorses;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public final class ECHorses extends JavaPlugin{

	@Override
	public void onEnable() {
		//Enable message
		getLogger().info("Enabling ECHorses");
		
		//Enabling/registering Tags Listener
		PluginManager pm = Bukkit.getServer().getPluginManager();
				pm.registerEvents(new ECHorsesListeners(), this);
					
	}
	
	@Override
	public void onDisable() {
		//Disable message
		getLogger().info("Disabling ECHorses");

	}
		
}	