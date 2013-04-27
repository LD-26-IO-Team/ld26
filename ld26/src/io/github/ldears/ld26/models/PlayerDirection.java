package io.github.ldears.ld26.models;

/**
 * @author dector
 */
public enum PlayerDirection {
	LEFT("player_left"), RIGHT("player_right"), STAND("player");

	public final String name;

	private PlayerDirection(String name) {
		this.name = name;
	}
}
