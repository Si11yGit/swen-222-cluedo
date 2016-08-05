package parts;

public class Accusation {
	private RoomCard room;
	private Character character;
	private Weapon weapon;
	
	public Accusation(RoomCard room, Character character, Weapon weapon){
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
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
		Accusation other = (Accusation) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}
	
	/**
	 * a special equals method for comparing an accusation to a solution
	 * 
	 * @param Solution
	 *  @return boolean
	 */
	public boolean equals(Solution sol){
		if(sol.getCharacter().equals(this.character)){
			if(sol.getRoom().equals(this.room)){
				if(sol.getWeapon().equals(this.weapon)){
					return true; // returns true if all three of the cards in the accusation are correct
				}
			}
		}
		return false;
	}
	

}
