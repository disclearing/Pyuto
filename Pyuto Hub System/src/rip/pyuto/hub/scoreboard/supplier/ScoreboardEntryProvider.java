package rip.pyuto.hub.scoreboard.supplier;

import java.util.List;

import org.bukkit.entity.Player;

public interface ScoreboardEntryProvider {
	
	public List<String> getScoreboardEntries(Player player);

}