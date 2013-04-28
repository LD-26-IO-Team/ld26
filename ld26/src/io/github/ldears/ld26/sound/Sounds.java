package io.github.ldears.ld26.sound;

/**
 * @author dector
 */
public enum Sounds {

	GET("get.wav"),
	DROP("drop.wav");

	public final String file;

	private Sounds(java.lang.String file) {
		this.file = file;
	}
}
