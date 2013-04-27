package io.github.ldears.ld26.models;

/**
 * @author dector
 */
public enum PlayerDirection {
	LEFT("player_left", 	1f/20),
	RIGHT("player_right", 	1f/20),
	STAND("player", 		0.5f);

	public final String name;
	public final float animationTime;

	private PlayerDirection(String name, float animationTime) {
		this.name = name;
		this.animationTime = animationTime;
	}
}
