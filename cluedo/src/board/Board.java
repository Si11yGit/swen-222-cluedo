package board;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.Player;

public class Board {
	public static final int BOARD_WIDTH = 49;
	public static final int BOARD_HEIGHT = 51;
	private Square board[][] = new Square[BOARD_WIDTH][BOARD_HEIGHT];
	private char asciiboard[][] = new char[BOARD_WIDTH][BOARD_HEIGHT];

	private ArrayList<Player> players;
	private Map<String, Room> rooms = new HashMap<String, Room>();
	private Map<Coordinate, Square> coordinates = new HashMap<Coordinate,Square>();

	public Board() {
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
		setupRooms();

		/*
		 * combines the room coordinates into one list for use in hallway creation
		 */
		ArrayList<Coordinate> locations = new ArrayList<Coordinate>();
		for(Room room: rooms.values()) {
			locations.addAll(room.getPositions());
		}

		/*
		 * sets impassable block in the centre of the board
		 */
		setupCenter();

		/*
		 * sets impassable square around the exterior
		 */
		setupExterior();

		/*
		 * adds hallway positions
		 */
		setupHallways(locations);

		/*
		 * add doors
		 */
		setupDoors();

		/*
		 * create walls
		 */
		setupWalls();
		
		for(int x = 0; x < BOARD_WIDTH; x++) {
			for(int y = 0; y < BOARD_HEIGHT; y++) {
				if (board[x][y] != null) {
					coordinates.put(new Coordinate(x,y), board[x][y]);
				} else {
					for(Room r: rooms.values()) {
						if (r.getPositions().contains(new Coordinate(x,y))) {
							coordinates.put(new Coordinate(x,y), r);
						}
					}
				}
			}
		}

	}

	/**
	 * sets up each room. this is hard coded due to the design of the board
	 */
	public void setupRooms() {
		ArrayList<Coordinate> locations = new ArrayList<Coordinate>();
		for (int y = 1; y < 8; y += 2) {
			for (int x = 1; x < 14; x += 2) {
				if (!(x == 13 && y == 1)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		Coordinate centre = new Coordinate(7, 5);
		rooms.put("Study", new Room("Study", centre, locations, "Stdy", Color.BLUE));

		locations = new ArrayList<Coordinate>();
		for (int y = 1; y < 14; y += 2) {
			for (int x = 19; x < 30; x += 2) {
				locations.add(new Coordinate(x, y));
			}
		}
		centre = new Coordinate(25, 7);
		rooms.put("Hall", new Room("Hall", centre, locations, "Hall", Color.GREEN));

		locations = new ArrayList<Coordinate>();
		for (int y = 1; y < 12; y += 2) {
			for (int x = 35; x < 48; x += 2) {
				if (!((x == 35) && y == 1)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(39, 7);
		rooms.put("Lounge", new Room("Lounge", centre, locations, "Lng", Color.RED));

		locations = new ArrayList<Coordinate>();
		for (int y = 13; y < 22; y += 2) {
			for (int x = 1; x < 14; x += 2) {
				if (!((x == 13 && (y == 13 || y == 21)) || (x == 1 && (y == 13 || y == 21)))) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(7, 18);
		rooms.put("Library", new Room("Library", centre, locations, "Lib", Color.YELLOW));

		locations = new ArrayList<Coordinate>();
		for (int y = 25; y < 34; y += 2) {
			for (int x = 1; x < 12; x += 2) {
				locations.add(new Coordinate(x, y));
			}
		}
		centre = new Coordinate(5, 31);
		rooms.put("Billiard Room", new Room("Billiard Room", centre, locations, "Bill", Color.CYAN));

		locations = new ArrayList<Coordinate>();
		for (int y = 39; y < 48; y += 2) {
			for (int x = 1; x < 12; x += 2) {
				if(!((x == 11 || x == 1) && y == 39)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(7, 45);
		rooms.put("Conservatory", new Room("Conservatory", centre, locations, "Con", Color.MAGENTA));
		rooms.get("Conservatory").setTunnel(rooms.get("Lounge"));
		rooms.get("Lounge").setTunnel(rooms.get("Conservatory"));

		locations = new ArrayList<Coordinate>();
		for (int y = 35; y < 48; y += 2) {
			for (int x = 17; x < 32; x += 2) {
				if(!((x == 17 || x == 19 || x == 29 || x == 31) && y == 47)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(25, 43);
		rooms.put("Ballroom", new Room("Ballroom", centre, locations,"Ball", Color.PINK));

		locations = new ArrayList<Coordinate>();
		for (int y = 37; y < 48; y += 2) {
			for (int x = 37; x < 48; x += 2) {
				if(!(x == 47 && y == 37)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(43, 43);
		rooms.put("Kitchen", new Room("Kitchen", centre, locations, "Kitch", Color.WHITE));

		rooms.get("Kitchen").setTunnel(rooms.get("Study"));
		rooms.get("Study").setTunnel(rooms.get("Kitchen"));

		locations = new ArrayList<Coordinate>();
		for (int y = 19; y < 32; y += 2) {
			for (int x = 33; x < 48; x += 2) {
				if(!((x == 33 || x == 35 || x == 37) && y == 31)) {
					locations.add(new Coordinate(x, y));
				}
			}
		}
		centre = new Coordinate(40, 27);
		rooms.put("Dining Room", new Room("Dining Room", centre, locations, "Dining", new Color(165,42,42)));
	}

	/**
	 * sets up central impassable block
	 */
	public void setupCenter() {
		for (int y = 17; y < 30; y++) {
			for (int x = 19; x < 28; x++) {
				board[x][y] = new Impassable(new Coordinate(x, y));
			}
		}
	}

	/**
	 * initializes walls
	 */
	public void setupWalls() {
		for(int x = 1; x < 48; x += 2) {
			for(int y = 1; y < 50; y += 2) {
				if (x < 2) {
					board[x-1][y]  = new Wall(true, new Coordinate(x-1,y));
				} else {
					if (board[x][y] == null) {

					} else if (board[x-1][y] != null) {

					} else if((board[x][y] == null && board[x-2][y] != null) || (board[x][y] != null && board[x-2][y] == null)&& board[x-1][y] == null) {
						board[x-1][y]  = new Wall(true, new Coordinate(x-1,y));
					} else if(!board[x][y].getClass().equals(board[x-2][y].getClass()) && board[x-1][y] == null) {
						board[x-1][y]  = new Wall(true, new Coordinate(x-1,y));
					}
				}

				if (x > 46) {
					board[x+1][y]  = new Wall(true, new Coordinate(x+1,y));
				} else {
					if (board[x][y] == null) {

					} else if (board[x+1][y] != null) {

					} else if((board[x][y] == null && board[x+2][y] != null) || (board[x][y] != null && board[x+2][y] == null)) {
						board[x+1][y]  = new Wall(true, new Coordinate(x+1,y));
					} else if(!board[x][y].getClass().equals(board[x+2][y].getClass()) && board[x+1][y] == null) {
						board[x+1][y]  = new Wall(true, new Coordinate(x+1,y));
					}
				}

				if (y < 2) {
					board[x][y-1]  = new Wall(false, new Coordinate(x,y-1));
				} else {
					if (board[x][y] == null) {

					} else if (board[x][y-1] != null) {

					}else if((board[x][y] == null && board[x][y-2] != null) || (board[x][y] != null && board[x][y-2] == null)) {
						board[x][y-1]  = new Wall(false, new Coordinate(x,y-1));
					} else if(!board[x][y].getClass().equals(board[x][y-2].getClass()) && board[x][y-1] == null) {
						board[x][y-1]  = new Wall(false, new Coordinate(x,y-1));
					}
				}

				if (y > 48) {
					board[x][y-1]  = new Wall(false, new Coordinate(x,y-1));
				} else if (board[x][y+1] != null) {

				} else {
					if (board[x][y] == null) {

					} else if((board[x][y] == null && board[x][y+2] != null) || (board[x][y] != null && board[x][y+2] == null)) {
						board[x][y+1]  = new Wall(false, new Coordinate(x,y+1));
					} else if(!board[x][y].getClass().equals(board[x][y+2].getClass()) && board[x-1][y+1] == null) {
						board[x][y+1]  = new Wall(false, new Coordinate(x,y+1));
					}
				}


			}
		}
	}

	/**
	 * sets up impassable squares on the board exterior positions
	 */
	public void setupExterior() {
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
			if(i != 19 && i != 29) {
				board[i][49] = new Impassable(new Coordinate(i,49));
			}
		}
	}

	/**
	 * adds hallway squares to board
	 */
	public void setupHallways(ArrayList<Coordinate> locations) {
		for(int x = 1; x < 48; x += 2) {
			for(int y = 1; y < 50; y += 2) {
				if(board[x][y] == null && !locations.contains(new Coordinate(x,y))) {
					board[x][y] = new Hallway(new Coordinate(x,y));
				}
			}
		}
	}

	/**
	 * adds doors to the board
	 */
	public void setupDoors() {
		int doorX = 13;
		int doorY = 8;
		String doorName = "Study";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 18;
		doorY = 9;
		doorName = "Hall";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 23;
		doorY = 14;
		doorName = "Hall";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 25;
		doorY = 14;
		doorName = "Hall";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 35;
		doorY = 12;
		doorName = "Lounge";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 14;
		doorY = 17;
		doorName = "Library";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 7;
		doorY = 22;
		doorName = "Library";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 3;
		doorY = 24;
		doorName = "Billiard Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 12;
		doorY = 31;
		doorName = "Billiard Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 10;
		doorY = 39;
		doorName = "Conservatory";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 16;
		doorY = 39;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 19;
		doorY = 34;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 29;
		doorY = 34;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 32;
		doorY = 39;
		doorName = "Ballroom";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 39;
		doorY = 36;
		doorName = "Kitchen";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 32;
		doorY = 25;
		doorName = "Dining Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
		doorX = 35;
		doorY = 18;
		doorName = "Dining Room";
		board[doorX][doorY] = new Door(rooms.get(doorName), (Hallway)board[doorX][doorY], new Coordinate(doorX,doorY));
		rooms.get(doorName).setDoor((Door)board[doorX][doorY]);
	}

	/**
	 * Updates the board
	 */
	public void update() {
		for(int x = 0; x <= 48; x ++) {
			for(int y = 0; y <= 50; y++) {
				if (board[x][y] == null) {
					asciiboard[x][y] = ' ';
				} else {
					asciiboard[x][y] = board[x][y].toString().charAt(0);
				}
			}
		}

		for(Room room: rooms.values()) {
			int nameStart = room.getCentre().getX() - room.getID().length()/2;
			int y = room.getCentre().getY();
			//places name of room in centre of room
			for(int x = nameStart; x < nameStart + room.getID().length(); x++) {
				asciiboard[x][y] = room.getID().toCharArray()[x-nameStart];
			}
			//places player counters in room
			for(int i = 0; i < room.getPlayers().size(); i++) {
				asciiboard[room.getPositions().get(i).getX()][room.getPositions().get(i).getY()] = room.getPlayers().get(i).toString().charAt(0);
			}
		}
		draw();
	}

	/**
	 * Draws the board
	 */
	public void draw() {
		for(int y = 0; y <= 50; y++) {
			for(int x = 0; x <= 48; x ++) {
				System.out.print(asciiboard[x][y]);
			}
			System.out.print("\n");
		}
	}

	/**
	 * checks whether a coordinate belongs to a room
	 * @param c
	 * @return
	 */
	public boolean isInRoom(Coordinate c) {
		for(Room r: rooms.values()) {
			if(r.getPositions().contains(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns the room a coordinate belongs to or null if it doesn't belong to a room
	 * @param c
	 * @return
	 */
	public Room getRoom(Coordinate c) {
		for(Room r: rooms.values()) {
			if(r.getPositions().contains(c)) {
				return r;
			}
		}
		return null;
	}
	
	public Map<String, Room> getRooms() {
		return rooms;
	}

	public Square[][] getBoardArray() {
		return board;
	}
}
