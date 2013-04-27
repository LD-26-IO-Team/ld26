package io.github.ldears.ld26.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.ldears.ld26.map.*;
import io.github.ldears.ld26.models.Action;
import io.github.ldears.ld26.models.GameModel;

import java.awt.Point;

/**
 * @author dector
 */
public class Renderer {

	public static final String APP_TITLE 		= "LD #26 entry";
	public static final int SCREEN_WIDTH 		= 640;
	public static final int SCREEN_HEIGHT 		= 480;
	public static final boolean IS_FULLSCREEN 	= false;

	public static final int TILE_SIZE 			= 32;

	public static final int INV_ICON_WIDTH	= 16;
	public static final int INV_ICON_HEIGHT	= 16;

	private GameModel model;

	private OrthographicCamera camera;
//	private SpriteBatch tmpBatch;
	private SpriteBatch batch;
	private SpriteBatch hudBatch;
	private ResLoader resLoader;

	public Renderer(GameModel model) {
		this.model = model;

		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);

//		tmpBatch = new SpriteBatch();
		batch = new SpriteBatch();
		hudBatch = new SpriteBatch();

		resLoader = new ResLoader();

		Gdx.gl.glClearColor(0, 0.5f, 0, 1);

		updateText(null);
	}

	public void render(float dt) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Point ppos = model.getPlayerPosition();

		camera.position.set(ppos.x, ppos.y, 0);
		camera.update();

		// Draw background
//		tmpBatch.begin();
//		tmpBatch.draw(resLoader.background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
//		tmpBatch.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// Draw map
		Tile[][] tileMap = model.getTileMap();
		int mapWidth = tileMap.length;
		int mapHeight = tileMap[0].length;
		for (int x = 0; x < mapWidth; x++) {
			for (int y = 0; y < mapHeight; y++) {
				Tile tile = tileMap[x][y];
				TileType type = tile.type;

				int absX = x * TILE_SIZE;
				int absY = y * TILE_SIZE;

				if (type != TileType.EMPTY) {
					batch.draw(resLoader.tiles[type.index], absX, absY, TILE_SIZE, TILE_SIZE);
				}


				if (tile.getContent() != null && tile.getContent().type == ObjectType.CONTAINER) {
					Container container = (Container) tile.getContent();

					for (Item item : container.getContents()) {
						TextureAtlas.AtlasRegion[] spriteHolder = (container.isTransparent())
								? resLoader.items
								: resLoader.itemsPacked;

						batch.draw(spriteHolder[item.itemType.ordinal()], absX, absY);
					}
				}
			}
		}

		// Draw player
		batch.draw(resLoader.player, ppos.x, ppos.y, TILE_SIZE, TILE_SIZE * 2);

		batch.end();

		drawHud();
	}

	private static int BOX_X = 15;
	private static int BOX_Y = SCREEN_HEIGHT - 15;
	private static int BOX_PADDING = 5;

	private static String TEXT_INV = "Inventory:";
	private static String TEXT_DOOR = "Press [x] to use door";
	private static String TEXT_GET_ITEM = "Press [x] to get item";
	private static String TEXT_DROP_ITEM = "Press [x] to drop item";

	private String text;
	private Action prevAction;

	private void drawHud() {
		Action newAction = model.getAvailableAction();

		if (newAction != prevAction) {
			System.out.println(newAction);

			switch (newAction) {
				case USE_DOOR:
					updateText(TEXT_DOOR);
					break;
				case GET_ITEM:
					updateText(TEXT_GET_ITEM);
					break;
				case DROP_ITEM:
					updateText(TEXT_DROP_ITEM);
					break;
				default:
					updateText(null);
					break;
			}
		}

		Item invItem = model.getInventoryItem();
		ItemType itemType = (invItem != null) ? invItem.itemType : null;

		BitmapFont.TextBounds bounds = resLoader.font.getMultiLineBounds(text);

		int boxWidth = (int) (bounds.width + 2 * BOX_PADDING);
		int boxHeight = (int) (bounds.height + 2 * BOX_PADDING);

		if (itemType != null) {
			if (text.isEmpty()) boxWidth += INV_ICON_WIDTH;

			boxHeight += INV_ICON_HEIGHT + BOX_PADDING;
		}

		hudBatch.begin();

		hudBatch.setColor(1, 1, 1, 0.5f);
		hudBatch.draw(resLoader.darkBox, BOX_X, BOX_Y - boxHeight, boxWidth, boxHeight);

		if (bounds.width != 0 ) {
			hudBatch.setColor(1, 1, 1, 1);
			resLoader.font.drawMultiLine(hudBatch, text, BOX_X + BOX_PADDING, BOX_Y - BOX_PADDING);
		}

		if (itemType != null) {
			hudBatch.draw(resLoader.items[itemType.ordinal()], BOX_X + BOX_PADDING, BOX_Y - boxHeight + BOX_PADDING, INV_ICON_WIDTH, INV_ICON_HEIGHT);
		}

		hudBatch.end();
	}

	private void updateText(String text) {
		StringBuilder sb = new StringBuilder();

		if (text != null) { sb.append(text).append("\n"); }
//		sb.append(TEXT_INV);

		this.text = sb.toString();
	}
}
