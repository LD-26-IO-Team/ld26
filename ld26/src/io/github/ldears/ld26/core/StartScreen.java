package io.github.ldears.ld26.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author dector
 */
public class StartScreen implements Screen, InputProcessor {

	private static final int FROM_X = 0;
	private static final int FROM_Y = 40;

	private GameImpl game;

	private int currentFrame;
	private boolean done;

	private SpriteBatch sb;
	private TextureRegion[] frames;
	private float[] frameTimes;
	private float[] framePauseTimes;

	private float currentFrameTime;

	public StartScreen(GameImpl game) {
		this.game = game;

		sb = new SpriteBatch();

		Texture tex = new Texture("data/images/start/start.png");

		frames = new TextureRegion[3];
		frames[0] = new TextureRegion(tex, 0, 0, 309, 199);
		frames[1] = new TextureRegion(tex, 0, 210, 309, 190);
		frames[2] = new TextureRegion(tex, 320, 0, 320, 400);

		frameTimes = new float[]{ 2, 2, 2 };
		framePauseTimes = new float[]{ 1, 1, 1 };

		currentFrameTime = -framePauseTimes[0];
	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (currentFrame < frames.length) {
			currentFrameTime += dt;

			if (currentFrameTime >= frameTimes[currentFrame]) {
				currentFrame++;

				if (currentFrame < frames.length) {
					currentFrameTime = -framePauseTimes[currentFrame];
				}
			}
		}

		sb.begin();

		// Fully visible frames
		for (int i = 0; i < currentFrame; i++) {
			drawFrame(i, 1);
		}

		// Partially visible frame
		if (currentFrame < frames.length) {
			drawFrame(currentFrame, currentFrameTime / frameTimes[currentFrame]);
		}

		sb.end();
	}

	private void drawFrame(int index, float alpha) {
		if (alpha < 0) return;
		sb.setColor(1, 1, 1, alpha);

		TextureRegion reg = frames[index];
		sb.draw(reg, FROM_X + reg.getRegionX(), Gdx.graphics.getHeight() - (FROM_Y + reg.getRegionY()) - 1 - reg.getRegionHeight(),
				reg.getRegionWidth(), reg.getRegionHeight());
	}

	@Override
	public void resize(int i, int i2) {
	}

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

	@Override
	public boolean keyDown(int i) {
		if (i == Input.Keys.SPACE || i == Input.Keys.ESCAPE) {
			if (currentFrame < frames.length) {
				currentFrameTime = frameTimes[currentFrame];
			} else {
				game.startGame();
			}
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
}
