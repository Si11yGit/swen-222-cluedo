package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import board.Board;
import board.Coordinate;
import main.Main;
import parts.Player;

public class GUI extends JFrame implements KeyListener, MouseListener, WindowListener {
	private JPanel container; 			//contains other components
	private JMenuBar menu;				//menu to hold new game and exit
	private JMenu file; // File on menu
	private JMenuItem menuNewGame;		//button to restart game
	private JMenuItem menuExit;			//exit button
	private BoardPanel guiBoard;		//JPanel implementation housing the board
	private PlayerPanel playerOptions;	//option for player to use during turn
	private HandPanel handDisplay;		//panel to display hand of current player
	private Main game;

	public GUI() {
		//create a game associated with this gui
		game = new Main(this);

		//set close operation
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		//create menu bar
		menu = new JMenuBar();
		setJMenuBar(menu);

		// create file in menu bar
		file = new JMenu("File");
		menuBar.add(file);
		//add new game to file
		menuNewGame = new JMenuItem("New Game");
		// action listener to create a new game
		menuNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					newGame();
			    	return;
			}
		});
		file.add(menuNewGame);
		// add exit to file
		menuExit = new JMenuItem("Exit");
		//action listener to exit program
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					exitGame();
				}
		});
		file.add(menuExit);

		// create outer most panel
		container = new JPanel();
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		container.setLayout(new BorderLayout(0, 0));
		//add panel to frame
		setContentPane(container);
		//create board panel (contains the board grid)
		guiBoard = new BoardPanel(game);
		container.add(guiBoard, BorderLayout.EAST);
		//create options panel (contains the option buttons and text area)
		playerOptions = new PlayerPanel(this);
		container.add(playerOptions, BorderLayout.WEST);
		//create hand panel (contains the current players hand)
		handDisplay = new HandPanel(game);
		container.add(handDisplay, BorderLayout.SOUTH);

		pack();
		this.setLocationRelativeTo(null);
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
	}

	public void movePlayer(Player player, Coordinate oldPos, Coordinate newPos) {
		guiBoard.movePlayer(player, oldPos, newPos);
	}


	/**
	 * performs actions depending on button pressed
	 *
	 * @param e
	 */
	public void buttonPressed(ActionEvent e){
		String s = e.getActionCommand();
		System.out.println(s);
		switch(s){
		case "Roll Dice":
			game.diceRoll();// this will need to be currentplayer.diceroll
			break;
		case "Guess":
			if(game.getCurrentPlayer().getRoom() == null){break;}//will need to add get current player method to "Main"
			String[] guess = createGuessAccuseGUI(false);
			if(guess == null){break;}
			game.guessAccuse(guess[0], null, guess[1], false);
			options.guessEnabled(false);
			break;
		case "Accuse":
			String[] accuse = createGuessAccuseGUI(true);
			if(accuse == null){break;}
			game.guessAccuse(accuse[0], accuse[1], accuse[2], true);
			break;
		case "End Turn":
			endTurn();
			break;
		case "Use Stairs":
			Stairs stairMove = new Stairs(game, game.getCurrentPlayer());
			if(stairMove.isValid()){
				stairMove.run();
			}
		}
		requestFocus();
	}

	public static void main(String[] args) {

    }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
