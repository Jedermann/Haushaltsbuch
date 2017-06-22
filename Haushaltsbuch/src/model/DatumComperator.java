package model;

import java.util.Comparator;

public class DatumComperator implements Comparator<DBEntry>{

	/**
	 *  compare method to sort the attributes
	 *  @return o1.Datum.compareTo(o2.Datum)
	 */
	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		// TODO Auto-generated method stub
		return o1.Datum.compareTo(o2.Datum);
	}
}
