package io.github.ldears.ld26.render;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.ldears.ld26.map.TileType;

/**
 * @author dector
 */
public class ResLoader {

	private static final String IMAGES_DIR = "data/images/";

	public final TextureAtlas.AtlasRegion[] tiles;
	public final TextureAtlas.AtlasRegion player;

	public ResLoader() {
		TextureAtlas atlas = new TextureAtlas(IMAGES_DIR + "sprites.atlas");

		tiles = new TextureAtlas.AtlasRegion[TileType.count()];
		initTiles(atlas);

		player = atlas.findRegion("player");
	}

	private void initTiles(TextureAtlas atlas) {
		for (TileType tile : TileType.values()) {
			if (tile != TileType.EMPTY) {
				tiles[tile.index] = atlas.findRegion(tile.name);
			}
		}
	}
}
