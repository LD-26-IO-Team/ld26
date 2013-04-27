package io.github.ldears.ld26.map;

import io.github.ldears.ld26.models.Action;

/**
 * @author dector
 */
public class Item extends GameObject {

	public ItemType itemType;

	public Item(int x, int y, String name) {
		super(x, y, name, ObjectType.ITEM);
	}

	@Override
	public Action getAction(boolean hasItem) {
		return Action.NONE;
	}

	// Sprite sprite;
	// Sprite partialSprite;
}
