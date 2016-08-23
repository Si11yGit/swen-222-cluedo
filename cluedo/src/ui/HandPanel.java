package ui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;
import parts.Card;

/**
 *panel that displays the hand of the current player
 *
 */
public class HandPanel extends JPanel {
	private static final long serialVersionUID = -5334182416168789455L;
	private Main game;
	private List<JLabel> cardLabels; 

	/**
	 * Create the panel.
	 */
	public HandPanel(Main game) {
		this.game = game;
		cardLabels = new ArrayList<JLabel>();
		setLayout(new GridLayout(1, 0, 0, 0));
		setToolTipText("Current players hand");
<<<<<<< HEAD
		List<Card> hand = game.getCurrentPlayer().getCards();
		for(Card c : hand) {
			ImageIcon card = c.getIcon();
			if(card == null) {
				System.out.println("icon still missing");
			}
			JLabel label = new JLabel(card);
			cardLabels.add(label);
			add(label);
		}
=======
		//List<Card> hand = game.getCurrentPlayer().getCards();
		//for(Card c : hand) {
			//ImageIcon card = c.getIcon();
			
			//JLabel label = new JLabel(card);
			//cardLabels.add(label);
			//add(label);
		//}
>>>>>>> branch 'master' of git://github.com/Si11yGit/swen-222-cluedo.git
	}
	
	/**
	 * update the icon on the labels when a player has moved
	 */
	public void updateLabels() {

		List<Card> newCards = game.getCurrentPlayer().getCards();
		for(int i = 0; i < cardLabels.size(); i++) {
			if(i >= newCards.size()){
				cardLabels.get(i).setIcon(null);
			}
			else {
				cardLabels.get(i).setIcon(newCards.get(i).getIcon());
			}
		}
	}
	
}