package rip.pyuto.ranks.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rip.pyuto.ranks.Ranks;
import rip.pyuto.ranks.rank.Rank;
import rip.pyuto.ranks.utils.PlayerUtil;

public class RankBaseCommand implements CommandExecutor {

	private final Ranks plugin;

	public RankBaseCommand(Ranks plugin) {
		this.plugin = plugin;
		this.plugin.getCommand("rank").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("set")) {
				if (args.length < 3) {
					sender.sendMessage(ChatColor.RED + "Usage: /" + label + " set <player> <rank>");
					return true;
				}
				Player target = null;
				if ((target = Bukkit.getPlayer(args[1])) == null) {
					sender.sendMessage(ChatColor.RED + "That player is not online.");
					return true;
				}
				Rank rank = null;
				try {
					rank = Rank.valueOf(args[2].toUpperCase());
				} catch (IllegalArgumentException exception) {
					sender.sendMessage(ChatColor.RED + "That rank does not exist.");
					return true;
				}
				PlayerUtil.setRank(target, rank);
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.WHITE + target.getName() + "'s "
						+ ChatColor.GREEN + "new Rank to " + rank.getPrefix() + ChatColor.GREEN + ".");
				target.sendMessage(ChatColor.GREEN + "An" + ChatColor.RED + " Administrator " + ChatColor.GREEN
						+ "has set your new Rank to " + rank.getPrefix() + ChatColor.GREEN + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("get")) {
				if (args.length < 2) {
					sender.sendMessage(ChatColor.RED + "Usage: /" + label + " get <player>");
					return true;
				}
				Player target = null;
				if ((target = Bukkit.getPlayer(args[1])) == null) {
					sender.sendMessage(ChatColor.RED + "That player is not online.");
					return true;
				}
				sender.sendMessage(PlayerUtil.getRankColor(target) + target.getName() + ChatColor.GREEN
						+ " is currently a " + PlayerUtil.getRankColor(target) + PlayerUtil.getRank(target).getPrefix()
						+ ChatColor.GREEN + ".");
				return true;
			}
			if (args[0].equalsIgnoreCase("list")) {
				sender.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "List of Every Rank");
				sender.sendMessage(ChatColor.DARK_RED + "Owner" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.OWNER.getHierarchy());

				sender.sendMessage(ChatColor.YELLOW + "Lead Developer" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.LEAD_DEVELOPER.getHierarchy());

				sender.sendMessage(ChatColor.GREEN + "Developer" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.DEVELOPER.getHierarchy());

				sender.sendMessage(ChatColor.DARK_RED + "Commnuity Manager" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.COMMUNITY_MANAGER.getHierarchy());

				sender.sendMessage(ChatColor.RED + "Platform Administrator" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.PLATFORM_ADMINISTRATOR.getHierarchy());

				sender.sendMessage(ChatColor.RED + "Senior Administrator" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.SENIOR_ADMINISTRATOR.getHierarchy());

				sender.sendMessage(ChatColor.RED + "Administrator" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.ADMINISTRATOR.getHierarchy());

				sender.sendMessage(ChatColor.BLUE + "Head Moderator" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.HEAD_MODERATOR.getHierarchy());

				sender.sendMessage(ChatColor.BLUE + "Senior Moderator" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.SENIOR_MODERATOR.getHierarchy());

				sender.sendMessage(ChatColor.BLUE + "Moderator" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.MODERATOR.getHierarchy());

				sender.sendMessage(ChatColor.DARK_AQUA + "Auxiliary" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.AUXILIARY.getHierarchy());

				sender.sendMessage(ChatColor.GOLD + "Builder" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.BUILDER.getHierarchy());

				sender.sendMessage(ChatColor.LIGHT_PURPLE + "Media" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.MEDIA.getHierarchy());

				sender.sendMessage(ChatColor.DARK_BLUE + "Guard" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.GUARD.getHierarchy());

				sender.sendMessage(ChatColor.DARK_BLUE + "Criminal" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.CRIMINAL.getHierarchy());

				sender.sendMessage(ChatColor.DARK_PURPLE + "Inmate" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.INMATE.getHierarchy());

				sender.sendMessage(ChatColor.DARK_RED + "Default" + ChatColor.GRAY + " → " + ChatColor.WHITE
						+ Rank.DEFAULT.getHierarchy());
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Rank Command Information");
			sender.sendMessage(ChatColor.GREEN + "/" + label + " set <player> <rank>" + ChatColor.GRAY + " - "
					+ ChatColor.WHITE + "Set a player's rank.");
			sender.sendMessage(ChatColor.GREEN + "/" + label + " get <player>" + ChatColor.GRAY + " - "
					+ ChatColor.WHITE + "View a player's rank.");
			sender.sendMessage(ChatColor.GREEN + "/" + label + " list" + ChatColor.GRAY + " - " + ChatColor.WHITE
					+ "View all the Ranks on the Network.");
			return true;
		}
		return true;
	}
}