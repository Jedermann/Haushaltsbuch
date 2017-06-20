package model;

import java.util.Comparator;

public class GrundComperator implements Comparator<DBEntry>{

	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		// TODO Auto-generated method stub
		return o1.Grund.compareTo(o2.Grund);
	}

}
