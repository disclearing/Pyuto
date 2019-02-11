package rip.pyuto.hub.listeners.player.useless;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import rip.pyuto.hub.Hub;

public class PlayerPickupItemListener implements Listener {

	private final Hub plugin;

	public PlayerPickupItemListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPickup(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
}