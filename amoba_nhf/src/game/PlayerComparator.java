package game;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player>{

	@Override
	public int compare(Player p1, Player p2) {
		return p2.getPower()-p1.getPower();
	}

}
