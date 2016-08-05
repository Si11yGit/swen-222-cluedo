package parts;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import main.Main;

public class Player {
	private Character character;
	public int playerNum;
	public ArrayList<Card> hand;
	private boolean inGame;
	private Main game;
	private ArrayList<Card> AllCards;
	private Board board;
	
	public Player(int playerNumber, Character playerCharacter){
		this.character = playerCharacter;
		this.playerNum = playerNumber;
		this.hand = new ArrayList<Card>();
		this.inGame = true;
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
	
	private int	diceRoll(){

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
	
	public void setBoard(Board board){
		this.board =  board;
	}
	

	//move method which is tacked with moving the player, 
	//the player is prompted to move in a direction W,A,S,D
	//checks that the player is able to move in that direction
	//if they can't they are prompted to move in a different direction
	public void move(){
		//WASD?
		//loop, until valid input is entered
		
		//var old location
		//temporary, new location
		
		//if the move to the new loc is valid
		//var new location
		//setLocation to the new location
		
		//check if the new location is a door
			// it is youve found  a door leading to a room
		//check which room it is, set the location to that room
		
		//else it is an invalid move so recursively try again
		
	
	}
	
	//check if a location is a valid place to move to 
	
	public Boolean isValidMove(Location newLoc){
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
	
	//make a "take turn class"
	public void takeTurn(boolean leavingRoom){
		boolean inRoom;//if a player is in a room or not
		
		//if player is not leaving room ...
	}
	
	//passageway checking method?
	
	//a method that returns the new location of the player (after they move)
	
	//ask for accusations 	//end every turn with this 
	//if they are right then the game is over
	//if they are wrong then they lose the game
	
	
	//ask for suggestions 	//end every turn where player if in room with this 
	//other players disprove 
	
	//check suggestion
	//called to prove or disprove a suggestion
	//check the cards starting to the left (next turn) 
	//of the current player 
	
	//public void setCharacter(Character character) {
	//	this.character = character;
	//}
}
