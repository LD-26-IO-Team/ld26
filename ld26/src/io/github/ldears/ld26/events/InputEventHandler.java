package io.github.ldears.ld26.events;

/**
 * @author dector
 */
public interface InputEventHandler {

	public void handleEvent(InputEvent event);

	public static enum InputEvent {
		LEFT_DOWN, LEFT_UP, RIGHT_DOWN, RIGHT_UP, Z, X;
	}
}
