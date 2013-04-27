package io.github.ldears.ld26.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author dector
 */
public class ResLoader {

	private static final String IMAGES_DIR = "data/images/";
	private static final int TILE_SIZE = 16;

	public final TextureRegion[][] tileTex;

	public ResLoader() {
		FileHandle spritesFile = Gdx.files.internal(IMAGES_DIR + "sprites.png");
		Texture spritesTexture = new Texture(spritesFile);

		tileTex = TextureRegion.split(spritesTexture, TILE_SIZE, TILE_SIZE);
	}
}
