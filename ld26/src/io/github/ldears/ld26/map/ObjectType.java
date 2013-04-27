package io.github.ldears.ld26.map;

/**
 * @author dector
 */
public enum ObjectType {
	DOOR("door", ""),
	WINDOW("", ""),
	ITEM("", ""),
	CONTAINER("", "");

	public final String name;
	public final String namePacked;

	private ObjectType(String name, String namePacked) {
		this.name = name;
		this.namePacked = namePacked;
	}
}
