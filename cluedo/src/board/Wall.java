package board;

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

}
