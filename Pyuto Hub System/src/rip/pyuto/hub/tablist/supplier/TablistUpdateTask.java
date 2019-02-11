package rip.pyuto.hub.tablist.supplier;

import java.util.stream.Stream;

import org.bukkit.Bukkit;

public class TablistUpdateTask implements Runnable {

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		TablistManager manager = TablistManager.INSTANCE;
		if (manager == null)
			return;
		Stream.of(Bukkit.getOnlinePlayers()).forEach(player -> {
			Tablist tablist = manager.getTablist(player);
			if (tablist != null) {
				tablist.hideRealPlayers().update();
			}
		});
	}
}