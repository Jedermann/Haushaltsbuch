package model;

import java.util.Comparator;

public class DatumComperator implements Comparator<DBEntry>{

	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		// TODO Auto-generated method stub
		return o1.Datum.compareTo(o2.Datum);
	}
}
