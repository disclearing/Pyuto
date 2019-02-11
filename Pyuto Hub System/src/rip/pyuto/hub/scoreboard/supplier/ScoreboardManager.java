package rip.pyuto.hub.scoreboard.supplier;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class ScoreboardManager implements Listener {

	@Getter
	private static ScoreboardManager instance;

	@Getter
	private final ScoreboardEntryProvider provider;

	@Getter
	private final String title;

	@Getter
	private final Map<UUID, PlayerScoreboard> scoreboards;

	private final JavaPlugin plugin;

	public boolean debugging;

	@SuppressWarnings("deprecation")
	public ScoreboardManager(JavaPlugin plugin, ScoreboardEntryProvider provider, String title) {
		ScoreboardManager.instance = this;
		this.plugin = plugin;
		this.provider = provider;
		this.title = title;
		this.scoreboards = new ConcurrentHashMap<>();
		Bukkit.getPluginManager().registerEvents(this, plugin);
		Stream.of(Bukkit.getOnlinePlayers()).forEach(player -> {
			if (debugging) {
				plugin.getLogger().info("[ScoreboardManager] Loaded scoreboard for " + player.getName() + "["
						+ player.getUniqueId().toString() + "]");
			}
			UUID uniqueId = player.getUniqueId();
			scoreboards.putIfAbsent(uniqueId, new PlayerScoreboard(player));
		});
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this::update, 5, 5);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		UUID uniqueId = player.getUniqueId();
		Bukkit.getScheduler().runTaskLater(plugin, () -> {
			if (debugging) {
				plugin.getLogger().info("[ScoreboardManager] Loaded scoreboard for " + player.getName() + "["
						+ player.getUniqueId().toString() + "]");
			}
			scoreboards.putIfAbsent(uniqueId, new PlayerScoreboard(player));
		}, 3l);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		UUID uniqueId = player.getUniqueId();
		PlayerScoreboard scoreboard;
		if ((scoreboard = scoreboards.remove(uniqueId)) != null) {
			if (debugging) {
				plugin.getLogger().info("[ScoreboardManager] Deleted scoreboard of " + player.getName() + "["
						+ player.getUniqueId().toString() + "]");
			}
			scoreboard.clean();
		}
	}

	@EventHandler
	public void onDisable(PluginDisableEvent event) {
		if (event.getPlugin() == plugin) {
			scoreboards.forEach((id, board) -> {
				board.clean();
			});
			scoreboards.clear();
			HandlerList.unregisterAll(this);
		}
	}

	public void update() {
		scoreboards.forEach((id, board) -> {
			board.send();
		});
	}

}
