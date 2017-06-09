package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.HHB_Entry;

public class Databasemanager {

	public static int getHighestIDUser(){
		
		int highestID = 0;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");

	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch", "root", "");
			PreparedStatement pstm;
		    
		    String sql = "SELECT Eintragid FROM eintrag";
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
			System.out.println("Highest ID (in table eintrag) found is: "+highestID);
		}
	}
	
	public static ArrayList<HHB_Entry> getAllEntrys() throws ClassNotFoundException, SQLException {
		
		ArrayList<HHB_Entry> entrys = new ArrayList<>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch", "root", "");
		    PreparedStatement pstm;
		    
		    String sql = "SELECT * FROM eintrag";
		    pstm = con.prepareStatement(sql);
		    
		    ResultSet rst;
		    rst = pstm.executeQuery();
		    
		    
		    while (rst.next()) {
		        HHB_Entry dbl = new HHB_Entry(  rst.getInt("Eintragid"), rst.getString("Art"), rst.getDouble("Betrag"), rst.getString("Grund"), rst.getString("Datum") );
		        entrys.add(dbl);
		    }
		    
		    con.close();
		}
		finally {
			for (HHB_Entry dbEntry : entrys) {
				System.out.println(dbEntry.toString());
			}
		}		
	    return entrys;
	}
	
	public static boolean commitAllEntrys(ArrayList<HHB_Entry> entrys) throws Exception{
		
		try{

			Class.forName("com.mysql.jdbc.Driver");

	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch", "root", "");
		    PreparedStatement resetStatement;
		    
		    String sql = "DELETE FROM eintrag;";
		    resetStatement = con.prepareStatement(sql);
		    resetStatement.execute();   
		    System.out.println("Deleted and Resetted the whole table!");
		    
		    
		    for (HHB_Entry dbEntry : entrys) {
		    	sql = "INSERT INTO eintrag (Eintragid, Art, Betrag, Grund, Datum) VALUES ("+dbEntry.getEintragid()+", '"+dbEntry.getEart()+"', '"+dbEntry.getbetrag()+"', '"+dbEntry.getEgrund()+"', '"+dbEntry.getEdatum()+"');";	    
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

	public boolean insertNewEntry(HHB_Entry dbEntry){
		try{

			Class.forName("com.mysql.jdbc.Driver");

	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch", "root", "");
		    	String sql = "INSERT INTO eintrag (Eintragid, Art, Betrag, Grund, Datum) VALUES ("+dbEntry.getEintragid()+", '"+dbEntry.getEart()+"', '"+dbEntry.getbetrag()+"', '"+dbEntry.getEgrund()+"', '"+dbEntry.getEdatum()+"');";	     
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
			System.out.println("Neuer Eintrag wurde übertragen");
		}
	}

	public static boolean deleteSelectedEntry(HHB_Entry dbe){
		try{
			Class.forName("com.mysql.jdbc.Driver");

	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/haushaltsbuch", "root", "");

		    	String sql = "DELETE FROM eintrag WHERE Art='"+dbe.getEart()+"' AND Betrag='"+dbe.getbetrag()+"' AND Grund='"+dbe.getEgrund()+"';";	    
			    System.out.println(sql);
		    	PreparedStatement insertStatement = con.prepareStatement(sql);
			    insertStatement.execute();
			    System.out.println("Markiertes wurde gelöscht");
		    con.close();
		    return true;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		finally {
			System.out.println("DELETED Entry --TIMESTAMP"+System.currentTimeMillis()+"\t"+dbe.toString());
		}
	}

	public void setEbetrag(double d) {
		// TODO Auto-generated method stub
		
	}

	public void updateEntry(Connection con, Databasemanager e) {
		// TODO Auto-generated method stub
		
	}

	public void deleteSelectedEntry(Connection con, Databasemanager e) {
		// TODO Auto-generated method stub
		
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEgrund(String string) {
		// TODO Auto-generated method stub
		
	}	
	
}
