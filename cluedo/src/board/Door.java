package board;

/**
 * class which represent a door between a room and the hallway. holds the room and hallway tile which it connects
 * @author clarkebenj1
 *
 */
public class Door implements Square {
	Room room;
	Hallway hall;

	public Door(Room room, Hallway hall) {
		this.room = room;
		this.hall = hall;
	}

	@Override
	public boolean enterable() {
		return false;
	}

	/**
	 * gets the Room the door goes to
	 * @return
	 */
	public Square room() {
		return room;
	}

	/**
	 * returns the hallway square the door opens to
	 * @return
	 */
	public Square hall() {
		return hall;
	}

}
