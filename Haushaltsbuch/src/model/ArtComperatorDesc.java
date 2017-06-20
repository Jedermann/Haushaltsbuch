package model;

import java.util.Comparator;

public class ArtComperatorDesc implements Comparator<DBEntry>{

	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		// TODO Auto-generated method stub
		if((o1.Art.compareTo(o2.Art)) == 0){
			return 0;
		}
		else if((o1.Art.compareTo(o2.Art)) == 1){
			return -1;
		}
		else{
			return 1;
		}
		
	}
}