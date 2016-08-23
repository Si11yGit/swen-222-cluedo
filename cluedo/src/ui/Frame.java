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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import board.Coordinate;
import board.Square;
import main.Main;
import parts.Player;


public class Frame extends JFrame implements KeyListener, MouseListener, WindowListener{
	
	//the frame is the window which holds all the other swing components

	private static final long serialVersionUID = -9109857494274448808L;
	//menu components
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem newGame;
	private JMenuItem exitGame;
	
	//panels
	private JPanel outerPanel;
	private OptionPanel options;
	private HandPanel hand;
	private BoardPanel board;
	//the game
	private Main game;
	
	public Frame(){
		super("Game of Cluedo!");
		//Initialize the game
		this.game = new Main(this);
		
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
					}
				});
		file.add(exitGame);
		
		//create the outer panel
		outerPanel = new JPanel();
		outerPanel.setBorder(new EmptyBorder(5,5,5,5));
		outerPanel .setLayout(new BorderLayout(0,0));
		setContentPane(outerPanel);
		
		//create board panel
		board = new BoardPanel(game);
		outerPanel.add(board, BorderLayout.EAST);
		
		//create options panel
		options = new OptionPanel(this);
		outerPanel.add(options, BorderLayout.WEST);
		
		//create hand panel 
		hand = new HandPanel(game);
		outerPanel.add(hand, BorderLayout.SOUTH);
		
		pack();
		this.setLocationRelativeTo(null);
		addKeyListener(this);
		addMouseListener(this);
		setVisible(true);
		setFocusable(true);
		requestFocus();
	}
	
	
	
	

	protected void newGame() {
		game.restart();
		this.dispose();
	}


 /**
  * exits the game
  */
	protected void exitGame() {
		int option = JOptionPane.showOptionDialog(this, "Are you sure you want to exit?", 
						"Confirm Exit", JOptionPane.YES_NO_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null,null, null);
		if(option == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}



	public void buttonPressed(ActionEvent e) {
		String s = e.getActionCommand();
		System.out.println(s);
		
		switch(s){
		case "Roll Dice":
			game.diceRoll();//need to move the dice roll method into the Main class
			break;
		case "Suggest":
			if(game.getCurrentPlayer().getRoom() == null){
				break;
				}
			String[] suggestion = createGuessGUI(false);
			if(suggestion == null){
				break;
			}
			game.makeSuggestion(suggestion[0], null, suggestion[1]);
			options.suggestionEnabled(false);
			break;
		case "Accuse":
			String[] accuse = createGuessGUI(true);
			if(accuse == null){
				break;
			}
			game.makeAccusation(accuse[0], accuse[1], accuse[2]);
			break;
		case "End Turn":
			endTurn();
			break;
		case "Use Stairs":
			break;
		}
		requestFocus();
	}
	
	

	/**
	 * ends the players turn 
	 */
	private void endTurn() {
		this.game.nextPlayerTurn();//change the current player to the next in line
		options.rollEnabled(true);
		hand.updateLabels();
		this.getOptions().getTextArea().append(game.getCurrentPlayer().getCharacterName() + ", it's your turn!!" + "\n");
	}
	/**
	 * moves the player on the board
	 */
	public void movePlayer(Player player, Coordinate oldPos, Coordinate newPos){
		board.movePlayer(player, oldPos, newPos);
	}
	/**
	 * game over
	 * 
	 *  @param the parameter is the player that Won
	 */
	public void gameOver(Player player){
		int option = JOptionPane.showOptionDialog(this, player.getCharacter().name() + " has 					won!!!!", "Winner!!", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, 
							null, new String[]{"New Game", "Exit"}, "New Game");
		if(option == JOptionPane.OK_OPTION){
			newGame();
		}else{
			System.exit(0);
		}
	}
	
	/**
	 * creates the GUI for a guess or an accuse and returns the selected options for them.
	 * 
	 * @param isAccuse - is this an accuse for a guess
	 * @return string array holding the selected options (character-> room -> weapon)
	 */
	private String[] createGuessGUI(boolean isAccuse) {
		String[] answers;
		//create correct length array
		if(isAccuse){answers = new String[3];} //if we are accusing someone or just suggestion
		else {answers = new String[2];} // a guess does not need to have a room
		
		//create character select GUI
		CharacterSelect cs = new CharacterSelect(game);
		int i = JOptionPane.showOptionDialog(this, cs, "Character Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(i == JOptionPane.CANCEL_OPTION || i == JOptionPane.CLOSED_OPTION) {
			return null;
		}
		answers[0] = cs.getSelectedChar();
		//if is an accuse get a room
		if(isAccuse){
			// create Room select GUI
			RoomSelect rs = new RoomSelect(game);
			int k = JOptionPane.showOptionDialog(this, rs, "Room Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(k == JOptionPane.CANCEL_OPTION || k == JOptionPane.CLOSED_OPTION) {
				return null;
			}
			answers[1] = rs.getSelectedRoom();
		}
		// create weapon select GUI
		WeaponSelect ws = new WeaponSelect(game);
		int j = JOptionPane.showOptionDialog(this, ws, "Weapon Select", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(j == JOptionPane.CANCEL_OPTION || j == JOptionPane.CLOSED_OPTION) {
			return null;
		}
		answers[answers.length-1] = ws.getSelectedWeapon();
		return answers;
	}
	
	public BoardPanel getBoard(){
		return this.board;
	}
	
	public OptionPanel getOptions(){
		return this.options;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		requestFocus();
		int x = e.getX()-board.getX()-8;
		int y = e.getY()-board.getY()-53;
		Square square = board.checkMouseOnDoor(x, y);
		if(square != null){
			game.doorClicked(square);
		}
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		exitGame();
	}	
	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	public void keyPressed(KeyEvent arg0) {	
	}
	@Override
	public void keyReleased(KeyEvent arg0) {		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	//test starter
	public static void main(String[] args) {
		new Frame();
	}
}
