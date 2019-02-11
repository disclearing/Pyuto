package rip.pyuto.hub.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import rip.pyuto.hub.Hub;

public class WeatherChangeListener implements Listener {

	private final Hub plugin;

	public WeatherChangeListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
}