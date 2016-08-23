package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.text.Position;

import board.Board;
import board.Coordinate;
import board.Door;
import board.Hallway;
import board.Room;
import board.Square;
import main.Main;
import parts.Player;

/**
 * panel used to display the board on
 *
 */
public class BoardPanel extends JPanel{
	private Main game;
	
	public static final int SQUARE_SIZE = 15;
	public static final int BOARDER_SIZE = 3;
	private Square[][] board;
	private JLabel[][] labels;

	
	/**
	 * Create the panel.
	 */
	public BoardPanel(Main game) {
		this.game = game;
		setLayout(new GridBagLayout());
		board = game.getBoard().getBoardArray();
		labels = new JLabel[Board.BOARD_WIDTH][Board.BOARD_HEIGHT];
		GridBagConstraints c;
		System.out.println("initialized labels");
		for(int i = 0; i < Board.BOARD_HEIGHT; i++) {
			for(int j = 0; j < Board.BOARD_WIDTH; j++) {
				c = new GridBagConstraints();
				c.gridx = j;
				c.gridy = i;
				JLabel tile = new JLabel(); {
					if (i % 2 == 1 && j % 2 == 1) {
						tile.setPreferredSize(new Dimension(SQUARE_SIZE,SQUARE_SIZE));
						
						
					} else if (i % 2 == 1) {
						tile.setPreferredSize(new Dimension(BOARDER_SIZE,SQUARE_SIZE));
					} else if (j % 2 == 1) {
						tile.setPreferredSize(new Dimension(SQUARE_SIZE,BOARDER_SIZE));
					} else {
						tile.setPreferredSize(new Dimension(BOARDER_SIZE,BOARDER_SIZE));
					}
					if (board[j][i] != null) {
						tile.setBackground(board[j][i].getColor());
					} else if (game.getBoard().isInRoom(new Coordinate(j,i))) {
						tile.setBackground(game.getBoard().getRoom(new Coordinate(j,i)).getColor());
					} else {
						tile.setBackground(Color.BLACK);
					}
					
					tile.setOpaque(true);
					if(board[j][i] != null && board[j][i].getPlayer() != null) {
						tile.setIcon(board[j][i].getPlayer().getIcon());
					}
					add(tile,c);
					labels[j][i] = tile;
				}
			}
		}
		setBorder(new LineBorder(Color.black));
		setMouseover();
	}

	
	/**
	 * Sets text for mousing over tiles on board. 
	 * Displays the names of any players on the tile then the name of the square.
	 */
	private void setMouseover() {
		for (int i =0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				if (board[i][j] != null && board[i][j].getPlayer() != null) {
					labels[i][j].setToolTipText(game.getBoard().getBoardArray()[i][j].getPlayer().getName());
				} else if (game.getBoard().isInRoom(new Coordinate(j,i))) {
					labels[i][j].setToolTipText(game.getBoard().getRoom(new Coordinate(j,i)).getName());
				} else {
					labels[i][j].setToolTipText(null);
				}
			}
		}
	}

	/**
	 * moves a player from the old position to the new position
	 * 
	 * @param player - player to move
	 * @param oldPos - old position
	 * @param newPos - new position
	 */
	public void movePlayer(Player player, Coordinate oldPos, Coordinate newPos) {
		labels[oldPos.getX()][oldPos.getY()].setIcon(null);
		labels[newPos.getX()][newPos.getY()].setIcon(player.getIcon());
		setMouseover();
	}
	
	/**
	 * check if a x and y position is on a square
	 * 
	 * @param x - x position
	 * @param y - y position
	 * @return
	 */
	public Coordinate mouseSelection(int x, int y){
		System.out.println(x + " "  + y);
		System.out.println(this.getBounds().getMinX() + " "  + this.getBounds().getMinY());
		x = (int) (x - this.getBounds().getMinX());
		y = (int) (y - this.getBounds().getMinY());
		for(int i = 0; i < Board.BOARD_HEIGHT; i++) {
			for(int j = 0; j < Board.BOARD_WIDTH; j++) {
				if(labels[j][i].getBounds().contains(x,y)){
					return new Coordinate(j,i);
				}
			}
		}
		return null;
	}
	
	/**
	 * remove a player icon from a tile
	 * 
	 * @param player - player that is being removed
	 */
	public void removePlayer(Player player){
		labels[player.getPosition().getPosition().getX()][player.getPosition().getPosition().getY()].setIcon(null);
		setMouseover();
	}
}
