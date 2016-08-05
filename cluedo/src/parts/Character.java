package parts;

public class Character extends Card{


	private String name;
	private Card card;
	private Player player;

	public Character(String c) {
		super(c);
		this.name = c;
	}

	public String name() {
		return name;
	}

	public Card card() {
		return card;
	}

	public Player player() {
		return player;
	}

	public String toString() {
		return name.substring(name.indexOf(" ") + 1,name.indexOf(" ") + 2);
	}
}
