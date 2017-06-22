package model;

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.*;
import com.mysql.jdbc.*;

public class Datenbank {
	
	private static final String className = "com.mysql.jdbc.Driver";
	
	/**
	 * Returns the highest ID found in the table
	 * @return highestID
	 */
	public static int getHighestID(){
		
		int highestID = 0;
		
		try{
			
			
			Class.forName(className);

	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch","root", "");
		    PreparedStatement pstm;
		    
		    String sql = "SELECT eintragid FROM Eintrag";
		    pstm = con.prepareStatement(sql);
		    
		    ResultSet rst;
		    rst = pstm.executeQuery();
		    
		    
		    while (rst.next()) {
		        int currentID = rst.getInt(1);
		        if(currentID > highestID){
		        	highestID = currentID;
		        }
		    }
		    
		    con.close();
		    
		    return highestID;
		    
		}
		catch(Exception ex){
			ex.printStackTrace();
			return -1;
		}
		finally {
			System.out.println("Highest ID found is: "+highestID);
		}
	}
	
	/**
	 * Returns an Arraylist with all DBEntrys found in the Database
	 * @return buchungen
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<DBEntry> getAllEntrys() throws ClassNotFoundException, SQLException {
		
		ArrayList<DBEntry> buchungen = new ArrayList<>();
		
		try{
			Class.forName(className);


	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch", "root", "");
		    PreparedStatement pstm;
		    
		    String sql = "SELECT * FROM Eintrag";
		    pstm = con.prepareStatement(sql);
		    
		    ResultSet rst;
		    rst = pstm.executeQuery();
		    
		    
		    while (rst.next()) {
		        DBEntry eintrag = new DBEntry(rst.getInt("eintragid"), rst.getString("art"), rst.getFloat("betrag"), rst.getString("grund"), rst.getString("datum"));
		        
		        Boolean addPermission = true;
		        for (DBEntry dbEntry : buchungen) {
					if(eintrag.equals(dbEntry)){
						addPermission=false;
					}
				}
		        
		        if(addPermission){
		        	buchungen.add(eintrag);
		        }
		        
		    }
		    
		    con.close();
		}
		finally {
			System.out.println("Finished the Database call!");
			for (DBEntry dbEntry : buchungen) {
				System.out.println(dbEntry.toString());
			}
		}		
	    return buchungen;
	}
	
	/**
	 * Inserts all the DBEntrys from an arraylist to the selected Database
	 * @param contacts
	 * @return true
	 * @throws Exception
	 */
	public static boolean commitAllEntrys(ArrayList<DBEntry> buchungen) throws Exception{
				
		try{
			Class.forName(className);


	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch","root", "");
		    PreparedStatement resetStatement;
		    
		    String sql = "DELETE FROM Eintrag;";
		    resetStatement = con.prepareStatement(sql);
		    resetStatement.execute();   
		    System.out.println("Deleted and Resetted the whole table!");
		    
		    
		    for (DBEntry dbEntry : buchungen) {
		    	sql = "INSERT INTO Eintrag (eintragid, art, betrag, grund, datum) VALUES ("+dbEntry.ID+", '"+dbEntry.Art+"', '"+dbEntry.Betrag+"', '"+dbEntry.Grund+"', '"+dbEntry.Datum+"');";	    
			    System.out.println(sql);
		    	PreparedStatement insertStatement = con.prepareStatement(sql);
			    insertStatement.execute();
			}
		    
		    con.close();
		    return true;
		}
		finally {
			System.out.println("Table rebuild! --TIMESTAMP"+System.currentTimeMillis());
		}
	}
	
	/**
	 * Inserts a new entry into the Database
	 * @param dbe
	 * @return
	 */
	public static boolean insertNewEntry(DBEntry dbe){
		try{
			Class.forName(className);


	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch","root", "");

		    	String sql = "INSERT INTO Eintrag (eintragid, art, betrag, grund, datum) VALUES ("+dbe.ID+", '"+dbe.Art+"', '"+dbe.Betrag+"', '"+dbe.Grund+"', '"+dbe.Datum+"');";  
			    System.out.println(sql);
		    	PreparedStatement insertStatement = con.prepareStatement(sql);
			    insertStatement.execute();
		    
		    con.close();
		    return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		finally {
			System.out.println("INSERTED NEW Entry --TIMESTAMP"+System.currentTimeMillis());
		}
	}
	
	/**
	 * Deletes a selected entry from the Database
	 * @param dbe
	 * @return true
	 * @return false
	 */
	public static boolean deleteSelectedEntry(DBEntry dbe){
		try{
			Class.forName(className);


	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch","root", "");

		    	String sql = "DELETE FROM Eintrag WHERE eintragid='"+dbe.ID+"' AND art='"+dbe.Art+"';";	    
			    System.out.println(sql);
		    	PreparedStatement insertStatement = con.prepareStatement(sql);
			    insertStatement.execute();
			    System.out.println("Selected Row has been deleted! --Commit!");
		    con.close();
		    return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		finally {
			System.out.println("DELETED Eintrag --TIMESTAMP"+System.currentTimeMillis()+"\t"+dbe.toString());
		}
	}
	
}

