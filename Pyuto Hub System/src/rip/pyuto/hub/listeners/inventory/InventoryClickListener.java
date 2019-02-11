package rip.pyuto.hub.listeners.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import rip.pyuto.hub.Hub;

public class InventoryClickListener implements Listener {

	private final Hub plugin;

	public InventoryClickListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if (event.getCurrentItem() == null || (event.getCurrentItem().getType() == Material.AIR))
			return;
		if (event.getCurrentItem().getType() == Material.FIRE) {
			plugin.sendToServer(player, "PRISON");
			player.sendMessage(
					ChatColor.GREEN + "Attempting to Send you to the " + ChatColor.DARK_GREEN.toString()
							+ ChatColor.BOLD + "Flame Realm" + ChatColor.GREEN + " that has " + ChatColor.WHITE
							+ plugin.getOnlineCount("PRISON") + " player(s)" + ChatColor.GREEN + " connected.");
			player.closeInventory();
		}
	}
}