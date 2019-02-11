package rip.pyuto.hub.tablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import rip.pyuto.hub.Hub;
import rip.pyuto.hub.tablist.supplier.TablistEntrySupplier;
import rip.pyuto.ranks.utils.PlayerUtil;

public class TablistProvider implements TablistEntrySupplier {

	private final Hub plugin;

	public TablistProvider(Hub plugin) {
		this.plugin = plugin;
	}

	@Override
	public Table<Integer, Integer, String> getEntries(Player player) {
		Table<Integer, Integer, String> tab = HashBasedTable.create();
		tab.put(0, 1, ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------");
		tab.put(1, 1, ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------");
		tab.put(2, 1, ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");
		tab.put(1, 3,
				ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Pyuto" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Bukkit.getWorlds().stream()
								.filter(world -> world.getEnvironment() == World.Environment.NORMAL).findFirst()
								.orElse(Bukkit.getWorlds().get(0)).getName());
		tab.put(0, 6, ChatColor.GREEN.toString() + ChatColor.BOLD + "Current Rank");
		tab.put(0, 7, PlayerUtil.getRankColor(player) + PlayerUtil.getRank(player).getPrefix());
		tab.put(2, 6, ChatColor.GREEN.toString() + ChatColor.BOLD + "Online Players");
		tab.put(2, 7, plugin.getOnlineCount("ALL") + " player(s)");

		tab.put(1, 8, ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Flame Realm");
		tab.put(1, 9, plugin.getOnlineCount("PRISON") + " player(s)");
		tab.put(1, 10, plugin.getServer("PRISON").getStatus().toString());

		tab.put(3, 7, ChatColor.DARK_RED.toString() + ChatColor.BOLD + "WARNING");
		tab.put(3, 9, ChatColor.RED + "Please use 1.7.10");
		tab.put(3, 10, ChatColor.RED + "for a better time");
		tab.put(3, 11, ChatColor.RED + "and more of an op");
		tab.put(3, 12, ChatColor.RED + "timal experience.");

		tab.put(0, 10, ChatColor.GREEN.toString() + ChatColor.BOLD + "Hub #1");
		tab.put(0, 11, plugin.getOnlineCount("Hub1") + " player(s)");
		tab.put(0, 12, plugin.getServer("Hub1").getStatus().toString());

		tab.put(2, 10, ChatColor.GREEN.toString() + ChatColor.BOLD + "Hub Restricted");
		tab.put(2, 11, plugin.getOnlineCount("Hub-Restricted") + " player(s)");
		tab.put(2, 12, plugin.getServer("Hub-Restricted").getStatus().toString());

		tab.put(0, 18, ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------");
		tab.put(1, 18, ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------");
		tab.put(2, 18, ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");
		return tab;
	}

	@Override
	public String getHeader(Player player) {
		return null;
	}

	@Override
	public String getFooter(Player player) {
		return null;
	}
}