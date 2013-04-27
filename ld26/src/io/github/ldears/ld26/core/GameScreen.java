package io.github.ldears.ld26.core;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import io.github.ldears.ld26.models.GameModel;

import static io.github.ldears.ld26.events.InputEventHandler.InputEvent;

/**
 * @author dector
 */
public class GameScreen implements Screen, InputProcessor, ReactionEventHandler {

	private GameModel model;

	private Renderer renderer;

	public GameScreen() {
		model = new GameModel(this);
		renderer = new Renderer(model);
	}

	@Override
	public void render(float dt) {
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
		if (key == Input.Keys.SPACE) {
			model.handleEvent(InputEvent.TEST);
		}

		return true;
	}

	@Override
	public boolean keyUp(int i) {
		return false;
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

	// Gameplay reaction event

	@Override
	public void handleEvent(ReactionEvent event) {

		// TODO MOCKUP
		System.out.println("GameScreen: " + event + " received");
	}
}
