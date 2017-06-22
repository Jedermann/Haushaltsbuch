package view;

import controller.*;
import model.DBEntry;
import model.Datenbank;
import model.ArtComperatorDesc;
import model.ArtComperator;
import model.BetragComperatorDesc;
import model.BetragComperator;
import model.GrundComperatorDesc;
import model.GrundComperator;
import model.DatumComperatorDesc;
import model.DatumComperator;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.event.*;
import net.miginfocom.swing.MigLayout;



import java.util.*;
import java.awt.Font;
import java.awt.Color;

/**
 * ContactTable View
 * @author Eller Martin, Schmidt Manuel
 * @version 2.0
 */
public class Eintrag_Tabelle extends JFrame {
	
	

	private JPanel contentPane;
	private JTextField tfDatum;
	private JTextField tfArt;
	private JTextField tfGrund;
	private JTextField tfBetrag;
	private JTable table;
	private JButton btnEnde;
	private JButton btnUebernehmen;
	private UebernehmenListener uebList;
	
	/**
	 * Stores the current data
	 */
	public ArrayList<DBEntry> buchungen;
	
	private JLabel lblVerfgbar = new JLabel("{ Verfügbar }");
	private JMenuBar menuBar;
	private JMenu mnHome;
	private JMenuItem mntmRefreshData;

	private JLabel lblAlienddb;
	private JMenu mnSort;
	private JMenuItem mntmArt;
	private JMenuItem mntmIBetrag;
	private JMenuItem mntmGrund;
	private JMenuItem mntmDatum;
	private JMenu mnSortdescending;
	private JMenuItem mntmArtDesc;
	private JMenuItem mntmBetragDesc;
	private JMenuItem mntmGrundDesc;
	private JMenuItem mntmDatumDesc;
	private JLabel lblLabel;
	private JLabel lblNewLabel;
	private JLabel lblDatenbankStatus;

	/**
	 * Constructor
	 */
	public Eintrag_Tabelle() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		initialize();
		
		try {
            refreshTableView();
        } catch (Exception ex) {
        	ex.printStackTrace();
        	lblVerfgbar.setText("{ FEHLER BEI VERBINDUNGSAUFBAU }");
        	lblVerfgbar.setForeground(Color.RED);
        }
	}

	/**
	 * INIT-Method
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Haushaltsbuch (1.0)");
		setBounds(200, 200, 1117, 832);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		setJMenuBar(menuBar);
		
		mnHome = new JMenu(" Home");
		mnHome.setForeground(Color.WHITE);
		menuBar.add(mnHome);
		
		mntmRefreshData = new JMenuItem("Refresh Data");
		mntmRefreshData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTableView();
			}
		});
		mnHome.add(mntmRefreshData);
		
		mnSort = new JMenu("Sort \u25B2");
		mnSort.setForeground(Color.WHITE);
		menuBar.add(mnSort);
		
		mntmArt = new JMenuItem("Art");
		mntmArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortDatum();
			}
		});
		mnSort.add(mntmArt);
		
		mntmIBetrag = new JMenuItem("Betrag");
		mntmIBetrag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortArt();
			}
		});
		mnSort.add(mntmIBetrag);
		
		mntmGrund = new JMenuItem("Grund");
		mntmGrund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortBetrag();
			}
		});
		mnSort.add(mntmGrund);
		
		mntmDatum = new JMenuItem("Datum");
		mntmDatum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortGrund();
			}
		});
		mnSort.add(mntmDatum);
		
		mnSortdescending = new JMenu("Sort \u25BC");
		mnSortdescending.setForeground(Color.WHITE);
		menuBar.add(mnSortdescending);
		
		mntmArtDesc = new JMenuItem("Art");
		mntmArtDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortDatumDesc();
			}
		});
		mnSortdescending.add(mntmArtDesc);
		
		mntmBetragDesc = new JMenuItem("Betrag");
		mntmBetragDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortArtDesc();
			}
		});
		mnSortdescending.add(mntmBetragDesc);
		
		mntmGrundDesc = new JMenuItem("Grund");
		mntmGrundDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortBetragDesc();
			}
		});
		mnSortdescending.add(mntmGrundDesc);
		
		mntmDatumDesc = new JMenuItem("Datum");
		mntmDatumDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortGrundDesc();
			}
		});
		mnSortdescending.add(mntmDatumDesc);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new MigLayout("", "[][191.00px,grow][13px][157.00px,grow][10px][120px,grow][9px][89px,grow]", "[][14px][20px][14px][23px][][][14px][][][105px,grow][23px][]"));

		
		lblAlienddb = new JLabel("");
		lblAlienddb.setFont(new Font("Square Unique", Font.PLAIN, 24));
		contentPane.add(lblAlienddb, "cell 1 0");

		JLabel lblArt = new JLabel("Art");
		contentPane.add(lblArt, "cell 1 1,growx,aligny top");

		tfDatum = new JTextField();
		tfDatum.addActionListener(new TfArtActionListener());
		
				JLabel lblBetrag = new JLabel("Betrag");
				contentPane.add(lblBetrag, "cell 5 1,alignx left,aligny top");
		contentPane.add(tfDatum, "cell 1 2 3 1,growx,aligny top");
		tfDatum.setColumns(10);
		
				tfArt = new JTextField();
				tfArt.addActionListener(new TfGrundActionListener());
				contentPane.add(tfArt, "cell 5 2 3 1,growx,aligny top");
				tfArt.setColumns(10);

		JLabel lblGrund = new JLabel("Grund");
		contentPane.add(lblGrund, "cell 1 3,growx,aligny top");
		uebList = new UebernehmenListener();
		
		tfBetrag = new JTextField();
		// --- gleicher ActionListener wie Übernehmenbutton ---
		tfBetrag.addActionListener(uebList);
		
				JLabel lblDatum = new JLabel("Datum");
				contentPane.add(lblDatum, "cell 5 3,growx,aligny top");
		contentPane.add(tfBetrag, "cell 1 4 3 1,growx,aligny center");
		tfBetrag.setColumns(10);
				
						tfGrund = new JTextField();
						tfGrund.addActionListener(new TfBetragActionListener());
						contentPane.add(tfGrund, "cell 5 4 3 1,growx,aligny top");
						tfGrund.setColumns(10);
				
						btnUebernehmen = new JButton("Buchung einrechnen ");
						btnUebernehmen.setBackground(Color.RED);
						btnUebernehmen.setForeground(Color.BLACK);
						btnUebernehmen.addActionListener(uebList);
						JLabel lblBuchungen = new JLabel("Buchungen");
						contentPane.add(lblBuchungen, "cell 1 5,growx,aligny top");
						contentPane.add(btnUebernehmen, "cell 7 5,growx,aligny top");		

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 6 7 5,grow");

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Art", "Betrag", "Grund", "Datum"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		btnEnde = new JButton("Schlie\u00DFen");
		btnEnde.setForeground(Color.WHITE);
		btnEnde.setBackground(Color.DARK_GRAY);
		btnEnde.addActionListener(new EndeListener(this));
		contentPane.add(btnEnde, "cell 7 11,growx,aligny top");

		JButton btnLoeschen = new JButton("Eintrag entfernen\r\n");
		btnLoeschen.setBackground(Color.DARK_GRAY);
		btnLoeschen.setForeground(Color.WHITE);
		btnLoeschen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					if (table.getSelectedRow() >= 0) {
						
						int ID = (int)table.getValueAt(table.getSelectedRow(), 0);						

						String Art = (String)table.getValueAt(table.getSelectedRow(), 1);

						float Betrag = (float)table.getValueAt(table.getSelectedRow(), 2);

						String Grund = (String)table.getValueAt(table.getSelectedRow(), 3);
						
						String Datum = (String)table.getValueAt(table.getSelectedRow(), 4);
						
						
						DBEntry xDel = new DBEntry(ID, Art, Betrag, Grund, Datum);
						Datenbank.deleteSelectedEntry(xDel);
						refreshTableView();
					}
				}
				
			
		});
		contentPane.add(btnLoeschen, "flowx,cell 1 11 3 1,alignx left,aligny top");
		
		lblLabel = new JLabel("Entwickelt von Manuel Schmidt/Eller Martin");
		lblLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLabel.setForeground(Color.GRAY);
		contentPane.add(lblLabel, "cell 1 12");
		
		lblNewLabel = new JLabel("3BHWII / 2016/17");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(lblNewLabel, "cell 7 12,alignx right");
		
		lblDatenbankStatus = new JLabel("Datenbank Status:");
		lblDatenbankStatus.setForeground(Color.DARK_GRAY);
		lblDatenbankStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblDatenbankStatus, "cell 3 11");
		
		
		lblVerfgbar.setForeground(new Color(60, 179, 113));
		lblVerfgbar.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblVerfgbar, "cell 3 11");
	}
	
	/**
	 * Refreshes the dataviewport and loads the information from the database
	 */
	public void refreshTableView(){
		try {
            buchungen = Datenbank.getAllEntrys();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		refreshTable();
	}
	
	public void refreshTable(){
	    try {
	        try {
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	        tableModel.setRowCount(0);
	        
	        for (DBEntry dbe : buchungen) {
	            Object rowData[] = {dbe.ID, dbe.Art, dbe.Betrag,dbe.Grund,dbe.Datum};
	            tableModel.addRow(rowData);
	        }
	        


	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }

	}
		
	/**
	 * 
	 */
	public class UebernehmenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tfDatum.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "kein Name angegeben!");
				tfDatum.requestFocus();
			} else {
				int id = Datenbank.getHighestID();
				int newID = id+1;
				
				if(id == -1){
					JOptionPane.showMessageDialog(contentPane, "The database connection could not be established!");
				}
				
				try{
					DBEntry xNewEntry = new DBEntry(newID, tfDatum.getText(), Float.parseFloat(tfArt.getText()), tfBetrag.getText(), tfGrund.getText());		
					System.out.println("New ID:"+String.valueOf(newID));
					
					Datenbank.insertNewEntry(xNewEntry);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				refreshTableView();
			}
		}
	}
	
	

	private class TfArtActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			tfArt.requestFocus();
		}
	}
	private class TfGrundActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			tfGrund.requestFocus();
		}
	}
	private class TfBetragActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			tfBetrag.requestFocus();
		}
	}
	
	
	public void sortDatum(){
		Collections.sort(buchungen,new DatumComperator());
		refreshTable();
		System.out.println("Datum comparator");
	}
	
	public void sortArt(){
		Collections.sort(buchungen,new ArtComperator());
		refreshTable();
		System.out.println("Art comparator");
	}
	
	public void sortBetrag(){
		Collections.sort(buchungen,new BetragComperator());
		refreshTable();
		System.out.println("Betrag comparator");
	}
	
	public void sortGrund(){
		Collections.sort(buchungen,new GrundComperator());
		refreshTable();
		System.out.println("Grund comparator");
	}
	
	public void sortDatumDesc(){
		Collections.sort(buchungen,new DatumComperatorDesc());
		refreshTable();
		System.out.println("Datum comparator Desc");
	}
	
	public void sortArtDesc(){
		Collections.sort(buchungen,new ArtComperatorDesc());
		refreshTable();
		System.out.println("Art comparator Desc");
	}
	
	public void sortBetragDesc(){
		Collections.sort(buchungen,new BetragComperatorDesc());
		refreshTable();
		System.out.println("Betrag comparator Desc");
	}
	
	public void sortGrundDesc(){
		Collections.sort(buchungen,new GrundComperatorDesc());
		refreshTable();
		System.out.println("Grund comparator Desc");
	}
}	