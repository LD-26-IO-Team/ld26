package io.github.ldears.ld26.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * @author dector
 */
public class GameImpl extends Game {

	@Override
	public void create() {
		StartScreen screen = new StartScreen(this);

		Gdx.input.setInputProcessor(screen);
		setScreen(screen);
	}

	public void startGame() {
		GameScreen screen = new GameScreen();

		Gdx.input.setInputProcessor(screen);
		setScreen(screen);
	}
}
