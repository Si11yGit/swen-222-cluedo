package parts;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.Main;
import ui.Frame;

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
	
	/**
	 * returns the name of a player
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the name of a player
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns the owner of a card
	 * @return
	 */
	public Player getOwner() {
		return Owner;
	}
	
	/**
	 * sets the owner of a card
	 * @param owner
	 */
	public void setOwner(Player owner) {
		Owner = owner;
	}
	/**
	 * auto generated hashcode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/**
	 * auto generated equals method
	 */
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
	
	/**
	 * returns a formatted version of the card name
	 */
	@Override
	public String toString() {
		return "Card [name=" + name + "]";
	}

	/**
	 * metohd for creating the image icon for a card
	 * @param s
	 * @return
	 */
	private static ImageIcon makeImageIcon(String s){
		s = s.toLowerCase();
		java.net.URL imageURL = Frame.class.getResource("cards/"+s+".png");

		ImageIcon icon = null;
		if (imageURL != null) {
			ImageIcon img = new ImageIcon(imageURL);
            Image image = img.getImage(); // transform it
            Image newimg = image.getScaledInstance(95, 156,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            icon = new ImageIcon(newimg);  // transform it back
		} else {
			System.out.println("URL did not work for " + s);
		}
		return icon;
	}
	
	/**
	 * returns the image icon 
	 * @return
	 */
	public ImageIcon getIcon() { 
		return icon;
	}

	
}
