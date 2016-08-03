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

	public String toString() {
		if (vertical == true) {
			return "|";
		} else {
			return "_";
		}
	}

}
