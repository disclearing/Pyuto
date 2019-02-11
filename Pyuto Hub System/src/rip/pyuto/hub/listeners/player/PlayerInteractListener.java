package rip.pyuto.hub.listeners.player;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import rip.pyuto.hub.Hub;

public class PlayerInteractListener implements Listener {

	private final Hub plugin;

	public PlayerInteractListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL) {
			event.setCancelled(true);
		}
		if (player.getItemInHand() != null
				&& (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if (player.getItemInHand().getType() == Material.SLIME_BALL) {
				player.openInventory(plugin.getSelectorManager().selector);
				player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
			}
		}
	}
}