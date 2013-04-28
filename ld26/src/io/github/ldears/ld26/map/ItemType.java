package io.github.ldears.ld26.map;

/**
 * @author dector
 */
public enum ItemType {

	BOTTLE("bottle_full", "bottle_packed"),
	VANTUZ("vantuz", "vantuz"),
	CAT("cat_full", "cat_packed", 0.5f),
	KNIFE("knife_full", "knife_packed"),
	BOOK("book", "book"),
	GUITAR("guitar_full", "guitar_packed"),
	RASTA("rasta", "rasta"),
	APPLE("apple", "apple");

	public final String fullSprite;
	public final String packedSprite;

	public final float animationTime;

	private ItemType(String fullSprite, String packedSprite) {
		this(fullSprite, packedSprite, 1);
	}

	private ItemType(String fullSprite, String packedSprite, float animationTime) {
		this.fullSprite = fullSprite;
		this.packedSprite = packedSprite;
		this.animationTime = animationTime;
	}

	public static int count() {
		return values().length;
	}
}
