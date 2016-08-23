package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.Main;
import parts.Card;
import parts.Weapon;

/**
 * panel that is used in a guess or accuse to display the weapons
 *
 */
public class WeaponSelect extends JPanel implements ActionListener{

	private static final long serialVersionUID = -5878345777929474786L;

	//list of available weapons in the game
	private List<String> weapons;
	
	//picture for the currently selected weapon
	private JLabel weaponPicture;
	
	//currently selected weapon
	private String selectedWeapon;
	
	public WeaponSelect(Main game) {
		super(new BorderLayout());
		weapons = new ArrayList<String>();
		
		//get all weapons
		for(Card w : game.getWeapons()) {
			weapons.add(w.getName());
		}
		
		//create buttons for each weapon
		JRadioButton[] buttons = new JRadioButton[weapons.size()];
		for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JRadioButton(weapons.get(i));
		}
		
		//add buttons to a group
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < buttons.length; i++) {
			bg.add(buttons[i]);
		}
		
		//add action listener to each button
		for(JRadioButton b : buttons) {
			b.addActionListener(this);
		}
		
		//set first button to selected and update image
		buttons[0].setSelected(true);
		weaponPicture = new JLabel(createImageIcon(weapons.get(0)+".png"));
		
		//add buttons to button panel
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		for(JRadioButton b : buttons) {
			buttonPanel.add(b);
		}
		
		//add all components to 'this'
		add(buttonPanel, BorderLayout.LINE_START);
		add(weaponPicture, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//update selected weapon and image
		selectedWeapon = e.getActionCommand();
		weaponPicture.setIcon(createImageIcon(e.getActionCommand()+".png"));		
	}
	/**
	 * create imageIcon for the weapons
	 * @param path
	 * @return
	 */
	private ImageIcon createImageIcon(String s) {
		s = s.toLowerCase();
        java.net.URL imgURL = Main.class.getResource("cluedo/src/cluedo/cards/"+s);
        if (imgURL != null) {
        	ImageIcon img = new ImageIcon(imgURL);
            Image image = img.getImage(); // transform it
            Image newimg = image.getScaledInstance(105, 176,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            return new ImageIcon(newimg);  // transform it back
        } else {
            System.err.println("File: " + s +" doesn't exist!!!");
            return null;
        }
    }
	/**
	 * @return the selectedWeapon
	 */
	public String getSelectedWeapon() {
		return selectedWeapon;
	}
}