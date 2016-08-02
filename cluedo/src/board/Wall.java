package board;

public class Wall implements Square {
	private boolean vertical;

	public Wall(boolean vertical) {
		this.vertical = vertical;
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
