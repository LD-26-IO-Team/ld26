package io.github.ldears.ld26.render;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author dector
 */
public class TexturedWalls {

	public Wall[] walls;

	public TexturedWalls(int size) {
		walls = new Wall[size];
	}

	public static class Wall {

		public WallTexture texture;
		public Rectangle rect;

		public Wall(WallTexture texture, int fromTileX, int fromTileY, int toTileX, int toTileY) {
			this.texture = texture;

			rect = new Rectangle();
			rect.x = fromTileX * Renderer.TILE_SIZE;
			rect.y = fromTileY * Renderer.TILE_SIZE;
			rect.width = (toTileX - fromTileX) * Renderer.TILE_SIZE;
			rect.height = (toTileY - fromTileY) * Renderer.TILE_SIZE;
		}
	}

	public static enum WallTexture {
		ROSES("roses_wall_texture");

		public final String name;

		private WallTexture(String name) {
			this.name = name;
		}
	}
}
