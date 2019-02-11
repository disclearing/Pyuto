package rip.pyuto.ranks.rank;

import org.bukkit.ChatColor;

import lombok.Getter;

@Getter
public enum Rank {
	
	/*
	 * Change these to your liking; if you need help contact me.
	 */

	OWNER(ChatColor.DARK_RED + "Owner", ChatColor.DARK_RED, 1000),
	LEAD_DEVELOPER(ChatColor.YELLOW + "Lead Developer", ChatColor.YELLOW, 900),
	DEVELOPER(ChatColor.GREEN + "Developer", ChatColor.GREEN, 800),
	COMMUNITY_MANAGER(ChatColor.DARK_RED + "Community Manager", ChatColor.DARK_RED, 700),
	PLATFORM_ADMINISTRATOR(ChatColor.RED + "Platform Administrator", ChatColor.RED, 600),
	SENIOR_ADMINISTRATOR(ChatColor.RED + "Senior Administrator", ChatColor.RED, 500),
	ADMINISTRATOR(ChatColor.RED + "Administrator", ChatColor.RED, 400),
	HEAD_MODERATOR(ChatColor.BLUE + "Head Moderator", ChatColor.BLUE, 300),
	SENIOR_MODERATOR(ChatColor.BLUE + "Senior Moderator", ChatColor.BLUE, 200),
	MODERATOR(ChatColor.BLUE + "Moderator", ChatColor.BLUE, 100),
	AUXILIARY(ChatColor.DARK_AQUA + "Auxiliary", ChatColor.DARK_AQUA, 50),
	BUILDER(ChatColor.GOLD + "Builder", ChatColor.GOLD, 40),
	MEDIA(ChatColor.LIGHT_PURPLE + "Media", ChatColor.LIGHT_PURPLE, 35),
	GUARD(ChatColor.DARK_BLUE + "Guard", ChatColor.DARK_BLUE, 25),
	CRIMINAL(ChatColor.DARK_BLUE + "Criminal", ChatColor.DARK_BLUE, 15),
	INMATE(ChatColor.DARK_PURPLE + "Inmate", ChatColor.DARK_PURPLE, 5),
	DEFAULT("Default", ChatColor.WHITE, 1);

	private final String prefix;
	private final ChatColor color;
	private final int hierarchy;

	Rank(String prefix, ChatColor color, int hierarchy) {
		this.prefix = prefix;
		this.color = color;
		this.hierarchy = hierarchy;
	}
}