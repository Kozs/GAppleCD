package me.kozs.mc.gapple.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
/**
 * 
 * @author Zachary Smith (2016)
 * Util class I use for several projects
 */
public class Util {
	private final static int CENTER_PX = 154;

	public static void sCM(Player p, String messageToCenter) {
		sendCenteredMessage(p, messageToCenter);
	}

	public static String getString(String[] args) {
		StringBuilder builder = new StringBuilder();
		int counter = 0;
		for (String s : args) {
			if (s == null) {

			} else {
				if (counter < args.length - 1) {
					builder.append(s + " ");
				} else {
					builder.append(s);
				}
				counter++;
			}
		}
		return builder.toString();
	}

	public static void setupUserPurchase(Player p, ItemStack[] item) {
		List<ItemStack> ssitems = Arrays.asList(item);
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.addAll(ssitems);
		boolean hasHelmet = false;
		boolean hasChestplate = false;
		boolean hasLeggings = false;
		boolean hasBoots = false;
		for (ItemStack i : item) {
			if (i != null) {
				if (i.hasItemMeta() && i.getItemMeta().hasLore() && i.getType() == Material.WOOL) {
					items.remove(i);
				}

				if (!hasBoots) {
					if (i.getType().name().toLowerCase().contains("boots")) {
						p.getInventory().setBoots(i);
						items.remove(i);
						hasBoots = true;
					}
				}
				if (!hasChestplate) {
					if (i.getType().name().toLowerCase().contains("chestplate")) {
						p.getInventory().setChestplate(i);
						items.remove(i);
						hasChestplate = true;
					}
				}
				if (!hasLeggings) {
					if (i.getType().name().toLowerCase().contains("leggings")) {
						p.getInventory().setLeggings(i);
						items.remove(i);
						hasLeggings = true;
					}
				}
				if (!hasHelmet) {
					if (i.getType().name().toLowerCase().contains("helmet")) {
						p.getInventory().setHelmet(i);
						items.remove(i);
						hasHelmet = true;
					}
				}
			}
		}

		for (ItemStack is : items) { // whatever is left add to inventory
			if (is != null) {
				p.getInventory().addItem(is);
			}
		}
		p.updateInventory();
	}

	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static void seM(Player p, String msg) {
		if (p.isOnline()) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Edit&4Mode&8]&a " + msg));
		} else {
			return;
		}
	}
	
	//&8[&6Edit&4Mode&8]
	public static void sM(Player p, String msg) {
		if (p.isOnline()) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			return;
		}
	}

	public static void sendCenteredMessage(Player player, String message) {
		if (message == null || message.equals(""))
			player.sendMessage("");
		message = ChatColor.translateAlternateColorCodes('&', message);

		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;

		for (char c : message.toCharArray()) {
			if (c == '§') {
				previousCode = true;
				continue;
			} else if (previousCode == true) {
				previousCode = false;
				if (c == 'l' || c == 'L') {
					isBold = true;
					continue;
				} else
					isBold = false;
			} else {
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
		}

		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = CENTER_PX - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while (compensated < toCompensate) {
			sb.append(" ");
			compensated += spaceLength;
		}
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', sb.toString() + message));
	}

}
