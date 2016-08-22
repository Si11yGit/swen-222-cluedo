package parts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.Icon;

import board.Board;
import board.Coordinate;
import board.Door;
import board.Enterable;
import board.Hallway;
import board.Impassable;
import board.Room;
import board.Square;
import main.Main;

public class Player {
	private Character character;
	public int playerNum;
	public ArrayList<Card> hand;
	private boolean inGame;
	private Main game;
	private ArrayList<Card> AllCards;
	private Board board;
	private Enterable position;
	private Room prevroom = null;
	private Scanner scan;

	public Player(int playerNumber, Character playerCharacter, Scanner scan){
		this.character = playerCharacter;
		this.playerNum = playerNumber;
		this.hand = new ArrayList<Card>();
		this.inGame = true;
		this.scan = scan;
	}

	public void setGame(Main game){
		this.game = game;
		setBoard(game.getBoard());
	}

	public void deal(Card card){
		this.hand.add(card);
	}

	public void setAllCards(ArrayList<Card> all){
		this.AllCards= all;
	}

	

	public Character character() {
		return character;
	}

	public List<Card> getCards(){
		return hand;
	}

	public int getPlayerNumber(){
		return playerNum;
	}

	public boolean getInGame(){
		return this.inGame;
	}

	public void lose(){
		inGame = false;
		this.position.removePlayer(this);
	}

	public void setBoard(Board board){
		this.board =  board;
	}

	/**
	 * method which moves the player
	 */
	public void move(int x,int y){
		if (board.getBoardArray()[this.getX() + x][this.getY() + y] instanceof Hallway) {
			Hallway hall = (Hallway)board.getBoardArray()[this.getX() + x][this.getY() + y];
			hall.addPlayer(this);
			this.position.removePlayer(this);
			this.position = hall;
		} else {
			Room newRoom;
			for(Room room: board.getRooms().values()) {
				for(Coordinate co: room.getPositions()) {
					if (co.equals(new Coordinate(this.getX() + x,this.getY() + y))) {
						newRoom = room;
						newRoom.addPlayer(this);
						this.position.removePlayer(this);
						this.position = newRoom;
						return;
					}
				}
			}
		}
	}

	public int getX() {
		return position.getPosition().getX();
	}

	public int getY() {
		return position.getPosition().getY();
	}

	/**
	 *this is a method to check if a location is a valid place to move to
	 * @param newLoc
	 * @return
	 */
	public boolean isValidMove(int x,int y, int diceRoll){
		if (abs(x) + abs(y) > diceRoll) {
			System.out.println("position is out of range");
			return false;
		}

		//cant go outside the board
		if (this.getX() + x < 0 || this.getX() + x >= Board.BOARD_WIDTH
				|| this.getY() + y < 0 || this.getY() + y >= Board.BOARD_HEIGHT) {
			System.out.println("please choose a position on the board");
			return false;
		}

		if (board.getBoardArray()[this.getX() + x][this.getY() + y] instanceof Hallway) {
			Hallway hall = (Hallway)board.getBoardArray()[this.getX() + x][this.getY() + y];
			if (hall.getPlayer() != null) {
				System.out.println("Another player is already in that position");
				return false;
			}
		}

		//cant move into impassable square
		if(board.getBoardArray()[this.getX() + x][this.getY() + y] instanceof Impassable) {
			System.out.println("cannot move to impassable block");
			return false;
		}

		Room destination;
		if(board.getBoardArray()[this.getX() + x][this.getY() + y] == null) {
			for(Room room: board.getRooms().values()) {
				for(Coordinate co: room.getPositions()) {
					if (co.equals(new Coordinate(this.getX() + x,this.getY() + y))) {
						destination = room;
						Door closest = destination.doors().get(0);
						for(Door door: destination.doors()) {
							if (abs(door.getPosition().getX() - this.getX()) + abs(door.getPosition().getY() - this.getY())
							< abs(closest.getPosition().getX() - this.getX()) + abs(closest.getPosition().getY() - this.getY())) {
								closest = door;
							}
						}
						if (abs(closest.getPosition().getX() - this.getX()) + abs(closest.getPosition().getY() - this.getY()) > diceRoll) {
							System.out.println("cant reach room");
							return false;
						}
					}
				}
			}
		}
		return true;

	}

	/**
	 * get absolute value
	 * @param x
	 * @return
	 */
	public int abs(int x) {
		if (x < 0) {
			x = -x;
		}
		return x;
	}

	/**
	 * A method for checking if there is a secret passage way
	 *
	 * @return
	 */
	public boolean checkForPassageWays(){
		if (position instanceof Room) {
			if (((Room)position).getTunnel() != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * makes a new suggestion to check
	 * @param cards
	 * @return
	 */
	
	public Suggestion makeSuggestion(Map<String,Card> cards) {
		RoomCard room = (RoomCard)cards.get(((Room)position).getName());
		Weapon weapon = null;
		Character character = null;
		System.out.println("choose a weapon");
		System.out.println("options are:");
		for (Card card: cards.values()) {
			if (card instanceof Weapon) {
				System.out.println(card.getName());
			}
		}
		boolean check = false;
		String name = "";
		while(check == false) {
			if(scan.hasNext()) {
				name = scan.nextLine();
				if (cards.get(name) instanceof Weapon) {
					weapon = (Weapon) cards.get(name);
					check = true;
				} else {
					System.out.println("Please enter a correct option from the list, this is case sensitive");
				}
			}
		}
		System.out.println("choose a character");
		System.out.println("options are:");
		for (Card card: cards.values()) {
			if (card instanceof Character) {
				System.out.println(card.getName());
			}
		}
		check = false;
		while(check == false) {
			if(scan.hasNext()) {
				name = scan.nextLine();
				if (cards.get(name) instanceof Character) {
					character = (Character) cards.get(name);
					check = true;
				} else {
					System.out.println("Please enter a correct option from the list, this is case sensitive");
				}
			}
		}

		return new Suggestion(room,character,weapon);
	}
	
	/**
	 * checks a suggestion against the hand of the player. returns true if refuted
	 * @param seg
	 * @return
	 */
	
	public Card refuteSuggestion(Suggestion sug) {
		for (Card card: hand) {
			if (sug.compare(card) != null) {
				return card;
			}
		}
		return null;
	}
	 
	/**
	 * makes a new suggestion which is used as an accusation
	 * @param cards
	 * @return
	 */
	
	public Suggestion makeAccusation(Map<String,Card> cards) {
		RoomCard room = null;
		Weapon weapon = null;
		Character character = null;
		System.out.println("choose a room");
		System.out.println("options are:");
		for (Card card: cards.values()) {
			if (card instanceof RoomCard) {
				System.out.println(card.getName());
			}
		}
		boolean check = false;
		String name;
		while(check == false) {
			if(scan.hasNext()) {
				name = scan.nextLine();
				if (cards.get(name) instanceof RoomCard) {
					room = (RoomCard) cards.get(name);
					check = true;
				} else {
					System.out.println("Please enter a correct option from the list, this is case sensitive");
				}
			}
		}
		System.out.println("choose a weapon");
		System.out.println("options are:");
		for (Card card: cards.values()) {
			if (card instanceof Weapon) {
				System.out.println(card.getName());
			}
		}
		check = false;
		while(check == false) {
			if(scan.hasNext()) {
				name = scan.nextLine();
				if (cards.get(name) instanceof Weapon) {
					weapon = (Weapon) cards.get(name);
					check = true;
				} else {
					System.out.println("Please enter a correct option from the list, this is case sensitive");
				}
			}
		}
		System.out.println("choose a character");
		System.out.println("options are:");
		for (Card card: cards.values()) {
			if (card instanceof Character) {
				System.out.println(card.getName());
			}
		}
		check = false;
		while(check == false) {
			if(scan.hasNext()) {
				name = scan.nextLine();
				if (cards.get(name) instanceof Character) {
					character = (Character) cards.get(name);
					check = true;
				} else {
					System.out.println("Please enter a correct option from the list, this is case sensitive");
				}
			}
		}

		return new Suggestion(room,character,weapon);
	}
	/**
	 * A method for creating a suggestion
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AllCards == null) ? 0 : AllCards.hashCode());
		result = prime * result + ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((hand == null) ? 0 : hand.hashCode());
		result = prime * result + (inGame ? 1231 : 1237);
		result = prime * result + playerNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (AllCards == null) {
			if (other.AllCards != null)
				return false;
		} else if (!AllCards.equals(other.AllCards))
			return false;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (hand == null) {
			if (other.hand != null)
				return false;
		} else if (!hand.equals(other.hand))
			return false;
		if (inGame != other.inGame)
			return false;
		if (playerNum != other.playerNum)
			return false;
		return true;
	}

	public String toString() {
		return character.toString();
	}
	
	public Character getCharacter(){
		return character;
	}

	public Enterable getPosition() {
		return position;
	}

	public void setPosition(Enterable position) {
		this.position = position;
	}

	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<Card> getHand(){
		return hand;
	}


	public Card getRoom() {
		// TODO Auto-generated method stub
		return null;
	}
}
