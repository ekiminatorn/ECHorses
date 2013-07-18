package com.ekstemicraft.plugin.echorses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class ECHorsesListeners implements Listener {
		
@EventHandler
	public void horseEnter(VehicleEnterEvent event){
	
	if(event.getVehicle() instanceof Horse){

      EntityType entered = (EntityType)event.getEntered().getType();
      if(entered == EntityType.PLAYER){ //Playercheck
    	  
    	  Player p = (Player)event.getEntered();
    	  String playername = p.getName();
    	  @SuppressWarnings("unused")
		Server server = p.getServer(); //Not used for now
          Horse h = (Horse)event.getVehicle();
          AnimalTamer owner = h.getOwner();
          if(!(owner == null)){
        	  
        	  if(p.isOp() && p.hasPermission("echorse.override")){ //Overriding the protection if player is OP
        		  return;
        	  }
        	  if(h.getOwner().getName() == p.getName()){ //Checking if entering player is the owner
        	  return;
        	  }
        	  else {
        		  p.sendMessage(ChatColor.RED + "[ECHorses] This horse is owned by " + h.getOwner().getName());
        		  Bukkit.getLogger().info("[ECHorses]" + playername + "tried entering someone elses horse");
        		  event.setCancelled(true);
        		  return;
        	  }
          } 
       p.sendMessage("[ECHorses] This horse is untamed!"); 
      }	
		
	}
}

}