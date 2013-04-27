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

	private GameModel model;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ResLoader resLoader;

	public Renderer(GameModel model) {
		this.model = model;

		camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		camera.position.set(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 0);

		batch = new SpriteBatch();

		resLoader = new ResLoader();
	}

	public void render(float dt) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Point ppos = model.getPlayerPosition();

		camera.position.set(ppos.x, ppos.y, 0);
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// Draw map

		Tile[][] tileMap = model.getTileMap();
		int mapHeight = tileMap.length;
		for (int y = mapHeight - 1; y >= 0; y--) {
			for (int x = 0; x < tileMap[y].length; x++) {
				TileType tile = tileMap[y][x].type;

				if (tile != TileType.EMPTY) {
					batch.draw(resLoader.tiles[tile.index],
							x * TILE_SIZE, (mapHeight - y - 1) * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}

		// Draw player

		batch.draw(resLoader.player, ppos.x, ppos.y, TILE_SIZE, TILE_SIZE * 2);

		batch.end();
	}
}
