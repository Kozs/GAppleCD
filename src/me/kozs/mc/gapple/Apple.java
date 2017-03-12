package me.kozs.mc.gapple;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import me.kozs.mc.gapple.config.ConfigSettings;

/**
 * 
 * @author Zachary Smith (2016) Main Class, will control the sassy stuffs ;)
 *         -;)
 */
public class Apple extends JavaPlugin {

	public static Logger log;
	
	private static Apple INSTANCE;
	
	private CDManager cdManager;
	
	public void onEnable() {
		INSTANCE = this;
		log = this.getLogger(); /* Init Logger for Debug */
		setupConfig();
		
		this.cdManager = new CDManager(this);
	}
	
	void setupConfig() {
		saveDefaultConfig();
		@SuppressWarnings("unused")
		ConfigSettings configSettings = new ConfigSettings(this); //Will set the static vars and be collected by GC
	}

	public CDManager getCdManager() {
		return cdManager;
	}

	public void setCdManager(CDManager cdManager) {
		this.cdManager = cdManager;
	}

	public static Apple getInstance() {
		return INSTANCE;
	}

	public static void setInstance(Apple iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
}
