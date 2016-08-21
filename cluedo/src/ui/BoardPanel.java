package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Coordinate;
import main.Main;
import parts.Player;

public class BoardPanel extends JPanel {
	private Main game;

	public static final int TILE_SIZE = 30;
	//private Tile[][] board;
	private JLabel[][] labels;
	
	public BoardPanel(Main game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	public void movePlayer(Player player, Coordinate oldPos, Coordinate newPos) {
		// TODO Auto-generated method stub

	}

}
