/*
 * QueryArtist.java
 *
 * Created on November 5, 2004, 8:53 AM
 */
package mux;
import java.sql.*;
//import stringcleaner.*;

/**
 *
 * @author  Randall Duval
 */
public class QueryArtist {
	
    
    public int ID;
    /** Creates a new instance of QueryArtist */
    public QueryArtist(String Artist_Name,Connection conn) {
    	
        System.out.println("Start QueryArtist");
        stringCleaner Name = new stringCleaner(Artist_Name);
        String ArtistName = Name.Out;
        try{
         Statement queryArtistName = conn.createStatement();
         ResultSet ArtistNameResult = queryArtistName.executeQuery("SELECT ART_ID FROM MUSICIAN WHERE NAME LIKE '" + ArtistName + "';");
          while(ArtistNameResult.next())
            {
                ID = ArtistNameResult.getInt("ART_ID");
              //  System.out.println("Artist_Id is " + ID);
                
            }
           
           queryArtistName.close();
	}  catch (Exception e) { 
            ID = -1;
	   // System.out.println("Query Error"); 
           System.out.println("Artist Query Error- " + e.toString());
            
    }
  
   
    
// For test purposes   
   
    public static void main(String[] args)
  {
        System.out.println("\n\n***** MySQL JDBC Connection Testing *****");
		   Connection conn = null;
        try
        {
//		   Class.forName ("com.MySQL.jdbc.Driver").newInstance ();
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
            String userName = "rduval";
            String password = "blu4jazz";
           // 	      con = DriverManager.getConnection("jdbc:mysql:///JavaMusic","rduval", "blu4jazz"); 
            String url = "jdbc:mysql:///JavaMusic";        
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("\nDatabase Connection Established...");
        }
       catch (Exception ex)
        {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
        }        	
  
      int Id;
      //Test class
      System.out.println("Argument is " + args[0]);
      QueryArtist myQuery = new QueryArtist(args[0]);
      System.out.println("Artist ID is " + myQuery.ID);
   
/* Out put
C:\Code\Java\src\FileObj\Music_File\CLASSES>java QueryArtist 'TEST_Artist'
Argument is 'TEST_Artist'
Artist ID is 29
 */
  
 //   }
  
    }
}
