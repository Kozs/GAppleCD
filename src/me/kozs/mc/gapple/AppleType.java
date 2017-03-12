package me.kozs.mc.gapple;

public enum AppleType {

	GOLDEN_APPLE("Golden Apple", 0), ENCHANTED_GOLDEN_APPLE("Enchanted Golden Apple", 1),
	BOTH("Both", 2);

	private String name;
	private int durability;

	private AppleType(String name, int dur) {
		this.name = name;
		this.durability = dur;
	}

	public static AppleType getType(int durability) {
		switch (durability) {
		case 0:
			return GOLDEN_APPLE;
		case 1:
			return ENCHANTED_GOLDEN_APPLE;
		case 2:
			return BOTH;
		default:
			return null;
		}
	}

	public String getName() {
		return name;
	}

	public int getDurability() {
		return durability;
	}
}
