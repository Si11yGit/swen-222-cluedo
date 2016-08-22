package board;

import java.awt.Color;

import parts.Player;

public class Wall implements Square {
	private boolean vertical;
	private Coordinate location;

	public Wall(boolean vertical, Coordinate location) {
		this.vertical = vertical;
		this.location = location;
	}

	public boolean enterable() {
		return false;
	}

	public Coordinate getPosition() {
		return location;
	}

	/**
	 * returns a specific Character which represents a wall on the board
	 */
	public String toString() {
		if (vertical == true) {
			return "|";
		} else {
			return "_";
		}
	}

	@Override
	public Color getColor() {
		return Color.DARK_GRAY;
	}

	@Override
	public Player getPlayer() {
		return null;
	}

}
