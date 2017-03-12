package me.kozs.mc.gapple.objects;

import java.time.Instant;

import me.kozs.mc.gapple.AppleType;
import me.kozs.mc.gapple.config.ConfigSettings;

/**
 * 
 * @author Zachary Smith (2016) Will keep track of each users Apple Eating
 *         Habits xD
 */
public class CDDetector {

	private int totalAppleCount;
	private long totallastTimeStamp; // Will record last time a user ate an
										// apple
	/**
	 * Decided to follow this habit of separating the values depending on users
	 * config settings.
	 */
	private int gAppleCount;
	private long glastTimeStamp;
	private int egAppleCount;
	private long eglastTimeStamp;

	/**
	 * Will check if the users previous apples eaten should be reset or not
	 * 
	 * @param apple
	 * @return
	 */
	boolean appleCountVerified(AppleType apple) {
		switch (apple) {
		case ENCHANTED_GOLDEN_APPLE:
			return Instant.now().getEpochSecond() - eglastTimeStamp > ConfigSettings.EGAPPLESECS;
		case GOLDEN_APPLE:
			return Instant.now().getEpochSecond() - glastTimeStamp > ConfigSettings.GAPPLESECS;
		case BOTH:
			return Instant.now().getEpochSecond() - totallastTimeStamp > ConfigSettings.TOTALAPPLESECS;
		default:
			break;
		}
		return false;
	}

	/**
	 * 
	 * @param olap
	 *            if overlap is enabled
	 * @param apple
	 *            the type of the apple
	 */
	void removeAppleCount(AppleType apple) {
		switch (apple) {
		case ENCHANTED_GOLDEN_APPLE:
			egAppleCount = 0;
			break;
		case GOLDEN_APPLE:
			gAppleCount = 0;
			break;
		case BOTH:
			totalAppleCount = 0;
			break;
		}
	}

	/**
	 * Will display apple count to user
	 * 
	 * @param p
	 */
	public int getAppleCount(AppleType apple) {
		switch (apple) {
		case BOTH:
			return totalAppleCount;
		case ENCHANTED_GOLDEN_APPLE:
			return egAppleCount;
		case GOLDEN_APPLE:
			return gAppleCount;
		default:
			return 0;
		}
	}

	/**
	 * 
	 * @param apple
	 *            The apple the user is attempting to eat
	 */
	public void addAppleCount(AppleType apple) {
		if (appleCountVerified(apple)) { // Returns true if we need to clean ac
			removeAppleCount(apple);
		}
		switch (apple) {
		case ENCHANTED_GOLDEN_APPLE:
			egAppleCount++;
			eglastTimeStamp = Instant.now().getEpochSecond();
			break;
		case GOLDEN_APPLE:
			gAppleCount++;
			glastTimeStamp = Instant.now().getEpochSecond();
			break;
		case BOTH:
			totalAppleCount++;
			totallastTimeStamp = Instant.now().getEpochSecond();
			break;
		default:
			break;
		}
	}

	public int getTotalAppleCount() {
		return totalAppleCount;
	}

	public void setTotalAppleCount(int totalAppleCount) {
		this.totalAppleCount = totalAppleCount;
	}

	public int getGappleCount() {
		return gAppleCount;
	}

	public void setGappleCount(int gappleCount) {
		this.gAppleCount = gappleCount;
	}

	public int getEgAppleCount() {
		return egAppleCount;
	}

	public void setEgAppleCount(int egAppleCount) {
		this.egAppleCount = egAppleCount;
	}

	public long getTotallastTimeStamp() {
		return totallastTimeStamp;
	}

	public void setTotallastTimeStamp(long totallastTimeStamp) {
		this.totallastTimeStamp = totallastTimeStamp;
	}

	public long getGlastTimeStamp() {
		return glastTimeStamp;
	}

	public void setGlastTimeStamp(long glastTimeStamp) {
		this.glastTimeStamp = glastTimeStamp;
	}

	public long getEglastTimeStamp() {
		return eglastTimeStamp;
	}

	public void setEglastTimeStamp(long eglastTimeStamp) {
		this.eglastTimeStamp = eglastTimeStamp;
	}

}
