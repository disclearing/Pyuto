package rip.pyuto.hub.listeners.player.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import rip.pyuto.hub.Hub;

public class BlockBreakListener implements Listener {

	private final Hub plugin;
	
	public BlockBreakListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}
}