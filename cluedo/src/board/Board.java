package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.Player;

public class Board {
	Square board[][] = new Square[24][25];
	char asciiBoard[][] = new char[48][50];
	ArrayList<Player> players;
	Map<String, Room> rooms = new HashMap<String, Room>();

	public Board(ArrayList<Player> players){
		this.players = players;
		setupBoard();
	}

	private void setupBoard() {
		ArrayList<Coordinate> locations = new ArrayList<Coordinate>();
		for(int x = 0; x < 7; x++) {
			for(int y = 0; y < 4; y++) {
				locations.add(new Coordinate(x,y));
			}
		}
		rooms.put("Study", new Room("Study", null, locations));


	}

	public void update() {

	}

	public void draw() {
		System.out.print("________ ___");
		System.out.print("|      |_|_|__________");
		System.out.print("|        |__________");
		System.out.print("|        |___________");
		System.out.print("|________|___");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
	}
}
