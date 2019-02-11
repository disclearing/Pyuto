package rip.pyuto.hub.listeners.player.useless;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import rip.pyuto.hub.Hub;

public class PlayerDropItemListener implements Listener {

	private final Hub plugin;
	
	public PlayerDropItemListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
}