package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;

public class Frame extends JFrame implements KeyListener, MouseListener, WindowListener{
	
	//the frame is the window which holds all the other swing components
	
	//private static final long serialVersionUID;
	
	//menu components
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem newGame;
	private JMenuItem exitGame;
	
	//panels
	private JPanel outerPanel;
	private OptionPanel options;
	private HandPanel handPanel;
	private BoardPanel board;
	//the game
	private Main game;
	
	public Frame (){
		//Initialize the game
		//this.game = new Main();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		//create the menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		file = new JMenu("File");
		menuBar.add(file);
		
		//add new game to file
		newGame = new JMenuItem("New Game");

		newGame.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						newGame();
						return;
					}
				});
		file.add(newGame);
		
		//add exit game to menu
		exitGame = new JMenuItem("Exit Game");
		exitGame.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						exitGame();
						return;
					}
				});
		file.add(exitGame);
		
		//create the outer panel
		outerPanel = new JPanel();
		outerPanel.setBorder(new EmptyBorder(5,5,5,5));
		outerPanel .setLayout(new BorderLayout(0,0));
		setContentPane(outerPanel);
		
		//create hand panel
		board = new BoardPanel(game);
		outerPanel.add(board, BorderLayout.EAST);
		
		//create options panel
		options = new OptionPanel(this);
		outerMostPane
		//create board panel 
		
		
		pack();
		this.setLocationRelativeTo(null);
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
	}
	
	
	
	

	protected void newGame() {
		// TODO Auto-generated method stub
		
	}



	protected void exitGame() {
		// TODO Auto-generated method stub
		
	}



	public void buttonPressed(ActionEvent e) {
		
		
	}



	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
