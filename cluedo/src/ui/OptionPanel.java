package ui;

//import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class OptionPanel extends JPanel {
/**	
	//The different buttons that the panel has
	private JButton accuse;
	private JButton suggest;
	private JButton rollDice;
	private JButton endTurn;
	
	
	//Text
	private JTextArea textArea;
	
	//other buttons
	private JButton stairs;

	/**
	Create the Panel, this is a class that creates
	 a panel with the options a player has when making
	 a move
	*/
	/**
	public OptionPanel(Frame frame){
		setBorder(new TitledBorder(null,"Options", TitledBorder.LEADING, TitledBorder.TOP));
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
						accuse.setEnabled(false);
						frame.buttonPressed(e);//gotta make a fram class
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
		
		suggest.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e ){
						rollDice.setEnabled(false);
						frame.buttonPressed(e);//gotta make a frame class
					};
		});
		
		//Stairs button
		
		// End turn button
		endTurn =  new JButton("End Turn");
		GridBagConstraints gbcEndTurn = new GridBagConstraints();
	}
	*/
}
