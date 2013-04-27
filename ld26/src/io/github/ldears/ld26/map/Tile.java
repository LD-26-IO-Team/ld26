package io.github.ldears.ld26.map;

/**
 * @author dector
 */
public enum Tile {

	WALL_LEFT("wall_left.png"), WALL_RIGHT("wall_right.png"), WALL_MIDDLE("wall_middle.png"), CEIL("ceil.png");

	public final String tileName;

	private Tile(String tileName) {
		this.tileName = tileName;
	}

	public static final int WALL_MIDDLE_WIDTH 	= 0;
	public static final int TILE_SIZE 			= 0;
}
