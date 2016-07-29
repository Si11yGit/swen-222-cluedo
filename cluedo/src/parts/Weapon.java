package parts;

public class Weapon extends Card{
	private String name;
	private Player Owner;
	private Room room;

	public Weapon(String name) {
		super(name);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public Player getOwner() {
		return Owner;
	}

	public void setOwner(Player owner) {
		Owner = owner;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((Owner == null) ? 0 : Owner.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weapon other = (Weapon) obj;
		if (Owner == null) {
			if (other.Owner != null)
				return false;
		} else if (!Owner.equals(other.Owner))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

}
