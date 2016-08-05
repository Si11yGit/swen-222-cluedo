package board;

/**
 * class to use in array to present impassable tiles
 * @author clarkebenj1
 *
 */
public class Impassable implements Square {
	private Coordinate position;

	public Impassable(Coordinate position) {
		this.position = position;
	}


	public Coordinate getPosition() {
		return position;
	}

	@Override
	public boolean enterable() {
		return false;
	}

	public String toString() {
		return "@";
	}

}
