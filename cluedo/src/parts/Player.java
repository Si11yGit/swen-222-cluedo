package parts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import board.Board;
import board.Coordinate;
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
	private Square position;
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

	/**
	 * Simulates a dice roll in the game
	 * @return
	 */
	public int	diceRoll(){

		int diceOne = (int) Math.round(Math.random()*6);
		int diceTwo = (int) Math.round(Math.random()*6);
		return diceOne + diceTwo;
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
	}

	public void setBoard(Board board){
		this.board =  board;
	}


	//move method which is tacked with moving the player,
	//the player is prompted to move in a direction W,A,S,D
	//checks that the player is able to move in that direction
	//if they can't they are prompted to move in a different direction
	/**
	 *
	 */
	public void move(){
		prevroom = null;
		position.getPosition();
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


	public Boolean isValidMove(){

		//cant go outside the board

		//location on baord

		//if its a door

			//if its not  a previously visited room, or a
			//aka prevRoom == null
		 	return true;

		 	//if we were in there last turn, against the rules
		 	//return false;

		 //return true; outside the two nested statemnents
		//else if
		 	//check if there is another player oon the square
		 	//return false
		//else if
		 	//check if there is a wall there

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

	//a method that returns the new location of the player (after they move)

	//ask for accusations 	//end every turn with this
	//if they are right then the game is over
	//if they are wrong then they lose the game
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

	public Square getPosition() {
		return position;
	}

	public void setPosition(Square position) {
		this.position = position;
	}


	//public void setCharacter(Character character) {
	//	this.character = character;
	//}
}
