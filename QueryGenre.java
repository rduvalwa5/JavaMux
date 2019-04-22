/*
 * QueryGenre.java
 *
 * Created on November 1, 2004, 10:01 AM
 */
package mux;
import java.sql.*;
/**
 *
 * @author  Randall Duval
 */
public class QueryGenre{
    public int ID;
    /** Creates a new instance of QueryGenre */
    public QueryGenre(String Genre_Name,Connection conn) {
        System.out.println("Start QueryGenre");
          try{
           Statement queryGenreName = conn.createStatement();
           ResultSet GenreNameResult = queryGenreName.executeQuery("SELECT GENRE_ID FROM GENRE WHERE GENRE LIKE '" + Genre_Name + "';");
           while(GenreNameResult.next())
            {
                ID = GenreNameResult.getInt("GENRE_ID");
            }
           
           queryGenreName.close();
	}  catch (Exception e) { 
            ID = -1;
	    System.out.println("Query Error"); 
            System.out.println("Error - " + e.toString());
            
    }
  
  } 
    
// For test purposes   
  /*  
    public static void main(String[] args)
  {
      int Id;
      //Test class
      System.out.println("Argument is " + args[0]);
      QueryGenre myQuery = new QueryGenre(args[0]);
      System.out.println("Genre ID is " + myQuery.ID);
    */
/* Out put
C:\Code\Java\src\FileObj\Music_File\CLASSES>java QueryGenre 'TEST_GENRE'
Argument is 'TEST_GENRE'
Genre ID is 29
 */
  
 //   }
  
}
