package rip.pyuto.hub.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import rip.pyuto.hub.Hub;
import rip.pyuto.hub.scoreboard.supplier.ScoreboardEntryProvider;
import rip.pyuto.ranks.utils.PlayerUtil;

public class ScoreboardProvider implements ScoreboardEntryProvider {

	private final Hub plugin;

	public ScoreboardProvider(Hub plugin) {
		this.plugin = plugin;
	}

	@Override
	public List<String> getScoreboardEntries(Player player) {
		List<String> lines = new ArrayList<>();
		lines.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------------");
		lines.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Current Rank");
		lines.add(PlayerUtil.getRankColor(player) + PlayerUtil.getRank(player).getPrefix());
		lines.add("");
		lines.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Online Players");
		lines.add(plugin.getOnlineCount(null) + " player(s)");
		lines.add("");
		lines.add(ChatColor.BLUE + "store.arythium.cc");
		lines.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------------");
		return lines;
	}
}