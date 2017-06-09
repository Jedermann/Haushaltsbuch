package view;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.Databasemanager;
import model.HHB_Entry;
import net.miginfocom.swing.MigLayout;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class View_Main extends JFrame {

	ArrayList<HHB_Entry> entry_list = new ArrayList<>();

	private JPanel contentPane;
	private JTextField tfArt;
	private JTextField tfBetrag;
	private JTextField tfGrund;
	private JTextField tfDatum;
	private JTable table;
	private JButton btnEnde;
	private JButton btnEingabefelderLeeren;

	/**
	 * Launch 
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Main window = new View_Main();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create application
	 */
	public View_Main() {
		initialize();
		receiveDataFromDatabase();
		refreshTable();
	}

	/**
	 * Initialize frame contents
	 */
	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1005, 728);
		setTitle("Haushaltsbuch | SWP-SEPM");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(
				new MigLayout("", "[131px,grow][13px][132px,grow][10px][120px,grow][9px][89px,grow][120px,grow][5px]",
						"[14px][20px][14px][23px][14px][20px][14px][105px,grow][23px][105px,grow][23px]"));

		JLabel lblArt = new JLabel("Art");
		contentPane.add(lblArt, "cell 2 0,growx,aligny top");

		tfArt = new JTextField();
		contentPane.add(tfArt, "cell 2 1 3 1,growx,aligny top");
		tfArt.setColumns(10);

		JLabel lblBetrag = new JLabel("Betrag");
		contentPane.add(lblBetrag, "cell 5 0,alignx left,aligny top");

		tfBetrag = new JTextField();
		contentPane.add(tfBetrag, "cell 5 1 3 1,growx,aligny top");
		tfBetrag.setColumns(10);

		JLabel lblGrund = new JLabel("Grund");
		contentPane.add(lblGrund, "cell 5 2,growx,aligny top");

		tfGrund = new JTextField();
		contentPane.add(tfGrund, "cell 5 3 3 1,growx,aligny top");
		tfGrund.setColumns(10);

		JLabel lblDatum = new JLabel("Datum");
		contentPane.add(lblDatum, "cell 2 2,growx,aligny top");

		tfDatum = new JTextField();
		contentPane.add(tfDatum, "cell 2 3 3 1,growx,aligny center");
		tfDatum.setColumns(10);

		btnEingabefelderLeeren = new JButton("Eingabefelder leeren");
		btnEingabefelderLeeren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tfArt.setText("");
				tfBetrag.setText("");
				tfDatum.setText("");
				tfGrund.setText("");

			}
		});
		contentPane.add(btnEingabefelderLeeren, "cell 2 6 3 1, growx, aligny bottom");

		JButton btnUebernehmen = new JButton("hinzuf\u00FCgen >>");
		btnUebernehmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int idNew = Databasemanager.getHighestIDUser() + 1;

					

					HHB_Entry xNew = new HHB_Entry(idNew, tfArt.getText(), Double.parseDouble(tfBetrag.getText()),
							tfGrund.getText(), tfDatum.getText());

					receiveDataFromDatabase();
					refreshTable();

				} catch (Exception ex) {

				}

			}
		});
		contentPane.add(btnUebernehmen, "cell 5 6 3 1,growx,aligny top");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 7 8 4,grow");

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Art", "Betrag", "Grund", "Datum" }));
		JLabel lblEntryliste = new JLabel("Haushaltsbuch");
		contentPane.add(lblEntryliste, "cell 0 6,growx,aligny bottom");

		btnEnde = new JButton("Ende");
		btnEnde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int reply = JOptionPane.showConfirmDialog(null, "Save changes?", "Commit?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					commitAllEntrys();
					System.exit(0);
				} else {
					System.exit(0);
				}

			}
		});
		contentPane.add(btnEnde, "cell 7 11,growx,aligny top");

		JButton btnLoeschen = new JButton("markierten Eintrag l\u00F6schen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
				String eart = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
				double betrag = Double.parseDouble((String) table.getModel().getValueAt(table.getSelectedRow(), 2));
				String grund = (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
				String datum = (String) table.getModel().getValueAt(table.getSelectedRow(), 4);
				
				HHB_Entry xDel = new HHB_Entry(id, eart, betrag, grund, datum);
				Databasemanager.deleteSelectedEntry(xDel);

				receiveDataFromDatabase();
				refreshTable();

			}
		});
		contentPane.add(btnLoeschen, "cell 0 11 3 1,alignx left,aligny top");
	}

	private void receiveDataFromDatabase() {
		try {

			entry_list = Databasemanager.getAllEntrys();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}

	// Works faster than for this small programm ...
	// normally you should not delete all entrys and then commit the objects
	// stored locally
	private void commitAllEntrys() {

		try {
			Databasemanager.commitAllEntrys(entry_list);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void refreshTable() {

		try {

			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setRowCount(0);

			for (HHB_Entry xM : entry_list) {
				Object rowData[] = { xM.getEintragid(), xM.getEart(), xM.getbetrag(), xM.getEgrund(), xM.getEdatum() };
				tableModel.addRow(rowData);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
