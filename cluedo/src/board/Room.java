package board;

import java.util.ArrayList;

import parts.Player;

public class Room implements Square {
	private ArrayList<Coordinate> positions;
	private ArrayList<Player> players;
	private Coordinate centre;
	private String name;

	public Room(String name, Coordinate centre, ArrayList<Coordinate> positions) {
		this.name = name;
		this.centre = centre;
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

	public Coordinate getCentre() {
		return centre;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Coordinate> getPositions() {
		return positions;
	}
}
