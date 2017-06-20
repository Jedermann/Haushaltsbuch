package model;


public class DBEntry {
	
	public int ID;
	public String Art;
	public float Betrag;
	public String Grund;	
	public String Datum;
	
	public DBEntry(int id, String Art, float Betrag, String Grund, String Datum) {
		// Die Werte setzen...
		this.ID = id;
		this.Art = Art;
		this.Betrag = Betrag;
		this.Grund = Grund;
		this.Datum = Datum;
	}
		
	@Override
	public String toString() {
		return "ID: "+this.ID+" |Art: "+this.Art+ " |Betrag: "+this.Betrag+ " |Grund: "+this.Grund+" |Datum: "+this.Datum;
	}

	@Override
	public boolean equals(Object arg0) {
		DBEntry xDB = (DBEntry)arg0;
		
		if((xDB.ID == this.ID)&&(xDB.Art == this.Art)&&(xDB.Betrag==this.Betrag)&&(xDB.Grund==this.Grund)&&(xDB.Datum==this.Datum)){
			return true;
		}
		else{
			return false;
		}
	}
}
