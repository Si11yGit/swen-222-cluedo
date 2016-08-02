package board;

public class Door implements Square {
	Square side1;
	Square side2;

	public Door(Square side1, Square side2) {
		this.side1 = side1;
		this.side2 = side2;
	}

	@Override
	public boolean enterable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Square

}
