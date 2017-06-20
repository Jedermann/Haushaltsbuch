package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.DBEntry;
import model.Datenbank;

public class Datenbanktest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}	
	
	@Test
	public void testGetHighestID(){
		try{
			Datenbank.getHighestID();
			assertTrue("Received highest ID",true);
		}
		catch(Exception ex){
			fail("Error while handling Highest ID");
		}
	}
	
	@Test
	public void testGetEntryList() {
		try{
			Datenbank.getAllEntrys();
			assertTrue("Getting the entry List finished!",true);
		}
		catch(Exception ex){
			fail("Exception thrown while trying to request the database entrys");
		}
		
	}
	
	@Test
	public void testInserAndRemove(){
		try{
			DBEntry xentry = new DBEntry(Datenbank.getHighestID()+1, "TEST", 9999, "TEST ROUTINE", "1999-01-01");
			Datenbank.insertNewEntry(xentry);
			
			Datenbank.deleteSelectedEntry(xentry);
			assertTrue("Database Test Entry created and removed!",true);			
		}
		catch(Exception ex){
			fail("Insertion or Deletion did not work correctly!");
		}
	}
	
	@Test
	public void testSelectAndCommit(){
		try{
			ArrayList<DBEntry> entrys = Datenbank.getAllEntrys();
			Datenbank.commitAllEntrys(entrys);
			assertTrue("Database receive and commit finished!",true);
		}catch(Exception ex){
			fail("Error while handling data from the database!");
		}
	}

}
