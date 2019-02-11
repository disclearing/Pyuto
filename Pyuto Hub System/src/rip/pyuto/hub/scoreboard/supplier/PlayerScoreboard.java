package rip.pyuto.hub.scoreboard.supplier;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_7_R4.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_7_R4.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_7_R4.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import rip.pyuto.hub.scoreboard.supplier.reflection.ReflectionConstants;

public class PlayerScoreboard {

	public static final String[] TEAM_NAMES;

	private final Player player;
	private final PlayerConnection connection;
	private final String objectiveName;
	private int lastSent = 0;
	private boolean valid = true;

	@SuppressWarnings("unchecked")
	public PlayerScoreboard(Player player) {
		this.player = player;
		this.connection = ((CraftPlayer) player).getHandle().playerConnection;
		this.objectiveName = "Sidebar-" + RandomStringUtils.random(8,
				"aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789$_?*+-/()[]{}%!=&@<>~");
		PacketPlayOutScoreboardObjective objective = new PacketPlayOutScoreboardObjective();
		ReflectionConstants.SCOREBOARD_OBJECTIVE_NAME.set(objective, objectiveName);
		ReflectionConstants.SCOREBOARD_OBJECTIVE_TITLE.set(objective, ScoreboardManager.getInstance().getTitle());
		ReflectionConstants.SCOREBOARD_OBJECTIVE_ACTION.set(objective, 0);
		connection.sendPacket(objective);
		PacketPlayOutScoreboardDisplayObjective displayObjective = new PacketPlayOutScoreboardDisplayObjective();
		ReflectionConstants.SCOREBOARD_DISPLAY_OBJECTIVE_NAME.set(displayObjective, objectiveName);
		ReflectionConstants.SCOREBOARD_DISPLAY_OBJECTIVE_POSITION.set(displayObjective, 1);
		connection.sendPacket(displayObjective);
		for (int i = 0; i < 15; i++) {
			PacketPlayOutScoreboardTeam team = new PacketPlayOutScoreboardTeam();
			ReflectionConstants.SCOREBOARD_TEAM_NAME.set(team, TEAM_NAMES[i]);
			((Collection<String>) ReflectionConstants.SCOREBOARD_TEAM_PLAYERS.get(team)).add(TEAM_NAMES[i]);
			connection.sendPacket(team);
		}
	}

	public void send() {
		if (!valid)
			return;
		List<String> lines = ScoreboardManager.getInstance().getProvider().getScoreboardEntries(player);
		if (lastSent != lines.size()) {
			for (int i = 0; i < (15); i++) {
				PacketPlayOutScoreboardScore score = new PacketPlayOutScoreboardScore();
				ReflectionConstants.SCOREBOARD_SCORE_NAME.set(score, TEAM_NAMES[i]);
				ReflectionConstants.SCOREBOARD_SCORE_OBJECTIVE.set(score, objectiveName);
				ReflectionConstants.SCOREBOARD_SCORE_ACTION.set(score, 1);
				connection.sendPacket(score);
			}
		}
		for (int i = 0; i < Math.min(lines.size(), 15); i++) {
			String line = lines.get(i);
			String left = "", right = "";
			if (line.length() < 17) {
				left = line;
			} else {
				left = line.substring(0, 16);
				right = line.substring(16, line.length());
				if (left.endsWith("\u00a7")) {
					left = left.substring(0, left.length() - 1);
					right = "\u00a7" + right;
				}
				final String lastColors = ChatColor.getLastColors(left);
				right = lastColors + right;
			}
			PacketPlayOutScoreboardTeam team = new PacketPlayOutScoreboardTeam();
			ReflectionConstants.SCOREBOARD_TEAM_NAME.set(team, TEAM_NAMES[i]);
			ReflectionConstants.SCOREBOARD_TEAM_PREFIX.set(team, left);
			ReflectionConstants.SCOREBOARD_TEAM_SUFFIX.set(team, StringUtils.left(right, 16));
			ReflectionConstants.SCOREBOARD_TEAM_ACTION.set(team, 2);
			connection.sendPacket(team);
			PacketPlayOutScoreboardScore score = new PacketPlayOutScoreboardScore();
			ReflectionConstants.SCOREBOARD_SCORE_NAME.set(score, TEAM_NAMES[i]);
			ReflectionConstants.SCOREBOARD_SCORE_SCORE.set(score, 15 - i);
			ReflectionConstants.SCOREBOARD_SCORE_OBJECTIVE.set(score, objectiveName);
			connection.sendPacket(score);
		}
		lastSent = lines.size();
	}

	public void clean() {
		for (int i = 0; i < 15; i++) {
			PacketPlayOutScoreboardTeam team = new PacketPlayOutScoreboardTeam();
			ReflectionConstants.SCOREBOARD_TEAM_NAME.set(team, TEAM_NAMES[i]);
			ReflectionConstants.SCOREBOARD_TEAM_ACTION.set(team, 4);
			connection.sendPacket(team);
		}
		player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		valid = false;
	}

	static {
		TEAM_NAMES = new String[15];
		ChatColor[] colors = ChatColor.values();
		for (int i = 0; i < 15; i++) {
			ChatColor left = colors[i];
			ChatColor right = colors[15 - i];
			TEAM_NAMES[i] = left.toString() + ChatColor.RESET + right.toString() + ChatColor.RESET;
		}
	}

}
