package io.github.ldears.ld26.map;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dector
 */
public class Containter extends GameObject {

	int volume;
	int tileWidth;
	int tileHeight;
	boolean transparent;

	// Void if null
	// Sprite sprite;

	// Held objects
	private List<Item> items;

	public Containter(int x, int y, String name, int volume, int tileWidth,
			int tileHeight, boolean transparent) {
		super(x, y, name);
		this.volume = volume;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.items = new LinkedList<Item>();
		this.transparent = transparent;
	}

	public Iterable<Item> getContents() {
		return items;
	}

	public boolean add(Item i) {
		if (items.size() < volume - 1) {
			items.add(i);
			return true;
		} else
			return false;
	}

	public Item remove(Item i) {
		if (items.remove(i))
			return i;
		else
			return null;
	}
	
	@Override
	public boolean contains(Point p) {
		if (p.x >= tiledX && p.x < tiledX + tileWidth) 
			if (p.y >= tiledY && p.y < tiledY + tileHeight)
				return true;
		return false;
	}
}
