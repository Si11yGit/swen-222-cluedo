package board;

import java.util.ArrayList;

import parts.Player;

/**
 * class which represents a room and holds a name, set of Coordinates and player list inside the room
 *
 * @author clarkebenj1
 *
 */
public class Room implements Square {
	private ArrayList<Coordinate> positions;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Room tunnel;
	private Coordinate centre;
	private String name;
	private String id;			//used to shorten name on board

	public Room(String name, Coordinate centre, ArrayList<Coordinate> positions, String id) {
		this.name = name;
		this.centre = centre;
		this.positions = positions;
		this.id = id;
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

	public String getID() {
		return id;
	}

	public ArrayList<Coordinate> getPositions() {
		return positions;
	}

	public Room getTunnel() {
		return tunnel;
	}

	public void setTunnel(Room tunnel) {
		this.tunnel = tunnel;
	}
}
