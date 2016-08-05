package board;

import parts.Player;

public interface Enterable extends Square {
	public void removePlayer(Player player);

	public void addPlayer(Player player);
}
