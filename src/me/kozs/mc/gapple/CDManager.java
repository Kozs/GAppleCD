package me.kozs.mc.gapple;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import me.kozs.mc.gapple.listeners.GAppleEater;
import me.kozs.mc.gapple.objects.GoldenAppleCD;

public class CDManager {

	public static HashMap<String, GoldenAppleCD> appleCD = new HashMap<String, GoldenAppleCD>();

	/**
	 * Will contain all current CD and properly remove and add when needed.
	 */
	private GAppleEater appleEater;

	public CDManager(JavaPlugin plugin) {
		appleEater = new GAppleEater();
		plugin.getServer().getPluginManager().registerEvents(appleEater, plugin);
	}
	
	public static long getAppleTime(String name, AppleType apple) {
		return appleCD.get(name).getCDTime(apple);
	}

	public static boolean verifyEat(String name, AppleType apple) {
		if (appleCD.containsKey(name)) {
			return appleCD.get(name).canEat(apple);
		} else {
			return true;
		}
	}

	public static void addUserCD(String user, AppleType apple) {
		if (appleCD.containsKey(user)) {
			appleCD.get(user).restrictApple(apple);
		} else {
			appleCD.put(user, new GoldenAppleCD(apple));
		}

	}
}
