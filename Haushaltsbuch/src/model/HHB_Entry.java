package model;

public class HHB_Entry {
	private int eintragid;
	private String eart;
	private double betrag;
	private String datum;
	private String grund;
	
	/**
	 * Konstruktor für Eintrag
	 * @param eintragid
	 * @param betrag
	 * @param datum
	 * @param grund
	 * @param bemerkung
	 */
	public HHB_Entry(int eintragid, String eart, double ebetrag, String egrund, String datum) {
		super();
		this.eintragid = eintragid;
		this.eart = eart;
		this.betrag = ebetrag;
		this.datum = datum;
		this.grund = egrund;
	}
	
	/**
	 * Getter für eintragid
	 * @return eintragid
	 */
	public int getEintragid() {
		return eintragid;
	}
	
	/**
	 * Setter für eintragid
	 * @param eintragid
	 */
	public void setEintragid(int id) {
		eintragid = id;
	}
	
	public String getEart() {
		return eart;
	}
	
	/**
	 * Setter für eart
	 * @param eintragid
	 */
	public void setEart(int id) {
		eintragid = id;
	}
	
	/**
	 * Getter für betrag
	 * @return betrag
	 */
	public double getbetrag() {
		return betrag;
	}
	
	/**
	 * Setter für betrag
	 * @param betrag
	 */
	public void setEbetrag(double ebetrag) {
		betrag = ebetrag;
	}
	
	/**
	 * Getter für Edatum
	 * @return Egrund
	 */
	public String getEdatum() {
		return datum;
	}
	
	/**
	 * Setter für Edatum
	 * @param edatum
	 */
	public void setEdatum(String edatum) {
		datum = edatum;
	}
	
	/**
	 * Getter für Egrund
	 * @return Egrund
	 */
	public String getEgrund() {
		return grund;
	}
	
	/**
	 * Setter für Egrund
	 * @param egrund
	 */
	public void setEgrund(String egrund) {
		grund = egrund;
	}
	
	/**
	 * toString - Methode für die Ausgabe
	 */
	public String toString() {
		return "Einnahme [Eid=" + eintragid + ", Ebetrag=" + betrag + 
				", Edatum=" + datum + ", Egrund=" + grund;
	}
}
