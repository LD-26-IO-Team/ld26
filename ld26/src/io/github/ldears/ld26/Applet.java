package io.github.ldears.ld26;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;
import io.github.ldears.ld26.core.GameImpl;

/**
 * @author dector
 */
public class Applet extends LwjglApplet {
	public Applet() {
		super(new GameImpl(), false);
	}
}
