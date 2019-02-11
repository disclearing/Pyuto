package rip.pyuto.hub.listeners.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import rip.pyuto.hub.Hub;

public class InventoryOpenListener implements Listener {

	private final Hub plugin;

	public InventoryOpenListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onOpen(InventoryOpenEvent event) {
		if (event.getInventory().getHolder() == null) return;
		event.setCancelled(true);
	}
}