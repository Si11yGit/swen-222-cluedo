package ui;

//import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class OptionPanel extends JPanel {

	//The different buttons that the panel has
	private JButton accuse;
	private JButton suggest;
	private JButton rollDice;
	private JButton endTurn;
	
	
	//Text
	private JTextArea textArea;
	
	//other buttons
	private JButton stairs;
	private JScrollPane scroll;
	

	/**
	Create the Panel, this is a class that creates
	 a panel with the options a player has when making
	 a move
	*/

	public OptionPanel(Frame frame){
		setBorder(new TitledBorder(null,"Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[]{0, 0, 0};
		layout.rowHeights = new int[]{0, 0, 0, 0, 0};
		layout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(layout);
		
		//create all the buttons, the text area
		//place them in the panel
		
		// accuse button
		accuse =  new JButton("Accuse");
		GridBagConstraints gbcAccuse = new GridBagConstraints();
		gbcAccuse.fill = GridBagConstraints.BOTH;
		gbcAccuse.anchor = GridBagConstraints.WEST;
		gbcAccuse.insets = new Insets(0,0,5,5);
		gbcAccuse.gridx = 0;
		gbcAccuse.gridy = 0;
		add(accuse, gbcAccuse);
		
		accuse.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e ){
						frame.buttonPressed(e);
					};
		});
		
		
		
		// Suggest button
		suggest =  new JButton("Suggest");
		GridBagConstraints gbcSuggest = new GridBagConstraints();		
		gbcSuggest.fill = GridBagConstraints.BOTH;
		gbcSuggest.anchor = GridBagConstraints.WEST;
		gbcSuggest.insets = new Insets(0,0,5,0);
		gbcSuggest.gridx = 1;
		gbcSuggest.gridy = 0;
		add(suggest, gbcSuggest);
		
		suggest.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e ){
						frame.buttonPressed(e);//gotta make a fram class
					};
		});
		
		
		
		// Roll Dice button
		rollDice =  new JButton("Roll Dice");
		GridBagConstraints gbcRollDice = new GridBagConstraints();
		gbcRollDice.fill = GridBagConstraints.BOTH;
		gbcRollDice.anchor = GridBagConstraints.WEST;
		gbcRollDice.insets = new Insets(0,0,5,0);
		gbcRollDice.gridx = 1;
		gbcRollDice.gridy = 1;
		add(rollDice, gbcRollDice);
		
		rollDice.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e ){
						rollDice.setEnabled(false);
						frame.buttonPressed(e);
					};
		});
		
		//Stairs button
		stairs =  new JButton("Use Stairs");
		stairs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ){
				frame.buttonPressed(e);
			};
		});
		GridBagConstraints gbcStairs = new GridBagConstraints();
		gbcStairs.fill = GridBagConstraints.BOTH;
		gbcStairs.insets = new Insets(0,0,5,5);
		gbcStairs.gridx = 0;
		gbcStairs.gridy = 1;
		add(stairs, gbcStairs);
		
		// End turn button
		endTurn =  new JButton("End Turn");
		GridBagConstraints gbcEndTurn = new GridBagConstraints();
		gbcEndTurn.fill = GridBagConstraints.BOTH;
		gbcEndTurn.insets = new Insets(0,0,5,5);
		gbcEndTurn.gridx = 0;
		gbcEndTurn.gridy = 2;
		add(endTurn, gbcEndTurn);
		
		endTurn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e ){
						endTurn.setEnabled(true);
						rollDice.setEnabled(true);
						frame.buttonPressed(e);
					};
		});
		
		//Scrolling in the options pan
		scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbcScroll = new GridBagConstraints();
		gbcScroll.fill = GridBagConstraints.BOTH;
		gbcScroll.gridwidth = 2;
		gbcScroll.gridx = 0;
		gbcScroll.gridy = 3;
		add(scroll, gbcScroll);
		
		//txt area
		textArea = new JTextArea(5, 20);
		scroll.setViewportView(textArea);
		textArea.setEditable(false);
				
		
		
	}
	public JTextArea getTextArea(){
		return textArea;
	}
	
	public void suggestionEnabled(boolean enable){
		suggest.setEnabled(enable);
		}
	
	public void rollEnabled(boolean enable){
		rollDice.setEnabled(enable);
		}
}
