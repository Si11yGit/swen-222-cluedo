package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import board.Board;
import board.Hallway;
import board.Room;
import board.Square;
import parts.Card;
import parts.Player;
import parts.RoomCard;
import parts.Solution;
import parts.Suggestion;
import parts.Weapon;
import parts.Character;

/**
 *
 *
 * @author clarkebenj1, henry
 *
 */
public class Main {
	//fields:
	private ArrayList<Card> allCards;
	private ArrayList<Card> listOfCards;
	private ArrayList<Player> allPlayers;
	private ArrayList<Card> preservedCardList; /// for testing purposes
	private Map<String,Card> cardsearch = new HashMap<String,Card>();
	private int numPlayers;
	private Solution solution; //solution object
	private Board board;//reference to a board
	private boolean gameOver;//whether the game is over or not
	private Scanner scan;

	public Main(int numPlayers, Scanner scan){
		this.numPlayers=numPlayers;
		this.allCards = initialiseCards();
		this.listOfCards = this.allCards;
		this.preservedCardList = this.allCards;
		for(Card card: allCards) {
			cardsearch.put(card.getName(), card);
		}
		this.listOfCards = allCards;
		Collections.shuffle(this.allCards);
		this.scan = scan;
		this.board = new Board();
		this.solution =  initialiseSolution();
		this.allPlayers = initialisePlayer();
		this.board.update();

		//initialiseWeapons();

		dealCards();
		playGame();
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
			players.add(new Player(i, card, scan));

			Hallway position;
			switch(i) {
			case 0:
				position = (Hallway) board.getBoard()[33][1];
				players.get(i).setPosition(position);
				position.setPlayer(players.get(i));
				break;
			case 1:
				position = (Hallway) board.getBoard()[47][15];
				players.get(i).setPosition(position);
				position.setPlayer(players.get(i));
				break;
			case 2:
				position = (Hallway) board.getBoard()[1][11];
				players.get(i).setPosition(position);
				position.setPlayer(players.get(i));
				break;
			case 3:
				position = (Hallway) board.getBoard()[19][49];
				players.get(i).setPosition(position);
				position.setPlayer(players.get(i));
				break;
			case 4:
				position = (Hallway) board.getBoard()[29][49];
				players.get(i).setPosition(position);
				position.setPlayer(players.get(i));
				break;
			case 5:
				position = (Hallway) board.getBoard()[1][37];
				players.get(i).setPosition(position);
				position.setPlayer(players.get(i));
				break;
			}

			//players.get(i).setGame(this);
			//System.out.println("Player " + i + " you will be  playing as " + card.toString() + ".");
			System.out.println("Player " + i + " you will be  playing as " + card.name() + ".");
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
		for(int i = 1; i <= this.numPlayers; ++i){
			List<Card> cards = this.allPlayers.get(i-1).getCards();
//			System.out.println("Your cards are: "+cards.toString());
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
	//Will be used with gui, pointless in current version


//	public void initialiseWeapons(){
//		Map<String, Room> rooms =  this.board.getRooms();
//		List<Room> list = new ArrayList<Room>(rooms.values());
//
//		//iterate over all cards
//		for(int i = 0; i<listOfCards.size();i++){
//
//			//if its a weapon card assign it a random room
//			if(this.listOfCards.get(i) instanceof Weapon){
//				Weapon weapon = (Weapon) listOfCards.get(i);
//
//				//a random room index
//				int random = (int) Math.round(Math.random()*list.size());
//
//				//avoid index out of bounds exceptions
//				if(random == list.size()){
//					random--;
//				}
//				System.out.println("The " + weapon.toString()+" is in "+ rooms.get(random).toString());
//				rooms.remove(random);
//			}
//		}
//		System.out.println();
//	}

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
				if(!gameOver){
					currentPlayer = this.allPlayers.get(i);
					//is the current player in the game
					if(currentPlayer.getInGame()){
						turn(currentPlayer);
					}
					//check if a player just lost
					if(!currentPlayer.getInGame()){
						currentPlayers--;
						if(currentPlayers == 0){
							//no players remaining
							setGameOver();
							System.out.println("All players have failed to identify the killer!! Game Over");
							return;
						}
					}
				}
			}

		}
	}

	/**
	 * Turn method for a player
	 */
	public void turn(Player player) {
		boolean check = false;								//used for user response loops
		System.out.println(player.character().name() + " its your turn!");
		System.out.println("");
		System.out.println("would you like to make an accusation? (y/n)");
		check = false;
		String response = "";
		while (!check) {
			if (scan.hasNext()) {
				response = scan.nextLine();
				if (response.toLowerCase().equals("y")) {
					check = true;
					Suggestion accusation = player.makeAccusation(cardsearch);
					boolean right = accusation.compare(solution);
					if (right) {
						System.out.println("Correct!!!! " + player.character().getName() + " you win!!");
						setGameOver();
					} else {
						System.out.println("Incorrect!!!! Sorry " + player.character().getName() + " you are out!!");
						player.lose();
						return;
					}
				} else if (response.toLowerCase().equals("n")) {
					check = true;
				} else if (response != null) {
					System.out.print("enter one of the two choices (y/n)");
				}
			}
		}
		System.out.println("");
		int roll = player.diceRoll();
		System.out.println("you rolled " + roll);
		if (player.checkForPassageWays()) {
			System.out.println("Secret Tunnel in Room!! would you like to travel to " + ((Room)player.getPosition()).getTunnel() + "? (y/n)");
			check = false;
			while (!check) {
				if (scan.hasNext()) {
					response = scan.nextLine();
					if (response.toLowerCase().equals("y")) {
						check = true;

					} else if (response.toLowerCase().equals("n")) {
						System.out.println("How far down would you like to move? (use negative for up)");
						boolean check2 = false;
						while (!check2) {

						}
						System.out.println("How far right would you like to move? (use a negative for left)");
						check2 = false;
						while(!check2) {

						}
						check = true;
					} else if (response != null) {
						System.out.print("enter one of the two choices (y/n)");
					}
				}
			}
		} else {
			System.out.println("How far down would you like to move? (use negative for up)");
			check = false;
			while (check == false) {

			}
			System.out.println("How far right would you like to move? (use a negative for left)");
			check = false;
			while(check == false) {

			}
		}


		if(player.getPosition() instanceof Room) {
			System.out.println("you must make a suggestion");
			Suggestion sug = player.makeSuggestion(cardsearch);

			//moves accused to room of suggestion
			sug.getCharacter().getOwner().setPosition(player.getPosition());
			((Room)player.getPosition()).addPlayer(sug.getCharacter().getOwner());

			for(Player p: allPlayers) {
				if (!p.equals(player)) {
					Card refuted = p.refuteSuggestion(sug);
					if(refuted != null) {
						System.out.println(p.character().name() + "presents" + refuted.getName());
						break;
					}
				}
			}
		}
		System.out.println("would you like to make an accusation? (y/n)");
		check = false;
		while (!check) {
			if (scan.hasNext()) {
				response = scan.nextLine();
				if (response.toLowerCase().equals("y")) {
					check = true;
					Suggestion accusation = player.makeAccusation(cardsearch);
					boolean right = accusation.compare(solution);
					if (right) {
						System.out.println("Correct!!!! " + player.character().getName() + " you win!!");
						setGameOver();
					} else {
						System.out.println("Incorrect!!!! Sorry " + player.character().getName() + " you are out!!");
						player.lose();
						return;
					}
				} else if (response.toLowerCase().equals("n")) {
					check = true;
				} else if (response != null) {
					System.out.print("enter one of the two choices (y/n)");
				}
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
	public ArrayList<Card> getCards(){
		return this.preservedCardList;
	}
}