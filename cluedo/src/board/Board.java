package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.Player;

public class Board {
	Square board[][] = new Square[49][51];
	char asciiboard[][] = new char[49][51];
	ArrayList<Player> players;
	Map<String, Room> rooms = new HashMap<String, Room>();

	public Board(ArrayList<Player> players) {
		this.players = players;
		setupBoard();
	}

	/**
	 * sets up the rooms and board for the game. This is hard coded because of
	 * the awkward shape and positioning of the rooms
	 */
	private void setupBoard() {
		ArrayList<Coordinate> locations = new ArrayList<Coordinate>();
		for (int y = 1; y < 8; y += 2) {
			for (int x = 1; x < 14; x += 2) {
				if (!(x == 13 && y == 1)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		Coordinate centre = new Coordinate(7, 5);
		rooms.put("Study", new Room("Study", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 1; y < 14; y += 2) {
			for (int x = 19; x < 30; x += 2) {
				locations.add(new Coordinate(x, y));
			}
		}
		centre = new Coordinate(35, 7);
		rooms.put("Hall", new Room("Hall", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 1; y < 12; y += 2) {
			for (int x = 35; x < 48; x += 2) {
				if (!((x == 35 || x == 47) && y == 1)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(39, 7);
		rooms.put("Lounge", new Room("Lounge", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 13; y < 22; y += 2) {
			for (int x = 1; x < 14; x += 2) {
				if (!((x == 13 && (y == 13 || y == 21)) || (x == 1 && (y == 13 || y == 21)))) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(7, 18);
		rooms.put("Library", new Room("Library", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 27; y < 36; y += 2) {
			for (int x = 1; x < 12; x += 2) {
				locations.add(new Coordinate(x, y));
			}
		}
		centre = new Coordinate(5, 31);
		rooms.put("Billiard Room", new Room("Billiard Room", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 41; y < 48; y += 2) {
			for (int x = 1; x < 14; x += 2) {
				if(!((x == 13 && (y == 13 || y == 21)) || (x == 1 && (y == 13 || y == 21)))) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(7, 18);
		rooms.put("Conservatory", new Room("Conservatory", centre, locations));

	}

	public void update() {

	}

	public void draw() {

	}
}
