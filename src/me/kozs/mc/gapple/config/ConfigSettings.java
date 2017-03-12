package me.kozs.mc.gapple.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author Zachary Smith (2016) Simple Constant Class
 */
public class ConfigSettings {

	public static String CDMSG, CDSTARTMSG; // Message for Cooldown

	public static boolean OVERLAP; // Count both gapples as one or two seperate

	public static int GAPPLECDSECS, EGAPPLECDSECS;

	public static int GAPPLEAMT, GAPPLESECS; // Seconds/amt user can eat before
												// CD

	public static int EGAPPLEAMT, EGAPPLESECS; // Seconds amt user can eat
												// before CD
	
	public static int TOTALCDSECS, TOTALAPPLEAMT, TOTALAPPLESECS; //Will be if overlap is true ONLY

	public ConfigSettings(JavaPlugin plugin) {
		/**
		 * Only need this to be ran once.
		 */
		loadSettings(plugin);
	}

	public static void loadSettings(JavaPlugin plugin) {
		FileConfiguration config = plugin.getConfig();
		CDMSG = config.getString("cooldown-message");
		CDSTARTMSG = config.getString("cooldown-begin-message");
		OVERLAP = config.getBoolean("overlap-applecount");
		if (OVERLAP == true) { //This means we will want to record both apples themself
			TOTALCDSECS = config.getInt("total-cooldown-seconds");
			TOTALAPPLEAMT = config.getInt("total-trigger-amt");
			TOTALAPPLESECS = config.getInt("total-interval-seconds");
		} else {
			GAPPLECDSECS = config.getInt("gapple-cooldown-seconds");
			GAPPLEAMT = config.getInt("gapple-trigger-amt");
			GAPPLESECS = config.getInt("gapple-interval-seconds");
			EGAPPLECDSECS = config.getInt("enchanted-gapple-cooldown-seconds");
			EGAPPLEAMT = config.getInt("enchanted-gapple-tigger-amt");
			EGAPPLESECS = config.getInt("enchanted-gapple-interval-seconds");
		}
	}

}
