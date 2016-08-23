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

public class RoomSelect extends JPanel implements ActionListener{
	
	private List<String> rooms;
	private JLabel roomPic;
	private String selectedRoom;
	
	
	public RoomSelect(Main game){
		super(new BorderLayout());
		rooms =  new ArrayList<String>();
		
		//get all the rooms that the game has
		for(Card r : game.getRoom()){
			if(r.getName().equals("")){
				continue;
			}
			rooms.add(r.getName());
		}
		
		//create buttons for each of the rooms 
		JRadioButton[] buttons = new JRadioButton[rooms.size()];
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JRadioButton(rooms.get(i));
		}
		buttons[0].setSelected(true);
		//add action listener to each button
		for(JRadioButton b : buttons) {
			b.addActionListener(this);
		}
		//add buttons to a group
		ButtonGroup bg = new ButtonGroup();
		for(int i = 0; i < buttons.length; i++) {
			bg.add(buttons[i]);
		}
		
		//set images for the buttons
		roomPic = new JLabel(createImageIcon(rooms.get(0)+".png"));
		//add buttons to panel
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		for(JRadioButton b : buttons) {
			buttonPanel.add(b);
		}
		add(buttonPanel, BorderLayout.LINE_START);
		add(roomPic, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
				
	}

	public String getSelectedRoom() {
		return selectedRoom;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		selectedRoom = e.getActionCommand();
				roomPic.setIcon(createImageIcon(e.getActionCommand()+".png"));
		
	}
	
	private ImageIcon createImageIcon(String s){
		s = s.toLowerCase();
		java.net.URL imgURL = Main.class.getResource("cluedo/src/cluedo/cards/"+s);
		if(imgURL != null){
			ImageIcon img = new ImageIcon(imgURL);
            Image image = img.getImage(); // transform it
            Image newimg = image.getScaledInstance(105, 176,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            return new ImageIcon(newimg);  // transform it back
		}else{
			System.err.println("File: " + s +" doesn't exist!!!");
			return null;
		}
	}
}
