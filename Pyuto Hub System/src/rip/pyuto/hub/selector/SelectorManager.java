package rip.pyuto.hub.selector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import rip.pyuto.hub.Hub;
import rip.pyuto.hub.utils.ItemStackBuilder;

public class SelectorManager implements Listener {

	private final Hub plugin;
	public Inventory selector;

	public SelectorManager(Hub plugin) {
		this.plugin = plugin;
		this.selector = Bukkit.createInventory(null, 27,
				ChatColor.GOLD + "Select your journey..");
	}

	public void updateInventory() {
		selector.setItem(13, new ItemStackBuilder(Material.FIRE)
				.setName(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Fury Realm" + ChatColor.GRAY + " → "
						+ plugin.getServer("PRISON").getStatus())
				.addLore("",
						ChatColor.GREEN + "Online Player(s)" + ChatColor.GRAY + " → " + ChatColor.WHITE
								+ plugin.getServer("PRISON"),
						"", "      §7Mine to success, but it doesn't",
						"      §7just stop there; continue to freedom.", "",
						ChatColor.YELLOW.toString() + ChatColor.ITALIC + "To connect, simply §lclick §e§oon this item.")
				.build());
	}
}