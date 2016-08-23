package parts;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;

import board.Board;
import board.Coordinate;
import board.Door;
import board.Enterable;
import board.Hallway;
import board.Impassable;
import board.Room;
import main.Main;
import ui.Frame;

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
	private ImageIcon icon;

	public Player(int playerNumber, Character playerCharacter, Scanner scan){
		this.character = playerCharacter;
		this.playerNum = playerNumber;
		this.hand = new ArrayList<Card>();
		this.inGame = true;
		this.scan = scan;
		this.icon = makeImageIcon(playerCharacter.getName());
	}
	
	/**
	 * sets the game to the current game
	 * @param game
	 */
	public void setGame(Main game){
		this.game = game;
		setBoard(game.getBoard());
	}
	
	/**
	 * called when a card is dealt to the player
	 * @param card
	 */
	public void deal(Card card){
		this.hand.add(card);
	}
	
	/**
	 * sets the field which contains a list of all the cards
	 * @param all
	 */
	public void setAllCards(ArrayList<Card> all){
		this.AllCards= all;
	}

	/**
	 * returns the hand of the player
	 * @return
	 */
	public List<Card> getCards(){
		return hand;
	}

	/**
	 * returns the number of the player
	 * @return
	 */
	public int getPlayerNumber(){
		return playerNum;
	}
	
	/**
	 * returns whether or not the player is still in the game
	 * @return
	 */
	public boolean getInGame(){
		return this.inGame;
	}
	
	/**
	 * removes the player when they lose
	 */
	public void lose(){
		inGame = false;
		this.position.removePlayer(this);
	}
	
	/**
	 * sets the board state
	 * @param board
	 */
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
	
	/**
	 * returns the x position of the player
	 * @return
	 */
	public int getX() {
		return position.getPosition().getX();
	}

	/**
	 * returns the y position of the player
	 * @return
	 */
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
	 * auto generated hash code
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
	
	/**
	 * auto generated equals method
	 */
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
	
	/**
	 * to string method
	 */
	public String toString() {
		return character.toString();
	}
	
	/**
	 * returns the players character
	 * @return
	 */
	public Character getCharacter(){
		return character;
	}
	
	/**
	 * returns the position of the player on the board
	 * @return
	 */
	public Enterable getPosition() {
		return position;
	}

	/**
	 * sets the position of the player on the board
	 */
	public void setPosition(Enterable position) {
		this.position = position;
	}
	
	/**
	 * adds a card to the players hand 
	 * @param c
	 */
	public void addToHand(Card c){
		this.hand.add(c);
	}
	
	public Card getRoom() {
		return null;
	}
	
	/**
	 * makes an image icon for the player
	 * @param s
	 * @return
	 */
	private static ImageIcon makeImageIcon(String s){
		s = s.toLowerCase();
		java.net.URL imageURL = Frame.class.getResource("pieces/"+s+".png");

		ImageIcon icon = null;
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
			Image image = icon.getImage(); // transform it
            Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            icon = new ImageIcon(newimg);  // transform it back
		} else {
			System.out.println("URL did not work for " + s);
		}
		return icon;
	}
	
	/**
	 * returns the image icon of a player
	 * @return
	 */
	public ImageIcon getIcon() { 
		return icon;
	}
	
	/**
	 * returns the character name of the player
	 * @return
	 */
	public String getCharacterName(){
		return this.character.getName();
	}
	
	/**
	 * shortens call for name
	 * @return
	 */
	public String getName() {
		return character.getName();
	}
}
