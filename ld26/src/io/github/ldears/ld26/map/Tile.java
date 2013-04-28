package io.github.ldears.ld26.map;

/**
 * @author dector
 */
public class Tile {
	public TileType type;
	GameObject content;
	boolean containered = false;
	
	public GameObject getContent() {
		return content;
	}

	public void setContent(GameObject content) {
		this.content = content;
	}
	
	public void addContainer(){
		containered = true;
	}
	
	public boolean hasContainer() {
		return containered;
	}
}
