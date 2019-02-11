package rip.pyuto.hub;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import lombok.Getter;
import rip.pyuto.hub.listeners.async.AsyncPlayerChatListener;
import rip.pyuto.hub.listeners.entity.EntityDamageListener;
import rip.pyuto.hub.listeners.inventory.InventoryClickListener;
import rip.pyuto.hub.listeners.inventory.InventoryOpenListener;
import rip.pyuto.hub.listeners.other.ExplosionPrimeListener;
import rip.pyuto.hub.listeners.other.VehicleEnterListener;
import rip.pyuto.hub.listeners.other.WeatherChangeListener;
import rip.pyuto.hub.listeners.player.PlayerInteractListener;
import rip.pyuto.hub.listeners.player.PlayerJoinListener;
import rip.pyuto.hub.listeners.player.PlayerMoveListener;
import rip.pyuto.hub.listeners.player.PlayerQuitListener;
import rip.pyuto.hub.listeners.player.block.BlockBreakListener;
import rip.pyuto.hub.listeners.player.block.BlockPlaceListener;
import rip.pyuto.hub.listeners.player.useless.PlayerDropItemListener;
import rip.pyuto.hub.listeners.player.useless.PlayerPickupItemListener;
import rip.pyuto.hub.scoreboard.ScoreboardProvider;
import rip.pyuto.hub.scoreboard.supplier.ScoreboardManager;
import rip.pyuto.hub.selector.SelectorManager;
import rip.pyuto.hub.tablist.TablistProvider;
import rip.pyuto.hub.tablist.supplier.TablistManager;
import rip.pyuto.hub.utils.ServerInfo;

@Getter
public class Hub extends JavaPlugin {

	@Getter
	private static Hub instance;

	@Getter
	private SelectorManager selectorManager;

	private Map<String, ServerInfo> servers;	

	public Hub() {
		servers = new ConcurrentHashMap<>();
	}

	@Override
	public void onLoad() {
		Hub.instance = this;
		selectorManager = new SelectorManager(this);
	}

	@Override
	public void onEnable() {
		Bukkit.getScheduler().runTaskTimer(this, selectorManager::updateInventory, 0, 20);
		new ScoreboardManager(this, new ScoreboardProvider(this), ChatColor.DARK_GREEN.toString() + ChatColor.BOLD
				+ "Pyuto" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Hub");
		new TablistManager(this, new TablistProvider(this), 1000);
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		configuration: {
			File file = new File(getDataFolder(), "servers.yml");
			if (!file.exists()) {
				saveResource("servers.yml", false);
			}
			ConfigurationSection configuration = YamlConfiguration.loadConfiguration(file)
					.getConfigurationSection("servers");
			for (String key : configuration.getKeys(false)) {
				if (configuration.contains(key + ".host") && configuration.contains(key + ".port")) {
					servers.put(key, new ServerInfo(new InetSocketAddress(configuration.getString(key + ".host"),
							configuration.getInt(key + ".port"))));
				}
			}
			break configuration;
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				servers.values().forEach(server -> server.ping(true));
			}
		}.runTaskTimerAsynchronously(this, 20L, 20L);
		registerListeners();
	}

	private void registerListeners() {
		new AsyncPlayerChatListener(this);
		new EntityDamageListener(this);
		new InventoryClickListener(this);
		new InventoryOpenListener(this);
		new ExplosionPrimeListener(this);
		new VehicleEnterListener(this);
		new WeatherChangeListener(this);
		new BlockBreakListener(this);
		new BlockPlaceListener(this);
		new PlayerDropItemListener(this);
		new PlayerPickupItemListener(this);
		new PlayerInteractListener(this);
		new PlayerJoinListener(this);
		new PlayerMoveListener(this);
		new PlayerQuitListener(this);
	}

	@Override
	public void onDisable() {
	}

	public int getOnlineCount(String server) {
		if (server == null || server.equalsIgnoreCase("ALL")) {
			int online = 0;
			for (int next : servers.values().stream().map(ServerInfo::getCurrentOnline).map(AtomicInteger::get)
					.collect(Collectors.toSet())) {
				if (next > 0)
					online += next;
			}
			return online;
		}
		return getServer(server).getCurrentOnline().get();
	}

	public ServerInfo getServer(String name) {
		return servers.get(name);
	}

	public void sendToServer(Player player, String server) {
		ByteArrayDataOutput output = ByteStreams.newDataOutput();
		output.writeUTF("ConnectOther");
		output.writeUTF(player.getName());
		output.writeUTF(server);
		player.sendPluginMessage(this, "BungeeCord", output.toByteArray());
	}
}