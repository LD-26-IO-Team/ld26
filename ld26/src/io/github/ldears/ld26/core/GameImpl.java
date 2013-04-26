package io.github.ldears.ld26.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * @author dector
 */
public class GameImpl extends Game {

	@Override
	public void create() {
		GameScreen screen = new GameScreen();

		Gdx.input.setInputProcessor(screen);
		setScreen(screen);
	}
}
