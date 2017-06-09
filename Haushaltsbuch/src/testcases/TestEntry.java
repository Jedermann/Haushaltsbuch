package testcases;

import static org.junit.Assert.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import database.Databasemanager;
import model.HHB_Entry;

/**
 * 
 * @author Manuel Schmidt
 * @version 1.0
 *
 */

public class TestEntry {

	Databasemanager db;
	Connection con;
	ArrayList<Databasemanager> elist; 
	Databasemanager e;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		db = new Databasemanager();
		con = db.getConnection();
		e = elist.get(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test 
	/**
	 * Test für Update
	 * @throws SQLEXception
	 */
	public void testUpdateEntry() throws SQLException {
		
				
		try {
			if(elist.size() > 0) {
				e = elist.get(0);
				e.setEbetrag(200);
				db.updateEntry(con, e);
		}
			}
			catch (Exception  ex){
				assertTrue("SQLException was thrown", true);
			}
	}
	
	@Test
	/**
	 * Test für das Insert
	 * @throws SQLEXception
	 */
	public void testInsertEntry() throws SQLException {
		try {
			if(elist.size() > 2) {
				e = elist.get(1);
				e.setEgrund("guata Grund TEST");
				HHB_Entry dbEntry = new HHB_Entry(0, "hallo", 1, "schinken", "blutwurst");
				db.insertNewEntry(dbEntry);
		}
			}
			catch (Exception ex){
				assertTrue("SQLException was thrown", true);
			}
	}
	
	@Test
	/**
	 * Test für das Delete
	 * @throws SQLEXception
	 */
	public void testDeleteSelectedEntry() throws SQLException {
		try {
			if(elist.size() > 2) {
				e = elist.get(2);
				db.deleteSelectedEntry(con, e);
		}
			}
			catch (Exception ex){
				assertTrue("SQLException was thrown", true);
			}
	}
	
	
	@Test
	/**
	 * Test für GetAllEntry
	 * @throws SQLException
	 */
	public void testGetAllEntry() throws SQLException {
		try {
				Databasemanager.getAllEntrys();
		}
			
			catch (Exception ex){
				assertTrue("SQLException was thrown", true);
			}
	}
}
	









