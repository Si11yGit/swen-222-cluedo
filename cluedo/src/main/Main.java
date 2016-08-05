package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.Board;
import board.Room;
import parts.Card;
import parts.Player;
import parts.RoomCard;
import parts.Solution;
import parts.Weapon;
import parts.Character;

//	 ,o888888o.     8 8888         8 8888      88 8 8888888888   8 888888888o.          ,o888888o.
//	8888     `88.   8 8888         8 8888      88 8 8888         8 8888    `^888.    . 8888     `88.
//,8 8888       `8. 8 8888         8 8888      88 8 8888         8 8888        `88. ,8 8888       `8b
//88 8888           8 8888         8 8888      88 8 8888         8 8888         `88 88 8888        `8b
//88 8888           8 8888         8 8888      88 8 888888888888 8 8888          88 88 8888         88
//88 8888           8 8888         8 8888      88 8 8888         8 8888          88 88 8888         88
//88 8888           8 8888         8 8888      88 8 8888         8 8888         ,88 88 8888        ,8P
//`8 8888       .8' 8 8888         ` 8888     ,8P 8 8888         8 8888        ,88' `8 8888       ,8P
//	8888     ,88'   8 8888           8888   ,d8P  8 8888         8 8888    ,o88P'    ` 8888     ,88'
//	 `8888888P'     8 888888888888    `Y88888P'   8 888888888888 8 888888888P'          `8888888P'



/**
 *
 *
 * @author clarkebenj1
 *
 */
public class Main {
	//fields:
	private ArrayList<Card> allCards;
	private ArrayList<Card> listOfCards;
	private ArrayList<Player> allPlayers;
	private int numPlayers;
	private Solution solution; //solution object
	private Board board;//reference to a board
	private boolean gameOver;//whether the game is over or not 

	public Main(int numPlayers){
		this.numPlayers=numPlayers;
		this.allCards = initialiseCards();
		this.listOfCards = allCards;
		Collections.shuffle(this.allCards);
		this.allPlayers = initialisePlayer();
		this.board = new Board(allPlayers);
		initialiseWeapons();
		this.solution =  initialiseSolution();
		dealCards();
	}

	/**
	 * initialise all the cards 
	 * @return
	 */
	public ArrayList<Card> initialiseCards(){
		ArrayList<Card> cards = new ArrayList<Card>();

		//add all characters
		cards.add(new Character("Miss Scarlett"));
		cards.add(new Character("Colonel Mustard"));
		cards.add(new Character("Professor Plum"));
		cards.add(new Character("Reverend Green"));
		cards.add(new Character("Mrs. White"));
		cards.add(new Character("Mrs. Peacock"));

		//add all weapons
		cards.add(new Weapon("Candlestick"));
		cards.add(new Weapon("Revolver"));
		cards.add(new Weapon("Rope"));
		cards.add(new Weapon("Spanner"));
		cards.add(new Weapon("Dagger"));
		cards.add(new Weapon("Lead Pipe"));

		//add all rooms 
		cards.add(new RoomCard("Ballroom"));
		cards.add(new RoomCard("Kitchen"));
		cards.add(new RoomCard("Conservatory"));
		cards.add(new RoomCard("Billiard Room"));
		cards.add(new RoomCard("Library"));
		cards.add(new RoomCard("Study"));
		cards.add(new RoomCard("Hall"));
		cards.add(new RoomCard("Lounge"));
		cards.add(new RoomCard("Dining Room"));

		return cards;
	}

	/**
	 *This method creates the correct solution for the game to compare accusations or suggestions against.  
	 * @return
	 */
	public Solution initialiseSolution(){
		Character character  = null;
		Weapon weapon = null;
		RoomCard room = null;

		while(character == null || room ==  null || room == null){
			int random = (int) Math.round(Math.random())*listOfCards.size();
			if(random == listOfCards.size()){random--;}//avoid ArrayIndexOutOfBounds errors
			Card card = listOfCards.get(random);

			if(card instanceof Character && character == null){
				character = (Character) card;
				listOfCards.remove(card);
			}else if(card instanceof Weapon && weapon == null){
				weapon = (Weapon) card;
				listOfCards.remove(card);
			}else if(card instanceof RoomCard && room == null){
				room =  (RoomCard) card;
				listOfCards.remove(card);
			}
		}
		System.out.println("Solution created!");
		System.out.println("");
		return new Solution(room, character, weapon);
	}

	/**
	 * A method which initialises the players and creates the correct number of characters to players.
	 * @return
	 */
	public ArrayList<Player> initialisePlayer(){
		ArrayList<Player> players = new ArrayList<Player>();
		ArrayList<Character> cards = new ArrayList<Character>();

		//each player will get a character
		cards.add(new Character("Miss Scarlett"));
		cards.add(new Character("Colonel Mustard"));
		cards.add(new Character("Professor Plum"));
		cards.add(new Character("Reverend Green"));
		cards.add(new Character("Mrs. White"));
		cards.add(new Character("Mrs. Peacock"));

		//use a random number between 0 and 6 to assign a character to a player

		for(int i = 0;  i< this.numPlayers;i++){
			Character card = cards.get(i);
			players.add(new Player(i, card));
			System.out.println("Player " + i + " you will be  playing as " + card.toString() + ".");
		}
		return players;
	}

	/**
	 *This is a method which is used to deal all the cards out to the players.
	 */
	public void dealCards(){
		while(!this.allCards.isEmpty()){
			Player player = this.allPlayers.get(this.allCards.size() % this.numPlayers);
			Card newCard = this.allCards.get(0);
			player.deal(newCard); //give the current player the top card
			newCard.setOwner(player);//set the owner of the card to the current player
			this.allCards.remove(0);//remove the card from the deck
		}
		for(int i = 0; i <= this.numPlayers; i++){
			List<Card> cards = this.allPlayers.get(i).getCards();
			System.out.println("Your cards are: "+cards.toString());
		}
		System.out.println();	
	}

	/**
	 * sets the game state to over (false)
	 */
	public void setGameOver(){
		this.gameOver = true;
	}

	/**
	 * This method places all the weapons in the rooms
	 */
	public void initialiseWeapons(){
		Map<String, Room> rooms =  this.board.getRooms();
		List<Room> list = new ArrayList<Room>(rooms.values());

		//iterate over all cards
		for(int i = 0; i<listOfCards.size();i++){

			//if its a weapon card assign it a random room
			if(this.listOfCards.get(i) instanceof Weapon){
				Weapon weapon = (Weapon) listOfCards.get(i);

				//a random room index
				int random = (int) Math.round(Math.random()*list.size());

				//avoid index out of bounds exceptions
				if(random == list.size()){
					random--;				
				} 
				System.out.println("The " + weapon.toString()+" is in "+ rooms.get(random).toString());
				rooms.remove(random);
			}
		}
		System.out.println();
	}

	/**
	 * This method should begin playing the game
	 */
	public void playGame(){
		//current player
		Player currentPlayer;
		//all current players
		int currentPlayers = this.allPlayers.size();
		//random player starts 
		int random = (int) Math.round(Math.random()*this.allPlayers.size());
		if(random == this.allPlayers.size()){random--;}//avoid out of bounds exception
		//loop while game i not over
		while(!gameOver){
			//iterate over the players
			for(int i = random; i < this.allPlayers.size();i++){
				if(currentPlayers > 1 && !gameOver){
					currentPlayer = this.allPlayers.get(i);
					//is the current player in the game
					if(currentPlayer.getInGame()){
						currentPlayer.setGame(this);
						currentPlayer.takeTurn(false);
					}
					//check if a player just lost
					if(!currentPlayer.getInGame()){
						currentPlayers--;
						if(currentPlayers == 1){
							//last player remaining
							gameOver();
							return;
						}
					}
					if(gameOver){
						gameOver();
						return;
					}
				}
				//there must be 1 player left so end game
				else{
					gameOver();
					return;
				}
			}
			
		}
		//
	}

	/**
	 * This method determines what to do when the game is over, and declares a player the winner.
	 */
	public void gameOver(){
		setGameOver();
		for(int i = 0; i < this.allPlayers.size(); i++){
			if(this.allPlayers.get(i).getInGame()){
				System.out.println("Player " + this.allPlayers.get(i).getPlayerNumber() + " Wins!!!");
				break;
			}
		}
	}

	public int getNumPlayers(){
		return this.numPlayers;
	}
	public Solution getSolution(){
		return this.solution;
	}
	public Board getBoard(){
		return this.board;
	}
}