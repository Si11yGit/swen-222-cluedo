package parts;

public class Character extends Card{


	private String name;
	private Card card;
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

	public Player player() {
		return player;
	}
}
