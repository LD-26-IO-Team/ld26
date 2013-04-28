package io.github.ldears.ld26.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import io.github.ldears.ld26.map.ItemType;
import io.github.ldears.ld26.map.TileType;
import io.github.ldears.ld26.models.PlayerDirection;

/**
 * @author dector
 */
public class ResLoader {

	private static final String IMAGES_DIR = "data/images/";
	private static final String FONTS_DIR = "data/fonts/";

//	public final TextureRegion background;

	public final BitmapFont font;

	public final TextureAtlas.AtlasRegion[] tiles;

	public final TextureAtlas.AtlasRegion[] items;
	public final TextureAtlas.AtlasRegion[] itemsPacked;

	public final TextureAtlas.AtlasRegion[] wallTextures;
	public final Array<TextureAtlas.AtlasRegion> cloudsTexture;
	public final TextureAtlas.AtlasRegion groundTexture;

	public final Animation[] player;

	public final TextureRegion darkBox;
	public final TextureRegion lightBox;
	public final TextureRegion trash;
	public final TextureRegion itemSelector[];

//	public final TextureAtlas.AtlasRegion[] objects;

	public ResLoader() {
		font = initFont();

//		background = new TextureRegion(
//				new Texture(Gdx.files.internal(IMAGES_DIR + "background.png")),
//				Renderer.SCREEN_WIDTH, Renderer.SCREEN_HEIGHT);

		TextureAtlas atlas = new TextureAtlas(IMAGES_DIR + "sprites.atlas");

		tiles = new TextureAtlas.AtlasRegion[TileType.count()];
		initTiles(atlas);

		items = new TextureAtlas.AtlasRegion[ItemType.count()];
		itemsPacked = new TextureAtlas.AtlasRegion[ItemType.count()];
		initObjects(atlas);

		player = new Animation[PlayerDirection.values().length];
		initPlayer(atlas);

		darkBox = atlas.findRegion("dark");
		lightBox = atlas.findRegion("light");
		trash = atlas.findRegion("trash");
		itemSelector = new TextureRegion[2];
		itemSelector[0] = atlas.findRegion("item_selector");
		itemSelector[1] = atlas.findRegion("item_selector_selected");


		TextureAtlas backgroundsAtlas = new TextureAtlas(IMAGES_DIR + "backgrounds.atlas");
		wallTextures = new TextureAtlas.AtlasRegion[TexturedWalls.WallTexture.values().length];
		initWallTextures(backgroundsAtlas);

		cloudsTexture = initClouds(backgroundsAtlas);

		groundTexture = backgroundsAtlas.findRegion("ground");
	}

	private Array<TextureAtlas.AtlasRegion> initClouds(TextureAtlas backgroundsAtlas) {
		return backgroundsAtlas.findRegions("cloud");
	}

	private void initWallTextures(TextureAtlas atlas) {
		for (TexturedWalls.WallTexture texture : TexturedWalls.WallTexture.values()) {
			wallTextures[texture.ordinal()] = atlas.findRegion(texture.name);
		}
	}

	private void initPlayer(TextureAtlas atlas) {
		for (PlayerDirection dir : PlayerDirection.values()) {
			player[dir.ordinal()] = new Animation(dir.animationTime, atlas.findRegions(dir.name));
		}
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

	private void initObjects(TextureAtlas atlas) {
		for (ItemType item : ItemType.values()) {
			items[item.ordinal()] = atlas.findRegion(item.fullSprite);
			itemsPacked[item.ordinal()] = atlas.findRegion(item.packedSprite);
		}
	}
}
