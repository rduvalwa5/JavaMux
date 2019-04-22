/*
 * queryAlbum.java
 *
 * Created on November 5, 2004, 11:31 AM
 */
package mux;
import java.sql.*;
// import mzax for testing class
//import MUX.*;
/**
 *TESt
 * @author  Randall Duval
 */
public class queryAlbum {
    public int ID;
    public int GEN; 
    /** Creates a new instance of queryAlbum */
    public queryAlbum(String Album_Name,Connection conn) {
    
        stringCleaner album = new stringCleaner(Album_Name);
        String Name = album.Out;
        System.out.println("Title is "+ Name);
//        String data = "jdbc:odbc:MUX";
//	String data = "jdbc:odbc:<Microsoft ODBC Driver Name> ";
          try{
              Statement queryAlbumName = conn.createStatement();
 
//SQL> select album_id from album where title like 'Drivin'' On- The ABC Years (1975-1982) Disc 1';
//ALBUM_ID
//----------
//       1   
           
           
           ResultSet AlbumNameResult = queryAlbumName.executeQuery("SELECT ALBUM_ID, GENRE_ID FROM ALBUM WHERE TITLE LIKE '" + Name + "';");
           while(AlbumNameResult.next())
            {
                ID = AlbumNameResult.getInt("ALBUM_ID");
                GEN = AlbumNameResult.getInt("GENRE_ID");
              //  System.out.println("Album_Id is " + ID);
                
            }
           
           queryAlbumName.close();
	}  catch (Exception e) { 
            ID = -1;
	   // System.out.println("Query Error"); 
           // System.out.println("Error - " + e.toString());
            
    }
    
 }
    
// For test purposes   
/*  
public static void main(String[] args)
{  
      int Id;
      //Test class
      System.out.println("Argument is " + args[0]);
      queryAlbum myQuery = new queryAlbum(args[0]);
      System.out.println("Album ID is " + myQuery.ID);
      System.out.println("Genre ID is " + myQuery.GEN);
} 
 */
 /* Out put
C:\Code\Java\src\FileObj\Music_File\CLASSES>java QueryAlbum 'TEST_Album'
Argument is 'TEST_Album'
Album ID is 29
 */

}


  
  
