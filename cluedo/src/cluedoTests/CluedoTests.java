package cluedoTests;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import main.Main;
import parts.*;
import parts.Character;


public class CluedoTests {

	

	
	@Test
	public void testPlayAssignedCharacter1(){
		Character c = new Character("Mrs. White");
		Player p = new Player(1, c, new Scanner(System.in));
		
		assertTrue(p.getCharacter().getName() == "Mrs. White");
	}
	@Test
	public void testPlayAssignedCharacter2(){
		Character c = new Character("Mrs. White");
		Player p = new Player(1, c, new Scanner(System.in));
		
		assertFalse(p.getCharacter().getName() == "Colonel Mustard");
	}

//package cluedoTests;
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import main.Main;
//import parts.*;
//import parts.Character;
//
//
//public class CluedoTests {
//
//	@Test
//	public void checkValidAccusation1(){
//		Character character = new Character("Mrs. White");
//		RoomCard room = new RoomCard("Kitchen");
//		Weapon weapon = new Weapon("Spanner");
//
//		Accusation a = new Accusation(room, character, weapon);
//		Solution s = new Solution(room, character, weapon);
//
//		assertTrue(a.equals(s));
//	}
//
//	@Test
//	public void checkValidAccusation2(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Lead Pipe");
//
//		Accusation a = new Accusation(room, character, weapon);
//		Solution s = new Solution(room, character, weapon);
//
//		assertTrue(a.equals(s));
//	}
//
//	@Test
//	public void checkInvalidAccusation1(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Lead Pipe");
//
//		Character character2 = new Character("Mrs. White");
//		RoomCard room2 = new RoomCard("Kitchen");
//		Weapon weapon2 = new Weapon("Spanner");
//
//		Accusation a = new Accusation(room, character, weapon);
//		Solution s = new Solution(room2, character2, weapon2);
//
//		assertFalse(a.equals(s));
//	}
//
//	@Test
//	public void checkInvalidAccusation2(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Spanner");
//
//		Character character2 = new Character("Professor Plum");
//		RoomCard room2 = new RoomCard("Dining Room");
//		Weapon weapon2 = new Weapon("Lead Pipe");
//
//		Accusation a = new Accusation(room, character, weapon);
//		Solution s = new Solution(room2, character2, weapon2);
//
//		assertFalse(a.equals(s));
//	}
//
//	@Test
//	public void checkValidSuggestionCompare1(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Spanner");
//
//		Suggestion s = new Suggestion(room, character, weapon);
//
//		assert(s.compare(weapon) == weapon);
//	}
//
//	@Test
//	public void checkValidSuggestionCompare2(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Spanner");
//		Suggestion s = new Suggestion(room, character, weapon);
//
//		assert(s.compare(character) == character);
//	}
//	@Test
//	public void checkinvalidSuggestionCompare1(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Spanner");
//		Weapon weapon2 = new Weapon ("Lead Pipe");
//		Suggestion s = new Suggestion(room, character, weapon);
//
//		assert(s.compare(weapon) != weapon2);
//	}
//	@Test
//	public void checkinvalidSuggestionCompare2(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Spanner");
//
//		Suggestion s = new Suggestion(room, character, weapon);
//
//		assert(s.compare(weapon) != room);
//	}
//	@Test
//	public void checkinvalidSuggestionCompare3(){
//		Character character = new Character("Professor Plum");
//		RoomCard room = new RoomCard("Dining Room");
//		Weapon weapon = new Weapon("Spanner");
//		RoomCard room2 = new RoomCard("Lounge");
//		Suggestion s = new Suggestion(room, character, weapon);
//
//		assert(s.compare(room2) == null);
//	}
//	@Test
//	public void testMainInvalidNumPlayers1(){
//		try{
//			Main main = new Main(7);
//		}catch(IndexOutOfBoundsException e){
//
//		}
//	}
//	@Test
//	public void testMainInvalidNumPlayers2(){
//		try{
//			Main main = new Main(7);
//		}catch(IndexOutOfBoundsException e){
//
//		}
//	}
//	@Test
//	public void testMainValidNumPlayers1(){
//		Main main = new Main(2);
//		System.out.println(main.getNumPlayers());
//		assert main.getNumPlayers() == 2;
//
//	}
//}
	@Test
	public void checkValidAccusation2(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Lead Pipe");

		Suggestion a = new Suggestion(room, character, weapon);
		Suggestion s = new Suggestion(room, character, weapon);

		assertTrue(a.equals(s));
	}

	@Test
	public void checkInvalidAccusation1(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Lead Pipe");

		Character character2 = new Character("Mrs. White");
		RoomCard room2 = new RoomCard("Kitchen");
		Weapon weapon2 = new Weapon("Spanner");

		Suggestion a = new Suggestion(room, character, weapon);
		Suggestion s = new Suggestion(room2, character2, weapon2);

		assertFalse(a.equals(s));
	}

	@Test
	public void checkInvalidAccusation2(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Spanner");

		Character character2 = new Character("Professor Plum");
		RoomCard room2 = new RoomCard("Dining Room");
		Weapon weapon2 = new Weapon("Lead Pipe");

		Suggestion a = new Suggestion(room, character, weapon);
		Suggestion s = new Suggestion(room2, character2, weapon2);

		assertFalse(a.equals(s));
	}

	@Test
	public void checkValidSuggestionCompare1(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Spanner");

		Suggestion s = new Suggestion(room, character, weapon);

		assert(s.compare(weapon) == weapon);
	}

	@Test
	public void checkValidSuggestionCompare2(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Spanner");
		Suggestion s = new Suggestion(room, character, weapon);

		assert(s.compare(character) == character);
	}
	@Test
	public void checkinvalidSuggestionCompare1(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Spanner");
		Weapon weapon2 = new Weapon ("Lead Pipe");
		Suggestion s = new Suggestion(room, character, weapon);

		assert(s.compare(weapon) != weapon2);
	}
	@Test
	public void checkinvalidSuggestionCompare2(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Spanner");

		Suggestion s = new Suggestion(room, character, weapon);

		assert(s.compare(weapon) != room);
	}
	@Test
	public void checkinvalidSuggestionCompare3(){
		Character character = new Character("Professor Plum");
		RoomCard room = new RoomCard("Dining Room");
		Weapon weapon = new Weapon("Spanner");
		RoomCard room2 = new RoomCard("Lounge");
		Suggestion s = new Suggestion(room, character, weapon);

		assert(s.compare(room2) == null);
	}
	@Test
	public void testMainInvalidNumPlayers1() {
		try{
			Main main = new Main(7,new Scanner(System.in));
		}catch(IndexOutOfBoundsException e){

		}
	}
	@Test
	public void testMainInvalidNumPlayers2(){
		try{
			Main main = new Main(7,new Scanner(System.in));
		}catch(IndexOutOfBoundsException e){

		}
	}
	@Test
	public void testMainValidNumPlayers1(){
		Main main = new Main(2,new Scanner(System.in));
		System.out.println(main.getNumPlayers());
		assert main.getNumPlayers() == 2;

	}
}

