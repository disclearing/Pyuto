package rip.pyuto.hub.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemStackBuilder {

	private ItemStack itemStack;
	private ItemMeta itemMeta;

	public ItemStackBuilder(Material material) {
		itemStack = new ItemStack(material);
		itemMeta = itemStack.getItemMeta();
	}

	public ItemStackBuilder amount(int amount) {
		itemStack.setAmount(amount);
		return this;
	}

	public ItemStackBuilder setName(String displayName) {
		itemMeta.setDisplayName(displayName);
		return this;
	}

	public ItemStackBuilder color(int hex) {
		if (itemMeta instanceof LeatherArmorMeta) {
			((LeatherArmorMeta) itemMeta).setColor(Color.fromRGB(hex));
		}
		return this;
	}

	public ItemStackBuilder addLore(String... strings) {
		List<String> loreArray = new ArrayList<>();
		for (String loreBit : strings) {
			loreArray.add(ChatColor.WHITE + loreBit);
		}
		itemMeta.setLore(loreArray);
		return this;
	}

	public ItemStackBuilder enchant(Enchantment enchanement, int level, boolean ignoreLevelRestriction) {
		itemMeta.addEnchant(enchanement, level, ignoreLevelRestriction);
		return this;
	}

	public ItemStackBuilder durability(int durability) {
		itemStack.setDurability((short) durability);
		return this;
	}

	public ItemStack build() {
		ItemStack clonedStack = itemStack.clone();
		clonedStack.setItemMeta(itemMeta.clone());
		return clonedStack;
	}
}