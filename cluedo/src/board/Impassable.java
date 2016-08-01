package board;

public class Impassable implements Square {

	@Override
	public boolean enterable() {
		return false;
	}

}
