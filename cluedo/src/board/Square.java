package board;

import java.awt.Color;

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


	public Coordinate getPosition();

	/**
	 * get color of the square for use with gui
	 * @return
	 */
	public Color getColor();

}
