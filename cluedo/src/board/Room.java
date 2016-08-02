package board;

import java.util.ArrayList;

import parts.Player;

public class Room implements Square {
	private ArrayList<Coordinate> positions;
	private ArrayList<Player> players;
	private Room connection;
	private String name;

	public Room(String name, Room connection, ArrayList<Coordinate> positions) {
		this.name = name;
		this.connection = connection;
		this.positions = positions;
	}

	@Override
	public boolean enterable() {
		return true;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player p) {
		players.add(p);
	}

	public void removePlayer(Player p) {
		players.remove(p);
	}

	public Room getConnection() {
		return connection;
	}

	public void setConnection(Room connection) {
		this.connection = connection;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Coordinate> getPositions() {
		return positions;
	}
}
