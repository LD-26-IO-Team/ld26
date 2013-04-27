package io.github.ldears.ld26.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import io.github.ldears.ld26.map.TileType;

/**
 * @author dector
 */
public class ResLoader {

	private static final String IMAGES_DIR = "data/images/";
	private static final String FONTS_DIR = "data/fonts/";

//	public final TextureRegion background;

	public final BitmapFont font;

	public final TextureAtlas.AtlasRegion[] tiles;
	public final TextureAtlas.AtlasRegion player;

	public final TextureRegion darkBox;

//	public final TextureAtlas.AtlasRegion[] objects;

	public ResLoader() {
		font = initFont();

//		background = new TextureRegion(
//				new Texture(Gdx.files.internal(IMAGES_DIR + "background.png")),
//				Renderer.SCREEN_WIDTH, Renderer.SCREEN_HEIGHT);

		TextureAtlas atlas = new TextureAtlas(IMAGES_DIR + "sprites.atlas");

		tiles = new TextureAtlas.AtlasRegion[TileType.count()];
		initTiles(atlas);

		player = atlas.findRegion("player");

		darkBox = atlas.findRegion("dark");
	}

	private BitmapFont initFont() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONTS_DIR + "visitor.ttf"));

		BitmapFont font = generator.generateFont(18);
		generator.dispose();

		return font;
	}

	private void initTiles(TextureAtlas atlas) {
		for (TileType tile : TileType.values()) {
			if (tile != TileType.EMPTY) {
				tiles[tile.index] = atlas.findRegion(tile.name);
			}
		}
	}
}
