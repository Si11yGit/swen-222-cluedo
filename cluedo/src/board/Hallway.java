package board;

import java.awt.Color;

import parts.Player;

/**
 * represents a hallway tile. essentially a mini room witha single coordinate , nmo name and space for one player
 *
 * @author clarkebenj1
 *
 */
public class Hallway implements Enterable {
	private Player player;
	private Coordinate position;

	public Hallway(Coordinate position) {
		this.position = position;
	}

	@Override
	public boolean enterable() {
		if (player == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get player currently in square
	 * @return player or null if none present
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * sets the Player currently in this square
	 * @param player
	 */
	public void addPlayer(Player player) {
		this.player = player;
	}

	public void removePlayer(Player player) {
		this.player = null;
	}

	/**
	 * gets the position of this square
	 * @return
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * returns the specific character that represents the hallways on the board
	 */
	@Override
	public String toString() {
		if (player == null) {
			return "#";
		} else {
			return player.toString();
		}
	}

	@Override
	public Color getColor() {
		return Color.orange;
	}
}
