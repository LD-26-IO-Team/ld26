package io.github.ldears.ld26.map;

import io.github.ldears.ld26.models.Action;

/**
 * @author dector
 */
public class Item extends GameObject {

	protected Item(int x, int y, String name) {
		super(x, y, name, ObjectType.ITEM);
	}

	@Override
	public Action getAction(boolean hasItem) {
		return null;
	}

	// Sprite sprite;
	// Sprite partialSprite;
}
