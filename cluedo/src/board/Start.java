package board;

import parts.Player;

public class Start implements Square {
	private Coordinate position;
	private Player player;

	public Start(Player player, Coordinate position) {
		this.player = player;
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
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Coordinate getPosition() {
		return position;
	}

}
