package board;

/**
 * Class which contains an x,y coordinate for use in deciding where a tile is on the board.
 * @author clarkebenj1
 *
 */
public class Coordinate {
	private int x;
	private int y;

	public Coordinate (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
