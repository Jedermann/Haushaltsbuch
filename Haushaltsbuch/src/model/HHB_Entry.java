package model;

public class HHB_Entry {
	private int eintragid;
	private String eart;
	private double betrag;
	private String datum;
	private String grund;
	
	/**
	 * Konstruktor f�r Eintrag
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
	 * Getter f�r eintragid
	 * @return eintragid
	 */
	public int getEintragid() {
		return eintragid;
	}
	
	/**
	 * Setter f�r eintragid
	 * @param eintragid
	 */
	public void setEintragid(int id) {
		eintragid = id;
	}
	
	public String getEart() {
		return eart;
	}
	
	/**
	 * Setter f�r eart
	 * @param eintragid
	 */
	public void setEart(int id) {
		eintragid = id;
	}
	
	/**
	 * Getter f�r betrag
	 * @return betrag
	 */
	public double getbetrag() {
		return betrag;
	}
	
	/**
	 * Setter f�r betrag
	 * @param betrag
	 */
	public void setEbetrag(double ebetrag) {
		betrag = ebetrag;
	}
	
	/**
	 * Getter f�r Edatum
	 * @return Egrund
	 */
	public String getEdatum() {
		return datum;
	}
	
	/**
	 * Setter f�r Edatum
	 * @param edatum
	 */
	public void setEdatum(String edatum) {
		datum = edatum;
	}
	
	/**
	 * Getter f�r Egrund
	 * @return Egrund
	 */
	public String getEgrund() {
		return grund;
	}
	
	/**
	 * Setter f�r Egrund
	 * @param egrund
	 */
	public void setEgrund(String egrund) {
		grund = egrund;
	}
	
	/**
	 * toString - Methode f�r die Ausgabe
	 */
	public String toString() {
		return "Einnahme [Eid=" + eintragid + ", Ebetrag=" + betrag + 
				", Edatum=" + datum + ", Egrund=" + grund;
	}
}
