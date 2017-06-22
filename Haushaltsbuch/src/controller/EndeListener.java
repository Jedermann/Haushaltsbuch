package controller;
import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


public class EndeListener implements ActionListener {
	
	private Eintrag_Tabelle gui;
	/**
	 * 
	 * @param gui
	 */
	public EndeListener(Eintrag_Tabelle gui) {
		this.gui = gui;
	}
	/**
	 * Action performed method for closing the Gui
	 * @param ActionEvent arg0
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}
}
