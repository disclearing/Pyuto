package rip.pyuto.hub.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rip.pyuto.hub.Hub;
import rip.pyuto.hub.utils.ItemStackBuilder;

public class PlayerJoinListener implements Listener {

	private final Hub plugin;

	public PlayerJoinListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.getInventory().setItem(4,
				new ItemStackBuilder(Material.SLIME_BALL).setName(ChatColor.LIGHT_PURPLE + "Server Selector").build());
		player.teleport(new Location(Bukkit.getWorld(player.getWorld().getName()), 0.5, 32.5, 0.5, 0, 0));
		player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1, 1);
		player.setGameMode(GameMode.ADVENTURE);
	}
}