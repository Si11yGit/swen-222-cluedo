package parts;

public class Suggestion extends Accusation{
	private RoomCard room;
	private Character character;
	private Weapon weapon;
	
	public Suggestion(RoomCard room, Character character, Weapon weapon){
		super(room, character, weapon);
		this.room = room;
		this.character = character;
		this.weapon = weapon;
	}
	
	/**
	 * this method presents the suggestion in a cluedo style manner
	 */
	public String toString(){
		return "It was " + character.toString() + ", in the " + room.toString() + ", with the " + weapon.toString() + ".";
	}
	
	/**
	 * compare method for comparing the cards in the suggestion 
	 * to the cards in the solution, returns any card that  
	 * matches a card in the solution
	 * 
	 *  @param Solution
	 *  @return Card
	 */
	public Card compare(Card card){
		if(card instanceof Character){
			if(card.equals(this.character)){
				return card;	
			}
		}
		else if(card instanceof Weapon){
			if(card.equals(this.weapon)){
				return card;
			}
		}
		else if(card instanceof RoomCard){
			if(card.equals(this.room)){
				return card;
			}
			
		}	
		return null;
	}
}
