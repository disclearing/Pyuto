package rip.pyuto.hub.listeners.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import rip.pyuto.hub.Hub;

public class EntityDamageListener implements Listener {

	private final Hub plugin;
	
	public EntityDamageListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDamage(EntityDamageEvent event) {
		event.setCancelled(true);
	}
}