package rip.pyuto.hub.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;

import rip.pyuto.hub.Hub;

public class VehicleEnterListener implements Listener {

	private final Hub plugin;
	
	public VehicleEnterListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEnter(VehicleEnterEvent event) {
		event.setCancelled(true);
	}
}