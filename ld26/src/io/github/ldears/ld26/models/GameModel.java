package io.github.ldears.ld26.models;

import io.github.ldears.ld26.events.InputEventHandler;
import io.github.ldears.ld26.map.Container;
import io.github.ldears.ld26.map.GameObject;
import io.github.ldears.ld26.map.Item;
import io.github.ldears.ld26.map.Tile;
import io.github.ldears.ld26.map.TileType;
import io.github.ldears.ld26.render.Renderer;

import java.awt.Point;

/**
 * @author dector
 */
public class GameModel implements InputEventHandler {
	private Tile[][] data;
	private Player player;
	private final int TILE_SIZE = Renderer.TILE_SIZE;
	private byte direction;
	/**
	 * Time passed since player position was last updated
	 */
	private float time;

	private class Player {
		Point pos;
		Container inventory;
		int velocity = TILE_SIZE * 3;
	}

	public GameModel() {
		player = new Player();
		player.inventory = new Container(0, 0, "inventory", 1, 0, 0, true);
	}

	public void setPlayerSpawn(int tiledX, int tiledY) {
		player.pos = new Point(tiledX * TILE_SIZE + TILE_SIZE / 2
				- TileType.PLAYER_WIDTH / 2, tiledY * TILE_SIZE);
	}

	public void init(Tile[][] tiles) {
		data = tiles;
	}

	public void update(float dt) {
		time += dt;
		int tiledX = player.pos.x / TILE_SIZE;
		int tiledY = player.pos.y / TILE_SIZE;
		int tiledRX = (player.pos.x + TileType.PLAYER_WIDTH) / TILE_SIZE;
		int dist = (int) (time * player.velocity);
		if (dist > 0) {
			time = 0;
			int playerX = player.pos.x;
			int playerRX = playerX + TileType.PLAYER_WIDTH;
			if ((direction & 2) != 0) {
				if (((playerRX + dist) / TILE_SIZE) > tiledX)
					if (data[tiledX + 1][tiledY].type == TileType.WALL_RIGHT)
						return;
				if (data[tiledRX][tiledY].type == TileType.WALL_MIDDLE)
					if (playerRX + dist > tiledRX * TILE_SIZE + (TILE_SIZE - TileType.WALL_MIDDLE_WIDTH) / 2)
						return;
				player.pos.move(playerX + dist, player.pos.y);

			}
			if ((direction & 1) != 0) {
				if (((playerX - dist) / TILE_SIZE) < tiledX)
					if (data[tiledX - 1][tiledY].type == TileType.WALL_LEFT)
						return;
				if (data[tiledX][tiledY].type == TileType.WALL_MIDDLE)
					if (playerX - dist < tiledX * TILE_SIZE + (TILE_SIZE + TileType.WALL_MIDDLE_WIDTH) / 2)
						return;
				player.pos.move(player.pos.x - dist, player.pos.y);
			}
		}
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
		switch (event) {
		case LEFT_DOWN:
			direction |= 1;
			break;
		case LEFT_UP:
			direction ^= 1;
			break;
		case RIGHT_DOWN:
			direction |= 2;
			break;
		case RIGHT_UP:
			direction ^= 2;
			break;
		case X:
			break;
		case Z:
			break;
		}

		System.out.println("GameModel: " + event + " received");
	}
}
