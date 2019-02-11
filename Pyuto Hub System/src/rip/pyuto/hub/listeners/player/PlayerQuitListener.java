package rip.pyuto.hub.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import rip.pyuto.hub.Hub;

public class PlayerQuitListener implements Listener {

	private final Hub plugin;
	
	public PlayerQuitListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onQuit(PlayerQuitEvent event) {
		event.getPlayer().getInventory().clear();
	}
}