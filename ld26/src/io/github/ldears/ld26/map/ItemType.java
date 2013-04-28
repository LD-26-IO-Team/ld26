package io.github.ldears.ld26.map;

/**
 * @author dector
 */
public enum ItemType {

	BOTTLE("bottle_full", "bottle_packed"),
	VANTUZ("vantuz", "vantuz"),
	CAT("cat_full", "cat_packed"),
	KNIFE("knife_full", "knife_packed"),
	BOOK("book", "book"),
	GUITAR("guitar_full", "guitar_packed"),
	RASTA("rasta", "rasta"),
	APPLE("apple", "apple");

	public final String fullSprite;
	public final String packedSprite;

	private ItemType(String fullSprite, String packedSprite) {
		this.fullSprite = fullSprite;
		this.packedSprite = packedSprite;
	}

	public static int count() {
		return values().length;
	}
}
