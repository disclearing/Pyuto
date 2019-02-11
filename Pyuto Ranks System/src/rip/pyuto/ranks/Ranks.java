package rip.pyuto.ranks;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import rip.pyuto.ranks.commands.RankBaseCommand;
import rip.pyuto.ranks.database.DatabaseDAO;
import rip.pyuto.ranks.database.Mongo;
import rip.pyuto.ranks.listeners.PlayerJoinListener;
import rip.pyuto.ranks.listeners.PlayerQuitListener;
import rip.pyuto.ranks.manager.PlayerManager;

@Getter
public class Ranks extends JavaPlugin {

	private DatabaseDAO databaseDAO;
	private PlayerManager playerManager;

	@Getter
	public static Ranks instance;

	@SuppressWarnings("deprecation")
	public void onEnable() {
		instance = this;
		databaseDAO = new Mongo();
		playerManager = new PlayerManager();
		for (Player player : getServer().getOnlinePlayers()) {
			databaseDAO.getUser(player)
					.whenComplete((user, throwable) -> playerManager.getUserMap().put(player, user));
		}
		new RankBaseCommand(this);
		new PlayerJoinListener(this);
		new PlayerQuitListener(this);
	}

	public void onDisable() {
		playerManager.getUserMap().values().forEach(databaseDAO::saveUser);
	}
}