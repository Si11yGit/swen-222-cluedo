package parts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.Main;

public class Card {
	private String name;
	private Player Owner;
	private ImageIcon icon;
	/**
	 * Create a new card with a given parameter
	 * 
	 * @param name
	 */
	public Card(String name){
		this.name = name;
		icon = makeImageIcon(name);
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

	private static ImageIcon makeImageIcon(String s){
		java.net.URL imageURL = getClass().getResource("cards/"+s+".png");

		ImageIcon icon = null;
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		} else {
			System.out.println("URL did not work");
		}
		return icon;
	}
	public ImageIcon getIcon() {
		
		return icon;
	}

	
}
