package rip.pyuto.hub.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import rip.pyuto.hub.Hub;

public class ExplosionPrimeListener implements Listener {

	private final Hub plugin;

	public ExplosionPrimeListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPrime(ExplosionPrimeEvent event) {
		event.setCancelled(true);
	}
}