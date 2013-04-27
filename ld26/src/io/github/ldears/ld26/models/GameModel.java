package io.github.ldears.ld26.models;

import io.github.ldears.ld26.events.InputEventHandler;
import io.github.ldears.ld26.map.Item;
import io.github.ldears.ld26.map.Tile;

import java.awt.geom.Point2D;

/**
 * @author dector
 */
public class GameModel implements InputEventHandler {

	// Work here with model

	private Tile[][] tileMap;

	public GameModel() {
	}

	public void setPlayerSpawn(int x, int y) {

	}

	public void init(Tile[][] tiles) {
		this.tileMap = tiles;
	}

	public void update(float dt) {

	}

	public Point2D getPlayerPosition() {
		return null;
	}

	public Tile[][] getTileMap() {
		return tileMap;
	}

	public Action getAvailableAction() {
		return null;
	}

	public Item getInventoryItem() {
		return null;
	}

	@Override
	public void handleEvent(InputEvent event) {

		// TODO MOCKUP
		System.out.println("GameModel: " + event + " received");
//		reactionHandler.handleEvent(ReactionEvent.TEST);
	}
}
