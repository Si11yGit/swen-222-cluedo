package parts;

import javax.swing.ImageIcon;

public class Card {
	private String name;
	private Player Owner;
	/**
	 * Create a new card with a given parameter
	 * 
	 * @param name
	 */
	public Card(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Player getOwner() {
		return Owner;
	}
	public void setOwner(Player owner) {
		Owner = owner;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Card [name=" + name + "]";
	}
	
	public ImageIcon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
