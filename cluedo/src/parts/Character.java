package parts;

import board.Piece;

public class Character extends Card{


	private String name;
	private Card card;
	private Piece piece;
	private Player player;
	
	public Character(String name) {
		super(name);
		this.name = name; 
	}

	public String name() {
		return name;
	}

	public Card card() {
		return card;
	}

	public Piece piece() {
		return piece;
	}

	public Player player() {
		return player;
	}
}
