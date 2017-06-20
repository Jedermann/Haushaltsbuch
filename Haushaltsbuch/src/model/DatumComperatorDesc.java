package model;

import java.util.Comparator;

public class DatumComperatorDesc implements Comparator<DBEntry>{

	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		// TODO Auto-generated method stub
		if((o1.Datum.compareTo(o2.Datum)) == 0){
			return 0;
		}
		else if((o1.Datum.compareTo(o2.Datum)) == 1){
			return -1;
		}
		else{
			return 1;
		}
		
	}
	
	
}
