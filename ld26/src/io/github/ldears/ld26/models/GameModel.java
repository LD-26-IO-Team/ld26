package io.github.ldears.ld26.models;

import io.github.ldears.ld26.events.InputEventHandler;
import io.github.ldears.ld26.map.Container;
import io.github.ldears.ld26.map.GameObject;
import io.github.ldears.ld26.map.Item;
import io.github.ldears.ld26.map.Tile;
import io.github.ldears.ld26.render.Renderer;

import java.awt.Point;

/**
 * @author dector
 */
public class GameModel implements InputEventHandler {
	private Tile[][] data;
	private Player player;
	private final int TILE_SIZE = Renderer.TILE_SIZE;
	
	private class Player {
		Point pos;
		Container inventory;
		int velocity = TILE_SIZE;
	}
	
	// Work here with model

	public GameModel() {
		player = new Player();
		player.inventory = new Container(0, 0, "inventory", 1, 0, 0, true);
	}

	public void setPlayerSpawn(int tiledX, int tiledY) {
		player.pos = new Point(tiledX * TILE_SIZE, tiledY * TILE_SIZE);
	}

	public void init(Tile[][] tiles) {
		data = tiles;
	}

	public void update(float dt) {

	}

	public Point getPlayerPosition() {
		return player.pos;
	}

	public Tile[][] getTileMap() {
		return data;
	}

	public Action getAvailableAction() {
		int tiledX = player.pos.x / TILE_SIZE;
		int tiledY = player.pos.y / TILE_SIZE;
		Tile ofInterest = data[tiledX][tiledY];
		GameObject c = ofInterest.getContent();
		return c.getAction(player.inventory.isFull());
	}

	public Item getInventoryItem() {
		return player.inventory.poke();
	}

	@Override
	public void handleEvent(InputEvent event) {

		// TODO MOCKUP
		System.out.println("GameModel: " + event + " received");
//		reactionHandler.handleEvent(ReactionEvent.TEST);
	}
}
