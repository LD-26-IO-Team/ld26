package io.github.ldears.ld26.map;

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
}
