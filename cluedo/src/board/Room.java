package board;

import java.awt.Color;
import java.util.ArrayList;

import parts.Player;

/**
 * class which represents a room and holds a name, set of Coordinates and player list inside the room
 *
 * @author clarkebenj1
 *
 */
public class Room implements Enterable {
	private ArrayList<Coordinate> positions;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Door> doors = new ArrayList<Door>();
	private Room tunnel;
	private Coordinate centre;
	private String name;
	private String id;			//used to shorten name on board
	private Color colour;

	public Room(String name, Coordinate centre, ArrayList<Coordinate> positions, String id, Color colour) {
		this.name = name;
		this.centre = centre;
		this.positions = positions;
		this.id = id;
		this.colour = colour;
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

	public Coordinate getPosition() {
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

	public ArrayList<Door> doors() {
		return doors;
	}

	public void setDoor(Door door) {
		doors.add(door);
	}

	@Override
	public Color getColor() {
		return colour;
	}

	@Override
	public Player getPlayer() {
		//not used due to being replaced with larger method
		return null;
	}
}
