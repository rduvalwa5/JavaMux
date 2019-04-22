/*
 * updateAlbum.java
 *
 * Created on November 13, 2004, 8:22 PM
 */

package mux;
import java.sql.*;
//import java.io.*;
//import java.lang.String;
//import java.lang.Integer;


/**
 *
 * @author  Randall Duval
 *Code provides update ability for MUX data base
 */
public class updateAlbum {
    public boolean upDate = false;
    
    /** Creates a new instance of updateAlbum */
    public updateAlbum( int Album_Id, int Genre_Id, Connection conn) 
    {
          int tries;
          System.out.println("Update Album");
          try{
               PreparedStatement updateAlbum = conn.prepareStatement("UPDATE ALBUM SET ALBUM.GENRE_ID = ? WHERE ALBUM_ID = ?;");
               updateAlbum.setInt(1,Genre_Id);
               updateAlbum.setInt(2,Album_Id);
               updateAlbum.executeUpdate();
               upDate = true;
    }  catch (Exception e) { 
	    System.out.println("Album Update Error"); 
            System.out.println("Error - " + e.toString());
            //upDate = false;
  }
}
}


