/*
 * AlbumSQL.java
 *
 * Created on November 5, 2004, 11:51 AM
 */

package mux;
import java.sql.*;

/**
 *
 * @author  Randall Duval
 *Created to submit found albums into data base committed to CVS
 */
public class AlbumSQL {
     public int ID;
    /** Creates a new instance of AlbumSQL */
    public AlbumSQL(String Album_Name, int Genre_Id, int Art_Id, Connection conn) {
    	try{
           System.out.println("Input is " + Album_Name + ", " +  Genre_Id + ", " + Art_Id);
           PreparedStatement InsertAlbum = conn.prepareStatement("INSERT INTO ALBUM (TITLE, ALBUM_ID, GENRE_ID, ART_ID) VALUES (?,0,?,?)"); 
                InsertAlbum.setString(1,Album_Name);
                InsertAlbum.setInt(2,Genre_Id);
                InsertAlbum.setInt(3,Art_Id);
                InsertAlbum.execute();
                InsertAlbum.close();
             }
           catch (Exception e) { 
           System.out.println("Album Insert Error");      
	   System.out.println("Error - " + e.toString());
	   }
        
    
  
  }
// Test entry
/*        
     public static void main(String[] args)
  {
      
      //int album_Id;
      //int genre_Id;
      int genre_Id = Integer.parseInt(args[1]);
      int art_Id = Integer.parseInt(args[2]);
      String Title = args[0];
           
      AlbumSQL myAlbum = new AlbumSQL(Title,genre_Id, art_Id);
      System.out.println("Album Id is " + myAlbum.ID);
      //myAlbum(Title,genre_Id,art_Id);
      
     }
*/
 }
