package board;

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

}
