package me.kozs.mc.gapple.listeners;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import me.kozs.mc.gapple.AppleType;
import me.kozs.mc.gapple.CDManager;
import me.kozs.mc.gapple.config.ConfigMethods;
import me.kozs.mc.gapple.config.ConfigSettings;
import me.kozs.mc.gapple.objects.CDDetector;
import me.kozs.mc.gapple.util.Util;

/**
 * 
 * @author Zachary Smith (2016) This listener will only be enabled if Overlap is
 *         enabled!
 */
public class GAppleEater implements Listener {

	public HashMap<String, CDDetector> appleCount = new HashMap<String, CDDetector>();

	@EventHandler
	public void onAppleBob(PlayerItemConsumeEvent event) {
		/**
		 * This method is here to keep track of each individual player who eats
		 * an apple and the interval between each time. If they qualify based on
		 * the config for a CD they will be rewarded with one
		 */
		ItemStack itemConsumed = event.getItem();

		if (itemConsumed.getType() == Material.GOLDEN_APPLE) {
			int durability = itemConsumed.getDurability();
			if (ConfigSettings.OVERLAP)
				durability = 2;
			Player p = event.getPlayer();
			if (CDManager.verifyEat(p.getName(), AppleType.getType(durability))) {
				if (appleCount.containsKey(p.getName())) {
					appleCount.get(p.getName()).addAppleCount(AppleType.getType(durability));
				} else {
					appleCount.put(p.getName(), new CDDetector());
					appleCount.get(p.getName()).addAppleCount(AppleType.getType(durability));
				}
				int count = appleCount.get(p.getName()).getAppleCount(AppleType.getType(durability));
				if (count == ConfigMethods.getAppleThreshold(AppleType.getType(durability))) {
					int total = ConfigMethods.getAppleThreshold(AppleType.getType(durability));
					Util.sM(p,
							ConfigSettings.CDSTARTMSG.replaceAll("%totalamt%", String.valueOf(total)).replaceAll(
									"%interval%",
									String.valueOf(ConfigMethods.getAppleInterval(AppleType.getType(durability)))));

					CDManager.addUserCD(p.getName(), AppleType.getType(durability));
					return;
				}
			} else {
				long time = CDManager.getAppleTime(p.getName(), AppleType.getType(durability));
				long timeLeft = ConfigMethods.getCurrentCooldownTime(AppleType.getType(durability)) - time;
				Util.sM(p, ConfigSettings.CDMSG.replaceAll("%timeleft%", String.valueOf(timeLeft)));
				event.setCancelled(true);
				return;
			}
		}
	}

}
