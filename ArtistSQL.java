/*
 * ArtistSQL.java
 *
 * Created on November 5, 2004, 12:17 PM
 */

package mux;
import java.sql.*;

/**
 *
 * @author  Randall Duval
 */
public class ArtistSQL {
    
    /** Creates a new instance of ArtistSQL */
    public ArtistSQL(String Artist_Name,Connection conn)
    {
	try{
                PreparedStatement InsertArtist = conn.prepareStatement("INSERT INTO MUSICIAN (NAME, ART_ID) VALUES (?,0)"); 
                InsertArtist.setString(1,Artist_Name);
                InsertArtist.execute();
                InsertArtist.close();
             }
           catch (Exception e) { 
           //System.out.println("Artist Insert Error");      
	   System.out.println("ArtistInsert Error- " + e.toString());
	   }
        
  
    }
    
}
