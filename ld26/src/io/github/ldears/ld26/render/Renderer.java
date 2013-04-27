package io.github.ldears.ld26.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.ldears.ld26.map.Tile;
import io.github.ldears.ld26.map.TileType;
import io.github.ldears.ld26.models.GameModel;

import java.awt.*;
import java.awt.geom.Point2D;

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
				TileType tile = tileMap[x][y].type;

				if (tile != TileType.EMPTY) {
					batch.draw(resLoader.tiles[tile.index],
							x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}

		// Draw player
		batch.draw(resLoader.player, ppos.x, ppos.y, TILE_SIZE, TILE_SIZE * 2);

		batch.end();

		drawHud();
	}

	private void drawHud() {
		int boxX = 10;
		int boxY = SCREEN_HEIGHT - 10;

		int boxWidth = 200;
		int boxHeight = 100;

		hudBatch.begin();

		hudBatch.setColor(1, 1, 1, 0.5f);
		hudBatch.draw(resLoader.darkBox, boxX, boxY - boxHeight, boxWidth, boxHeight);

		hudBatch.setColor(1, 1, 1, 1);
		resLoader.font.draw(hudBatch, "Inventory: n/a", boxX + 5, boxY - 5);

		hudBatch.end();
	}
}
