package io.github.ldears.ld26.map;

/**
 * @author dector
 */
public enum TileType {

	EMPTY(-1, -1),
	CEIL(0, 0),
	WALL_LEFT(1, 0), 
	WALL_RIGHT(2, 0),
	WALL_MIDDLE(3, 0);

	public static final int WALL_MIDDLE_WIDTH 	= 4;
	public static final int PLAYER_WIDTH 		= 2*12;

	public final int x;
	public final int y;

	private TileType(int column, int row) {
		this.x = row;
		this.y = column;
	}
}
