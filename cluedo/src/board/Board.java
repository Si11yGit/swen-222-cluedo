package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.Player;

public class Board {
	Square board[][] = new Square[48][50];
	char asciiboard[][] = new char[48][50];
	ArrayList<Player> players;
	Map<String, Room> rooms = new HashMap<String, Room>();

	public Board(ArrayList<Player> players) {
		this.players = players;
		setupBoard();
	}

	private void setupBoard() {
		ArrayList<Coordinate> locations = new ArrayList<Coordinate>();
		for (int y = 1; y < 8; y += 2) {
			for (int x = 1; x < 14; x += 2) {
				if (!(x == 13 && y == 1)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		Coordinate centre = new Coordinate(4, 5);
		rooms.put("Study", new Room("Study", centre, locations));

	}

	public void update() {

	}

	public void draw() {

	}
}
