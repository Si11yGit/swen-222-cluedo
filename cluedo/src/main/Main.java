package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import board.Board;
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
		//private HashMap<String, Object> players;//houses a map of players name -> object
		private ArrayList<Card> allCards;
		private ArrayList<Player> allPlayers;
		private int numPlayers;
		private Solution solution; //solution object
		private Board board;//reference to a board
		private boolean gameOver;//whether the game is over or not 
		
		public Main(int numPlayers){
			this.numPlayers=numPlayers;
			this.allCards = initialiseCards();
			this.allPlayers = initialisePlayer();
			this.board = new Board(allPlayers); 
		}

		
			//25x25 board
			//player make movement by doing letter/number position
			//random number for their turn for how far they can move (dice roll)
			//make an accusation/suggestion
			//solution class
			//accuse/suggest are two choice 
			//if you accuse and are wrong you are out of the game 
			//each player has a set of card
		/**
		 * initialise all the cards 
		 * @return
		 */
		public ArrayList<Card> initialiseCards(){//create method to create all the cards and initialise them into the field
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
		public Solution initialiseSolution(){//a method to initialise solution/generate one
			//new Solution();
			return null;
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
				//the random number to assign to a player
				int rand = (int) Math.round(Math.random()*cards.size());
				if(rand == cards.size()){rand--;}
				Character card = cards.get(rand);
				
				players.add(new Player(i, card));
				cards.remove(cards.get(rand)); 
				System.out.println("Player " + i + " you will be  playing as " + card.toString() + ".");
			}
			return players;
		}
		
		//place the weapons
		public void initialiseWeapons(){
			
		}
		
		//start the game
		public void playGame(){
			
		}
		
		//deal cards
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
		
		//set game over
		public void setGameOver(){
			this.gameOver = true;
		}
		
		//game over, what to do when game is over, declare somebody the winner
		public void gameOver(){}
		
		//get players
		public int getNumPlayers(){
			return this.numPlayers;
		}
		
		//get solution
		public Solution getSolution(){
			return this.solution;
		}
}