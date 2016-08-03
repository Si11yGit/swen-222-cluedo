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
		/*
		 * takes the positions of each room and creates a room object representing each
		 */
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
			for (int x = 1; x < 12; x += 2) {
				if(!((x == 11 || x == 1) && y == 47)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(7, 18);
		rooms.put("Conservatory", new Room("Conservatory", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 37; y < 48; y += 2) {
			for (int x = 17; x < 32; x += 2) {
				if(!((x == 17 || x == 19 || x == 29 || x == 31) && y == 41)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(45, 25);
		rooms.put("Ballroom", new Room("Ballroom", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 39; y < 48; y += 2) {
			for (int x = 37; x < 48; x += 2) {
				if(!(x == 47 && y == 39)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(43, 43);
		rooms.put("Kitchen", new Room("Kitchen", centre, locations));

		locations = new ArrayList<Coordinate>();
		for (int y = 21; y < 34; y += 2) {
			for (int x = 33; x < 48; x += 2) {
				if(!((x == 33 || x == 35 || x == 37) && y == 34)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(40, 27);
		rooms.put("Dining Room", new Room("Dining Room", centre, locations));

		/*
		 * combines the room coordinates into one list for use in hallway creation
		 */
		locations = new ArrayList<Coordinate>();
		for(Room room: rooms.values()) {
			locations.addAll(room.getPositions());
		}

		/*
		 * sets impassable block in the centre of the board
		 */
		for (int y = 17; y < 30; y++) {
			for (int x = 19; x < 28; x++) {
				board[x][y] = new Impassable(new Coordinate(x, y));
			}
		}

		/*
		 * sets impassable square around the exterior
		 */
		board[1][9] = new Impassable(new Coordinate(1, 9));
		board[1][13] = new Impassable(new Coordinate(1, 13));
		board[1][21] = new Impassable(new Coordinate(1, 21));
		board[1][23] = new Impassable(new Coordinate(1, 23));
		board[1][35] = new Impassable(new Coordinate(1, 35));
		board[1][39] = new Impassable(new Coordinate(1, 39));
		board[13][1] = new Impassable(new Coordinate(13, 1));
		board[17][1] = new Impassable(new Coordinate(17, 1));
		board[31][1] = new Impassable(new Coordinate(31, 1));
		board[35][1] = new Impassable(new Coordinate(35, 1));
		board[13][47] = new Impassable(new Coordinate(13, 47));
		board[35][47] = new Impassable(new Coordinate(35, 47));
		board[47][13] = new Impassable(new Coordinate(47, 13));
		board[47][17] = new Impassable(new Coordinate(47, 17));
		board[47][33] = new Impassable(new Coordinate(47, 33));
		board[47][37] = new Impassable(new Coordinate(47, 37));
		for(int i = 1; i < 48; i += 2) {
			if(i != 19 || i != 29) {
				board[i][49] = new Impassable(new Coordinate(i,49));
			}
		}

		/*
		 * adds hallway positions
		 */
		for(int x = 1; x < 48; x += 2) {
			for(int y = 1; y < 50; y += 2) {
				if(board[x][y] == null && !locations.contains(new Coordinate(x,y))) {
					board[x][y] = new Hallway(new Coordinate(x,y));
				}
			}
		}

		/*
		 * add doors
		 */
		int doorX = 13;
		int doorY = 8;
		String doorName = "Study";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 18;
		doorY = 9;
		doorName = "Hall";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 23;
		doorY = 14;
		doorName = "Hall";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 25;
		doorY = 14;
		doorName = "Hall";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 33;
		doorY = 12;
		doorName = "Lounge";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 14;
		doorY = 17;
		doorName = "Library";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 7;
		doorY = 22;
		doorName = "Library";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 3;
		doorY = 24;
		doorName = "Billiard Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 12;
		doorY = 31;
		doorName = "Billiard Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 10;
		doorY = 39;
		doorName = "Conservatory";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 16;
		doorY = 39;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 19;
		doorY = 34;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 29;
		doorY = 34;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 32;
		doorY = 39;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 39;
		doorY = 36;
		doorName = "Kitchen";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 32;
		doorY = 25;
		doorName = "Dining Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		doorX = 35;
		doorY = 18;
		doorName = "Dining Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));

		/*
		 * create walls
		 */

	}

	public void update() {

	}

	public void draw() {

	}
}
