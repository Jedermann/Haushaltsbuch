package model;

import java.util.Comparator;

public class ArtComperator implements Comparator<DBEntry>{
	
	/**
	 *  compare method to sort the attributes
	 *  @return o1.Art.compareTo(o2.Art)
	 */
	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		return o1.Art.compareTo(o2.Art);
	}

}
