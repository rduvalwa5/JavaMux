/*
 * GenreSQL.java
 *
 * Created on October 31, 2004, 11:18 PM
 */
//package MusicFile;
package mux;
import java.sql.*;
/**
 *
 * @author  Randall Duval
 */
public class GenreSQL {
    /** Creates a new instance of GenreSQL */
    public GenreSQL(String Genre_Name,Connection conn) {
     	try{
//mySQL sequence increment method is to insert 0 for all and sequence auto increments
	      PreparedStatement InsertGenre = conn.prepareStatement("INSERT INTO GENRE(GENRE, GENRE_ID) VALUES (?,0)"); 
                InsertGenre.setString(1,Genre_Name);
                InsertGenre.execute();
                InsertGenre.close();
             }
           catch (Exception e) { 
           System.out.println("Insert Error");      
	   System.out.println("Error - " + e.toString());
	   }
        
  
  }
}

// force recompile    

