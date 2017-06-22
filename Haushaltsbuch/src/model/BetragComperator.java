package model;

import java.util.Comparator;

public class BetragComperator implements Comparator<DBEntry>{
	
	/**
	 *  compare method to sort the attributes
	 *  @return 0
	 *  @return -1
	 *  @return 1
	 */
	@Override
	public int compare(DBEntry o1, DBEntry o2) {
		// TODO Auto-generated method stub
		if(o1.Betrag == o2.Betrag){
			return 0;
		}else if(o1.Betrag> o2.Betrag){
			return 1;
		}else{
			return -1;
		}
	}

}
