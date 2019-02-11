package rip.pyuto.hub.listeners.async;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import rip.pyuto.hub.Hub;
import rip.pyuto.ranks.Ranks;
import rip.pyuto.ranks.rank.Rank;
import rip.pyuto.ranks.user.RankUser;

public class AsyncPlayerChatListener implements Listener {

	private final Hub plugin;

	public AsyncPlayerChatListener(Hub plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onChat(AsyncPlayerChatEvent event) {
		RankUser user = Ranks.getInstance().getPlayerManager().getUserMap().get(event.getPlayer());
		Rank rank = user.getRank();
		StringBuilder builder = new StringBuilder();
		if (rank != Rank.INMATE) {
			builder.append(rank.getColor());
			builder.append(ChatColor.GRAY).append("[").append(rank.getPrefix()).append(ChatColor.GRAY).append("] ");
		}
		builder.append(rank.getColor());
		builder.append("%1$s").append(ChatColor.WHITE).append(" §7»§f %2$s");
		event.setFormat(builder.toString());
	}
}