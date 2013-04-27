package io.github.ldears.ld26;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.ldears.ld26.core.GameImpl;

import static io.github.ldears.ld26.render.Renderer.*;

/**
 * @author dector
 */
public class DesktopLauncher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = APP_TITLE;
		config.width = SCREEN_WIDTH;
		config.height = SCREEN_HEIGHT;
		config.useGL20 = false;
		config.resizable = false;
		config.fullscreen = IS_FULLSCREEN;

		new LwjglApplication(new GameImpl(), config);
	}
}
