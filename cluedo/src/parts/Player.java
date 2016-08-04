package parts;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private Character character;
	public int playerNum;
	public ArrayList<Card> hand;
	private boolean inGame;
	
	public Player(int playerNumber, Character playerCharacter){
		this.character = playerCharacter;
		this.playerNum = playerNumber;
		this.hand = new ArrayList<Card>();
		this.inGame = true;
	}


	
	public void deal(Card card){
		this.hand.add(card);
	}
	
	private int	diceRoll(){
		int diceOne = (int) Math.round(Math.random()*6);
		int diceTwo = (int) Math.round(Math.random()*6);
		return diceOne + diceTwo;
	}
	
	//move method which is tacked with moving the player, 
	//the player is prompted to move in a direction W,A,S,D
	//checks that the player is able to move in that direction
	//if they can't they are prompted to move in a different direction
	public void move(){
		
	}
	
	//check if a location is a valid place to move to 
	public Boolean isValidMove(Location newLoc){
		return false;
	}
	
	//make a "take turn class"
	public void takeTurn(boolean leavingRoom){
		boolean inRoom;//if a player is in a room or not
		
		//if player is not leaving room ...
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

	//public void setCharacter(Character character) {
	//	this.character = character;
	//}
}
