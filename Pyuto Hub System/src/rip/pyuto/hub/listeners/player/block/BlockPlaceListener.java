package rip.pyuto.hub.listeners.player.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import rip.pyuto.hub.Hub;

public class BlockPlaceListener implements Listener {
	
	private final Hub plugin;
	
	public BlockPlaceListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlace(BlockPlaceEvent event) {
		event.setCancelled(true);
	}
}