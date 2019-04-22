/*
 * SongObject.java
 *
 * Created on November 1, 2004, 2:13 PM
 */
package mux;
import java.sql.*;
//import java.io.*;
import java.lang.String;
//import java.lang.Integer;
//import StringCleaner.stringCleaner;

public class SongObject
{       
        public int art_Id;
        public int album_Id;
        public int genre_Id;
        public String Song_Path;
        public String path;
        public String name;
        public boolean AlreadyExist = false;
        public boolean insertSuccess = false;
        public boolean upDate = false;
        
    public void  Song(String Song_Name, String Song_Path,int Album_Id,int Genre_Id,int Art_Id,Connection conn)
     {
        art_Id = Art_Id;
        album_Id = Album_Id;
        genre_Id = Genre_Id;
        path = Song_Path;
        name = Song_Name;
        
        
    }      
     
    public void querySong(String Song_Name, String Song_Path,int Album_Id,int Genre_Id,int Art_Id,Connection conn)
        {
//            int DB_GEN_ID;
            int DB_ALB_ID;
            String DB_PATH;
            
            stringCleaner song = new stringCleaner(name);
            String QueryName = song.Out;
            stringCleaner songpath = new stringCleaner(path);
            String QueryPath = songpath.Out;
            System.out.println("Query Record");
          try{
           Statement querySongName = conn.createStatement();
           ResultSet SongNameResult = querySongName.executeQuery("SELECT SONG_PATH,ALBUM_ID,GENRE_ID FROM SONG WHERE SONG LIKE '" + QueryName + "' AND ALBUM_ID LIKE '" + album_Id + "';");
           
           while(SongNameResult.next())
            {
                
                DB_PATH = SongNameResult.getString("SONG_PATH");
                DB_ALB_ID = SongNameResult.getInt("ALBUM_ID");
 //               DB_GEN_ID = SongNameResult.getInt("GENRE_ID");
                System.out.println("DB_ALB_ID is  " + DB_ALB_ID + "  DB_PATH =  " + DB_PATH);
                System.out.println("album_Id is  " + album_Id + "  PATH =  " + path);
                
                int isSame = path.compareTo(DB_PATH);
                System.out.println("isSame is  " + isSame);
                
                if ((DB_ALB_ID == album_Id))
                {
                    System.out.println("Album Id is the same");
                    if(isSame != 0)
                    {
                      System.out.println("Update Record Path is NOT the same");
                      System.out.println(path);
                      System.out.println(genre_Id);
                      System.out.println(name);
                      //SQL> update song set song.song_path ='NEW/PATH', SONG.GENRE_ID = '8' where song = 'Always On My Mind.wav';
                      PreparedStatement updateSong = conn.prepareStatement("UPDATE SONG SET SONG.SONG_PATH = ?, SONG.GENRE_ID = ? WHERE SONG = ? AND ALBUM_ID = ?;");
                      updateSong.setString(1,path);
                      updateSong.setInt(2,genre_Id);
                      updateSong.setString(3,name);
                      updateSong.setInt(4,album_Id);
                      updateSong.executeUpdate();
                      AlreadyExist = true;
                      upDate = true;
                    }
                    else
                    {
                      System.out.println("Path is the same");
                      AlreadyExist = true;  
                    }
                }
                else
                {
                    AlreadyExist = false;
                }
            }
                    
           querySongName.close();
           
	}  catch (Exception e) { 
	    System.out.println("Update Error"); 
            System.out.println("Error - " + e.toString());
        }
      }
    
      public void insertSong(String Song_Name, String Song_Path,int Album_Id,int Genre_Id,int Art_Id,Connection conn)
//      void insertSong()
        {
      try{
	   PreparedStatement InsertSong = conn.prepareStatement("INSERT INTO SONG (SONG,SONG_PATH, ALBUM_ID, GENRE_ID, ART_ID) VALUES (?,?,?,?,?)"); 
                InsertSong.setString(1,name);
                InsertSong.setString(2,path);
                InsertSong.setInt(3,album_Id);
                InsertSong.setInt(4,genre_Id);
                InsertSong.setInt(5,art_Id);
                InsertSong.execute();
                InsertSong.close();
                insertSuccess = true;
             }
           catch (Exception e) { 
           System.out.println("Insert Error");      
	   System.out.println("Error - " + e.toString());
           insertSuccess = false;
	   }
                
    }
        
    //For Test
    // Argumnets <"Song Title"string> <"Song Path" string> <Album id (number)> Genre Id (number)> 
/*
      public static void main(String[] args)
  {
      int art_Id = Integer.parseInt(args[4]);
      int genre_Id = Integer.parseInt(args[3]);
      int album_Id = Integer.parseInt(args[2]);
      String song = args[0];
      String song_path = args[1];
      
      SongObject mySong = new SongObject();
      
      mySong.Song(song,song_path,album_Id,genre_Id,art_Id);
      mySong.querySong();
      System.out.println(mySong.AlreadyExist);
      if(mySong.AlreadyExist == false)
      {
          System.out.println(song + " "+ song_path + " " + album_Id + " " + genre_Id);
  //        mySong.insertSong(song,song_path,album_Id,genre_Id,art_Id);
      }
   
     }
 
*/   
     
}
    
