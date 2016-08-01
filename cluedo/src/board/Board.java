package board;

import java.util.ArrayList;

import parts.Player;

public class Board {
	Square board[][] = new Square[24][25];
	ArrayList<Player> players;
	
	public Board(ArrayList<Player> players){
		this.players = players;
	}
}
