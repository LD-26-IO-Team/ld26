package io.github.ldears.ld26.map;

import io.github.ldears.ld26.models.Action;

/**
 * @author dector
 */
public class Window extends GameObject {
	
	private boolean closed = true;

	public Window(int x, int y, String name) {
		super(x, y, name);
	}

	// Sprite closedSprite;
	// Sprite openedSprite;
	
	public void act() {
		closed = !closed;
	}

	@Override
	public Action getAction() {
		if (closed) return Action.OPEN_WINDOW;
		else return Action.CLOSE_WINDOW;
	}
}
