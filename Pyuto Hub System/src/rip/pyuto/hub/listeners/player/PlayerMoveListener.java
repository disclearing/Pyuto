package rip.pyuto.hub.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import rip.pyuto.hub.Hub;

public class PlayerMoveListener implements Listener {

	private final Hub plugin;

	public PlayerMoveListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (player.getLocation().getY() <= 15 || player.getLocation().getY() >= 125) {
			player.teleport(new Location(Bukkit.getWorld(player.getWorld().getName()), 0.5, 32.5, 0.5, 0, 0));
		}
	}
}