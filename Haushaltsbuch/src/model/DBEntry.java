package model;


public class DBEntry {
	
	public int ID;
	public String Art;
	public float Betrag;
	public String Grund;	
	public String Datum;
	
	/**
	 * 
	 * @param id
	 * @param Art
	 * @param Betrag
	 * @param Grund
	 * @param Datum
	 */
	public DBEntry(int id, String Art, float Betrag, String Grund, String Datum) {
		this.ID = id;
		this.Art = Art;
		this.Betrag = Betrag;
		this.Grund = Grund;
		this.Datum = Datum;
	}
	
	/**
	 * 
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * 
	 * @return Art
	 */
	public String getArt() {
		return Art;
	}

	/**
	 * 
	 * @param art
	 */
	public void setArt(String art) {
		Art = art;
	}

	/**
	 * 
	 * @return Betrag
	 */
	public float getBetrag() {
		return Betrag;
	}

	/**
	 * 
	 * @param betrag
	 */
	public void setBetrag(float betrag) {
		Betrag = betrag;
	}

	/**
	 * 
	 * @return Grund
	 */
	public String getGrund() {
		return Grund;
	}

	/**
	 * 
	 * @param grund
	 */
	public void setGrund(String grund) {
		Grund = grund;
	}

	/**
	 * 
	 * @return Datum
	 */
	public String getDatum() {
		return Datum;
	}

	/**
	 * 
	 * @param datum
	 */
	public void setDatum(String datum) {
		Datum = datum;
	}


	/**
	 * toString method for the output
	 * @return this.ID + this.Art + this.Betrag + this.Grund
	 */
	@Override
	public String toString() {
		return "ID: "+this.ID+" |Art: "+this.Art+ " |Betrag: "+this.Betrag+ " |Grund: "+this.Grund+" |Datum: "+this.Datum;
	}
	/**
	 * method for comparing different attributes
	 * @return true
	 * @return false
	 */
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
