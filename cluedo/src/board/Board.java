package board;

import java.util.ArrayList;

import parts.Player;

public class Board {
	Square board[][] = new Square[24][25];
	ArrayList<Player> players;

	public Board(ArrayList<Player> players){
		this.players = players;
	}

	public void draw() {
		System.out.print("________ ___");
		System.out.print("|      |_|_|__________");
		System.out.print("|        |__________");
		System.out.print("|        |___________");
		System.out.print("|________|___");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
		System.out.print("____________");
	}
}
