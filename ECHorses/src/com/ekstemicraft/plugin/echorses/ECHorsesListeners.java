package com.ekstemicraft.plugin.echorses;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTameEvent;
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
        		  p.sendMessage(ChatColor.AQUA + "[ECHorses]" + ChatColor.RED + " This horse is owned by " + h.getOwner().getName());
        		  Bukkit.getLogger().info("[ECHorses] " + playername + "tried entering someone elses horse");
        		  event.setCancelled(true);
        		  return;
        	  }
          } 
       p.sendMessage(ChatColor.AQUA + "[ECHorses]" + ChatColor.GREEN + " This horse is untamed!"); 
      }	
		
	}
}
@EventHandler
public void horseTameEvent(EntityTameEvent event){
     
	 if(event.getEntityType() == EntityType.HORSE){
		 Player p = (Player)event.getOwner();
		 p.sendMessage(ChatColor.AQUA + "[ECHorses]" + ChatColor.GREEN + " You have succesfully protected this horse!");
		 
	 }	
}
@EventHandler
public void horseDamageByEntity(EntityDamageByEntityEvent event){
	if(event.getEntityType() == EntityType.HORSE){
		Horse h = (Horse)event.getEntity();
		
		//Handle arrow shots from players
		if(event.getDamager().getType() == EntityType.ARROW){ //Arrowcheck
			Arrow a = (Arrow)event.getDamager();
			if(a.getShooter() instanceof Player){
				if(h.getOwner() == null){
					return;
				}
				event.setCancelled(true);
				return;
			}
		}
		
		//Handle melee attack direct from player
		if(event.getDamager().getType() == EntityType.PLAYER){ //Playercheck
			Player p = (Player)event.getDamager();
             
			if(p.isOp() || p.hasPermission("echorse.override") || h.getOwner() == null){ //Op & permission check & if horse isnt tamed.
				return;
			}
			 if(!(h.getOwner().getName() == p.getName())){ //If its not the horse owner, cancel the event
				 event.setCancelled(true);
				 p.sendMessage(ChatColor.AQUA + "[ECHorses]" + ChatColor.RED + " You dont have permission to hurt " + h.getOwner().getName() + "s horse!" );
				 return;
			 }
		}		
	return;	
	}	
}






}