package board;

public interface Square {

	/**
	 * checks whether characters can enter this square
	 * @return true or false
	 */
	public boolean enterable();

	/**
	 * creates string representation of Square
	 * @return
	 */
	public String toString();

}
