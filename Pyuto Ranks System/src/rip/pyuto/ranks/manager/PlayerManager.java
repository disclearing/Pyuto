package rip.pyuto.ranks.manager;

import java.util.IdentityHashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import lombok.Getter;
import rip.pyuto.ranks.user.RankUser;

@Getter
public class PlayerManager {

	private Map<Player, RankUser> userMap;

	public PlayerManager() {
		userMap = new IdentityHashMap<>();
	}
}