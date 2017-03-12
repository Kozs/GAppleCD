package me.kozs.mc.gapple.config;

import me.kozs.mc.gapple.AppleType;

public class ConfigMethods {

	/**
	 * 
	 * @param type
	 *            -
	 * @return
	 */
	public static int getCurrentCooldownTime(AppleType type) {
		switch (type) {
		case ENCHANTED_GOLDEN_APPLE:
			return ConfigSettings.EGAPPLECDSECS;
		case GOLDEN_APPLE:
			return ConfigSettings.GAPPLECDSECS;
		case BOTH:
			return ConfigSettings.TOTALCDSECS;
		default:
			return 0;
		}
	}
	
	/**
	 * 
	 * @param type
	 *            -
	 * @return
	 */
	public static int getAppleThreshold(AppleType type) {
		switch (type) {
		case ENCHANTED_GOLDEN_APPLE:
			return ConfigSettings.EGAPPLEAMT;
		case GOLDEN_APPLE:
			return ConfigSettings.GAPPLEAMT;
		case BOTH:
			return ConfigSettings.TOTALAPPLEAMT;
		default:
			return 0;
		}
	}
	
	/**
	 * 
	 * @param type
	 *            -
	 * @return
	 */
	public static int getAppleInterval(AppleType type) {
		switch (type) {
		case ENCHANTED_GOLDEN_APPLE:
			return ConfigSettings.EGAPPLESECS;
		case GOLDEN_APPLE:
			return ConfigSettings.EGAPPLESECS;
		case BOTH:
			return ConfigSettings.TOTALAPPLESECS;
		default:
			return 0;
		}
	}

}
