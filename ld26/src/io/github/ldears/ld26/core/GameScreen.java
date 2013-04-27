package io.github.ldears.ld26.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import io.github.ldears.ld26.events.InputEventHandler;
import io.github.ldears.ld26.map.Tile;
import io.github.ldears.ld26.map.TileType;
import io.github.ldears.ld26.models.GameModel;
import io.github.ldears.ld26.render.Renderer;

import static io.github.ldears.ld26.map.TileType.*;

/**
 * @author dector
 */
public class GameScreen implements Screen, InputProcessor {

	private GameModel model;

	private Renderer renderer;

	public GameScreen() {
		model = new GameModel();

		// TODO mockup
		{
			TileType[][] tileTypeMap = {
					{ WALL_LEFT, CEIL, CEIL, CEIL, CEIL, WALL_MC, CEIL, CEIL, CEIL, CEIL, WALL_RIGHT },
					{ WALL_LEFT, EMPTY, EMPTY, EMPTY, EMPTY, WALL_MIDDLE, EMPTY, EMPTY, EMPTY, EMPTY, WALL_RIGHT },
					{ WALL_LEFT, EMPTY, EMPTY, EMPTY, EMPTY, WALL_MIDDLE, EMPTY, EMPTY, EMPTY, EMPTY, WALL_RIGHT },
					{ WALL_LEFT, EMPTY, EMPTY, EMPTY, EMPTY, WALL_MIDDLE, EMPTY, EMPTY, EMPTY, EMPTY, WALL_RIGHT },
					{ WALL_LEFT, EMPTY, EMPTY, EMPTY, EMPTY, WALL_MIDDLE, EMPTY, EMPTY, EMPTY, EMPTY, WALL_RIGHT },
					{ WALL_LEFT, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, WALL_RIGHT },
					{ WALL_LEFT, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, WALL_RIGHT },
					{ WALL_LEFT, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, WALL_RIGHT },
					{ WALL_CLB, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, CEIL, WALL_CRB }
			};

			Tile[][] tileMap = new Tile[tileTypeMap.length][tileTypeMap[0].length];
			for (int i = 0; i < tileMap.length; i++) {
				for (int j = 0; j < tileMap[0].length; j++) {
					tileMap[i][j] = new Tile();
					tileMap[i][j].type = tileTypeMap[i][j];
				}
			}

			model.setPlayerSpawn(1, 1);
			model.init(tileMap);
			renderer = new Renderer(model);
		}

	}

	@Override
	public void render(float dt) {
		model.update(dt);
		renderer.render(dt);
	}

	@Override
	public void resize(int i, int i2) {}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

	// Input

	@Override
	public boolean keyDown(int key) {
		switch (key) {
			case Input.Keys.LEFT:
				model.handleEvent(InputEventHandler.InputEvent.LEFT_DOWN);
				break;
			case Input.Keys.RIGHT:
				model.handleEvent(InputEventHandler.InputEvent.RIGHT_DOWN);
				break;
		}

		return true;
	}

	@Override
	public boolean keyUp(int key) {
		switch (key) {
			case Input.Keys.LEFT:
				model.handleEvent(InputEventHandler.InputEvent.LEFT_UP);
				break;
			case Input.Keys.RIGHT:
				model.handleEvent(InputEventHandler.InputEvent.RIGHT_UP);
				break;
		}

		return true;
	}

	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	@Override
	public boolean touchDown(int i, int i2, int i3, int i4) {
		return false;
	}

	@Override
	public boolean touchUp(int i, int i2, int i3, int i4) {
		return false;
	}

	@Override
	public boolean touchDragged(int i, int i2, int i3) {
		return false;
	}

	@Override
	public boolean mouseMoved(int i, int i2) {
		return false;
	}

	@Override
	public boolean scrolled(int i) {
		return false;
	}
}
