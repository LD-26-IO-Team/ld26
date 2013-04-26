package io.github.ldears.ld26.core;

import io.github.ldears.ld26.models.GameModel;

/**
 * @author dector
 */
public class Renderer {

	public static final String APP_TITLE 	= "LD #26 entry";
	public static final int SCREEN_WIDTH 	= 640;
	public static final int SCREEN_HEIGHT 	= 480;
	public static final boolean IS_FULLSCREEN 	= false;

	private GameModel model;

	public Renderer(GameModel model) {
		this.model = model;
	}

	public void render(float dt) {
		// Render here

		// TODO MOCKUP
		System.out.println("Renderer: dt = " + dt);
	}
}
