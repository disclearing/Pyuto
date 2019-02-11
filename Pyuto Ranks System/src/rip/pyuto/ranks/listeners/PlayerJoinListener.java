package rip.pyuto.ranks.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rip.pyuto.ranks.Ranks;

public class PlayerJoinListener implements Listener {

	private final Ranks plugin;

	public PlayerJoinListener(Ranks plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		Player player = event.getPlayer();
		plugin.getDatabaseDAO().getUser(player).whenComplete((user, throwable) -> {
			if (throwable != null) {
				player.sendMessage("There was an Error whilst attempting to load your data.");
				throwable.printStackTrace();
				return;
			}
			plugin.getPlayerManager().getUserMap().put(player, user);
		});
	}
}