package io.github.ldears.ld26.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import io.github.ldears.ld26.map.*;
import io.github.ldears.ld26.models.Action;
import io.github.ldears.ld26.models.GameModel;
import io.github.ldears.ld26.models.PlayerDirection;
import io.github.ldears.ld26.sound.SoundManager;

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

	public static final int ITEM_SIZE 			= (int) (TILE_SIZE * 0.75f);
	public static final int ITEM_PADDING		= (TILE_SIZE - ITEM_SIZE) / 2;

	public static final int INV_ICON_WIDTH	= 16;
	public static final int INV_ICON_HEIGHT	= 16;

	private GameModel model;

	private OrthographicCamera camera;
//	private SpriteBatch tmpBatch;
	private SpriteBatch batch;
	private SpriteBatch hudBatch;
	private ResLoader resLoader;

	private TexturedWalls walls;
	private CloudSystem cloudSystem;

	public Renderer(GameModel model, TexturedWalls walls) {
		this.model = model;
		this.walls = walls;

		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);

//		tmpBatch = new SpriteBatch();
		batch = new SpriteBatch();
		hudBatch = new SpriteBatch();

		resLoader = new ResLoader();

		cloudSystem = new CloudSystem(resLoader,
				max(model.getTileMapWidth() * TILE_SIZE, SCREEN_WIDTH),
				max(model.getTileMapHeight() * TILE_SIZE, SCREEN_HEIGHT));

		Gdx.gl.glClearColor(0.15f, 0.57f, 0.86f, 1);

		updateText(null);
	}

	private float time;

	public void render(float dt) {
		time += dt;

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Point ppos = model.getPlayerPosition();

		updateCamera(ppos);

		cloudSystem.update(dt);

		// Draw background
//		tmpBatch.begin();
//		tmpBatch.draw(resLoader.background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
//		tmpBatch.end();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		cloudSystem.render(batch);

		batch.disableBlending();

		for (int i = 0; i < max(SCREEN_WIDTH / TILE_SIZE, model.getTileMapWidth()); i++) {
			batch.draw(resLoader.groundTexture, i * TILE_SIZE, 0);
		}

		for (TexturedWalls.Wall wall : walls.walls) {
			if (wall != null) {

				int textureIndex = wall.texture.ordinal();
				TextureRegion region = resLoader.wallTextures[textureIndex];

				int width = region.getRegionWidth();
				int height = region.getRegionHeight();

				int countX = MathUtils.ceil(wall.rect.width / width);
				int countY = MathUtils.ceil(wall.rect.height / height);

				for (int i = 0; i < countX; i++) {
					for (int j = 0; j < countY; j++) {
						batch.draw(region, wall.rect.x + i * width,
								wall.rect.y + j * height);
					}
				}
			}
		}

		batch.enableBlending();

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

						batch.draw(spriteHolder[item.itemType.ordinal()], absX + ITEM_PADDING, absY, ITEM_SIZE, ITEM_SIZE);
					}
				}
			}
		}

		PlayerDirection playerDir = model.getPlayerDirection();
		Animation player = resLoader.player[playerDir.ordinal()];

		// Draw player
		batch.draw(player.getKeyFrame(time, true), ppos.x, ppos.y, TILE_SIZE, TILE_SIZE * 2);

		batch.end();

		drawHud();
	}

	private Vector3 lerpVector = new Vector3();

	private void updateCamera(Point ppos) {
		int camX = max(ppos.x, SCREEN_WIDTH / 2);
		int camY = max(ppos.y, SCREEN_HEIGHT / 2);

		lerpVector.set(camX, camY, 0);

		camera.position.lerp(lerpVector, 0.08f);
//		camera.position.set(camX, camY, 0);
		camera.update();
	}

	private int max(int a, int b) {
		return (a >= b) ? a : b;
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
			updateText();
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

		sb.append("Music volume: ");
		if (SoundManager.instance.isMuted()) {
			sb.append("[Muted]\n")
					.append("Press [M] to unmute");
		} else {
			sb.append(SoundManager.instance.getMusicVolume()).append("%\n")
					.append("Press [M] to mute");
		}

		if (text != null) { sb.append("\n\n").append(text).append("\n"); }

		this.text = sb.toString();
	}

	public void updateText() {
		Action newAction = model.getAvailableAction();
		prevAction = newAction;

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
}
