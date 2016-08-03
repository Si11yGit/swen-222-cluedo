package board;

/**
 * Class which contains an x,y coordinate for use in deciding where a tile is on
 * the board.
 *
 * @author clarkebenj1
 *
 */
public class Coordinate implements Comparable {
	private int x;
	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Coordinate) {
			Coordinate c = (Coordinate) o;
			if (this.x == c.getX() && this.y == c.getY()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Object o) {
		if (equals(o)) {
			return 0;
		} else {
			return -1;
		}

	}
}
