package io.github.ldears.ld26.sound;

/**
 * @author dector
 */
public enum Sounds {

	GET("get.mp3"),
	DROP("drop.mp3"),
	DOOR("door.mp3");

	public final String file;

	private Sounds(java.lang.String file) {
		this.file = file;
	}
}
