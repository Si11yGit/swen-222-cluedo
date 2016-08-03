package board;

import parts.Player;

/**
 * represents a hallway tile. essentially a mini room witha single coordinate , nmo name and space for one player
 *
 * @author clarkebenj1
 *
 */
public class Hallway implements Square {
	private boolean entrance;
	private Player player;
	private Coordinate position;

	public Hallway(boolean entrance, Coordinate position) {
		this.entrance = entrance;
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
	 * returns whether hallway square is the entry to a room
	 *
	 * @return true or false
	 */
	public boolean entryway() {
		return entrance;
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
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * gets the position of this square
	 * @return
	 */
	public Coordinate getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return " ";
	}
}
