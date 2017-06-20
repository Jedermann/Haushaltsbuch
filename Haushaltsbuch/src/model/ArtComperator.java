package model;

import java.util.Comparator;

public class ArtComperator implements Comparator<DBEntry>{

	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		return o1.Art.compareTo(o2.Art);
	}

}
