package io.github.ldears.ld26.map;

import io.github.ldears.ld26.models.Action;

/**
 * @author dector
 */
public class Door extends GameObject {

	Door pairedDoor;
	public static final int height 	= 2;
	public static final int width 	= 1;
	
	public Door(int x, int y, String name) {
		super(x, y, name);
	}

	// Sprite closedSprite;
	// Sprite openedSprite;
	
	public Door getPairedDoor() {
		return pairedDoor;
	}

	public void setPairedDoor(Door pairedDoor) {
		this.pairedDoor = pairedDoor;
	}

	@Override
	public Action getAction(boolean hasItem) {
		return Action.USE_DOOR;
	}

}
