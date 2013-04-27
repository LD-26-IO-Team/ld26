package io.github.ldears.ld26.map;

import io.github.ldears.ld26.models.Action;

import java.awt.Point;

/**
 * @author dector
 */
public abstract class GameObject {

	String name;
	int tiledX;
	int tiledY;

	public ObjectType type;

	protected GameObject(int x, int y, String name, ObjectType type) {
		this.tiledX = x;
		this.tiledY = y;
		this.name = name;
		this.type = type;
	}

	public boolean contains(Point p) {
		if (p.x == tiledX && p.y == tiledY)
			return true;
		return false;
	}
	
	public Point coordinates() {
		return new Point(tiledX, tiledY);
	}

	public abstract Action getAction(boolean hasItem);
}
