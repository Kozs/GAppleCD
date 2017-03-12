package me.kozs.mc.gapple.objects;

import java.time.Instant;

import me.kozs.mc.gapple.AppleType;
import me.kozs.mc.gapple.config.ConfigSettings;

/**
 * 
 * @author Zachary Smith (2016) This object will be given to each player that
 *         qualifies for CD
 */
public class GoldenAppleCD {

	private long totalstartCD; // Start of the CD to compare relative

	private long gApplestartCD;
	private long egApplestartCD;

	public boolean both = false;
	public boolean gApple = false;
	public boolean egApple = false;

	public GoldenAppleCD(AppleType apple) {
		restrictApple(apple);
	}

	public void restrictApple(AppleType apple) {
		switch (apple) {
		case BOTH:
			this.totalstartCD = Instant.now().getEpochSecond();
			both = true;
			break;
		case ENCHANTED_GOLDEN_APPLE:
			this.egApplestartCD = Instant.now().getEpochSecond();
			egApple = true;
			break;
		case GOLDEN_APPLE:
			this.gApplestartCD = Instant.now().getEpochSecond();
			gApple = true;
			break;
		}
	}

	public boolean isRestricted(AppleType apple) {
		switch (apple) {
		case BOTH:
			return both;
		case ENCHANTED_GOLDEN_APPLE:
			return egApple;
		case GOLDEN_APPLE:
			return gApple;
		default:
			return false;
		}
	}

	public boolean canEat(AppleType apple) {
		switch (apple) {
		case BOTH:
			return getCDTime(apple) > ConfigSettings.TOTALCDSECS;
		case ENCHANTED_GOLDEN_APPLE:
			return getCDTime(apple) > ConfigSettings.EGAPPLECDSECS;
		case GOLDEN_APPLE:
			return getCDTime(apple) > ConfigSettings.GAPPLECDSECS;
		default:
			return false;
		}
	}

	/**
	 * @return the time between when the CD started to curr time
	 */
	public long getCDTime(AppleType apple) {
		switch (apple) {
		case BOTH:
			return Instant.now().getEpochSecond() - totalstartCD;
		case ENCHANTED_GOLDEN_APPLE:
			return Instant.now().getEpochSecond() - egApplestartCD;
		case GOLDEN_APPLE:
			return Instant.now().getEpochSecond() - gApplestartCD;
		}
		return 0;
	}

}
