package jMusic;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	public class JdbcSelectTest {   // Save as "JdbcSelectTest.java"

	public static void main(String args[]) throws Exception
	  {
	    Connection con = null;
	    try {
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      con = DriverManager.getConnection("jdbc:mysql:///JavaMusic",
	        "rduval", "blu4jazz");
	      if(!con.isClosed())
	        System.out.println("Successfully connected to " +
	          "MySQL server using TCP/IP...");
	    } catch(Exception e) {
	      System.err.println("Exception: " + e.getMessage());
	    } finally {
	      try {
	        if(con != null)
	          con.close();
	      } catch(SQLException e) {}
	    }
	  }
	}