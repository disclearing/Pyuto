package rip.pyuto.ranks.utils;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import rip.pyuto.ranks.Ranks;
import rip.pyuto.ranks.rank.Rank;
import rip.pyuto.ranks.user.RankUser;

public class PlayerUtil {

	private PlayerUtil() {
		throw new RuntimeException("Cannot instantiate a utility.");
	}

	public static Rank getRank(Player player) {
		RankUser user = getUser(player);
		if (user == null)
			return null;
		return user.getRank();
	}

	public static ChatColor getRankColor(Player player) {
		return getRank(player).getColor();
	}

	public static RankUser getUser(Player player) {
		Map<Player, RankUser> userMap = Ranks.getInstance().getPlayerManager().getUserMap();
		userMap.putIfAbsent(player, new RankUser(player.getUniqueId(), player.getName()));
		return userMap.get(player);
	}

	public static void setRank(Player player, Rank rank) {
		getUser(player).setRank(rank);
	}
}