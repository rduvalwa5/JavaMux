/*
 * QueryArtist.java
 *
 * Created on November 5, 2004, 8:53 AM
 */
package jMusic;
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
    	
//        System.out.println("Start QueryArtist");
        stringCleaner Name = new stringCleaner(Artist_Name);
        String ArtistName = Name.Out;
        try{
         Statement queryArtistName = conn.createStatement();
         ResultSet ArtistNameResult = queryArtistName.executeQuery("SELECT * FROM ARTIST WHERE artist LIKE '" + ArtistName + "';");
 //        System.out.println(ArtistNameResult.toString());
         while(ArtistNameResult.next())
            {
                String gen = ArtistNameResult.getString("genre");
                String art = ArtistNameResult.getString("artist");
                int id = ArtistNameResult.getInt("index");
                System.out.println("Artist is " + art +"\t" + "Genre is " +  gen + "\t" + "Artist Id is " + id);
                
            }
           
           queryArtistName.close();
	}  catch (Exception e) { 
            ID = -1;
	   // System.out.println("Query Error"); 
           System.out.println("Artist Query Error- " + e.toString());
            
    }
    }
   
    
// For test purposes   
   
    public static void main(String[] args)
  {
//        System.out.println("\n\n***** MySQL JDBC Connection Testing *****");
		   Connection conn = null;
        try
        {
//		   Class.forName ("com.MySQL.jdbc.Driver").newInstance ();
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
            String userName = "rduval";
            String password = "blu4jazz";
           // 	      con = DriverManager.getConnection("jdbc:mysql:///JavaMusic","rduval", "blu4jazz"); 
            String url = "jdbc:mysql:///Music";        
            conn = DriverManager.getConnection (url, userName, password);
//            System.out.println ("\nDatabase Connection Established...");
        }
       catch (Exception ex)
        {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
        }        	
  
//      int Id  = 1;
      String [] artists = {"Joan Baez","Bill Perry","Fats Waller","Mark Knopfler","Zac Brown Band"};
      //Test class
      
      for(String art:artists) {
//    	  System.out.println("Artist is " + art);
    	  QueryArtist myQuery = new QueryArtist(art, conn);

      }
  
   
/* Out put
C:\Code\Java\src\FileObj\Music_File\CLASSES>java QueryArtist 'TEST_Artist'
Argument is 'TEST_Artist'
Artist ID is 29

23	Bill Perry	Rock
25	Fats Waller	Jazz
26	Mark Knopfler	Rock
27	Zac Brown Band	Country
 */
  
 //   }
  
    }
}
