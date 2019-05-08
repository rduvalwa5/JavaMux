package jMusic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class IsExist {
	
	List<String> allArtistLike(String ArtistName,Connection conn) 
	{
		List<String> duplicateArtistLike = new ArrayList<String>();
        try{
         Statement queryArtistNameLike = conn.createStatement();
         ResultSet SetResult = queryArtistNameLike.executeQuery("SELECT * FROM ARTIST WHERE ARTIST LIKE '%" + ArtistName + "%';");
         if (!SetResult.next()) {
        	 duplicateArtistLike.add("Artist Like " + ArtistName + " Not Found");
               }
         else  {
       		 System.out.println("xxArtist like " + SetResult.getString("ARTIST")) ;
    		 duplicateArtistLike.add(SetResult.getString("ARTIST"));  
        	 while(SetResult.next())
        	 	{
        		 System.out.println("Artist like " + SetResult.getString("ARTIST")) ;
        		 duplicateArtistLike.add(SetResult.getString("ARTIST"));       
        	 	}       
         	}
        }
         catch (Exception e) { 
	           System.out.println("ArtistNameLike Query Error- " + e.toString());
		}
         return duplicateArtistLike;
	}
	
	List<String> allSongsLike(String SongName,Connection conn) 
	{
		List<String> duplicateSongs = new ArrayList<String>();
        try{
         Statement querySongName = conn.createStatement();
         ResultSet SetResult = querySongName.executeQuery("SELECT * FROM album2songs WHERE song LIKE '%" + SongName + "%';");
         if (!SetResult.next()) {
             duplicateSongs.add("Song " + SongName + " Not Found");
               }
         else  {
        	 duplicateSongs.add(SetResult.getString("song"));  
        	 while(SetResult.next())
        	 	{
 	                duplicateSongs.add(SetResult.getString("song"));       
        	 	}       
         	}
        }
         catch (Exception e) { 
	           System.out.println("Song Query Error- " + e.toString());
		}
         return duplicateSongs;
	}
	
	Boolean doesSongExist(String SongName,Connection conn) 
	{
		List<String> duplicateSongs = new ArrayList<String>();
		Boolean result = false;
        try{
         Statement querySongName = conn.createStatement();
         ResultSet SetResult = querySongName.executeQuery("SELECT * FROM album2songs WHERE song LIKE '%" + SongName + "%';");
         if (!SetResult.next()) {
         		result = false;
   	         }
         else  {
        	 result = true;
        	 while(SetResult.next()) {
  	                String song = SetResult.getString("song");
	                String album = SetResult.getString("album");
	                int id = SetResult.getInt("index");
	                duplicateSongs.add(song);
	                System.out.println("Logging song is " + song +"\t" + "album is " +  album + "\t" + "song Id is " + id);     }           
         }
         return result;
        }
         catch (Exception e) { 
	           System.out.println("Song Query Error- " + e.toString());
	           return result;
		}
   
	}
	
	Boolean doesArtistExist(String ArtistName,Connection conn) 
		{
			Boolean result = false;
	        try{
	         Statement queryArtistName = conn.createStatement();
	         ResultSet SetResult = queryArtistName.executeQuery("SELECT * FROM ARTIST WHERE artist LIKE '" + ArtistName + "';");
	         if (!SetResult.next()) {
	         		result = false;
	   	         }
	         else  {
	        	 result = true;
	         }
	         return result;
	        }
	         catch (Exception e) { 
		           System.out.println("Artist Query Error- " + e.toString());
		           return result;
			}
		}

	Boolean doesAlbumExist(String albumName,Connection conn) 
	{
		Boolean result = false;
        try{
         Statement queryAlbumName = conn.createStatement();
         ResultSet SetResult = queryAlbumName.executeQuery("SELECT * FROM ARTIST_ALBUMS WHERE ALBUM LIKE '" + albumName + "';");
         if (!SetResult.next()) {
         		result = false;
   	         }
         else  {
        	 result = true;
         }
         return result;
        }
         catch (Exception e) { 
	           System.out.println("Artist Query Error- " + e.toString());
	           return result;
		}
	}
	
	Boolean doesAlbumCoverExist(String albumCover,Connection conn) 
	{
		Boolean result = false;
        try{
         Statement queryCoverName = conn.createStatement();
         ResultSet SetResult= queryCoverName.executeQuery("SELECT * FROM ALBUM_COVERS WHERE ALBUM_COVER LIKE '" + albumCover + "';");
         if (!SetResult.next()) {
         		result = false;
   	         }
         else  {
        	 result = true;
         }
         return result;
        }
         catch (Exception e) { 
	           System.out.println("Artist Query Error- " + e.toString());
	           return result;
		}
	}
	
// For test purposes   	   
    public static void main(String[] args)
  {
		   Connection conn = null;
        try
        {
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
            String userName = "rduval";
            String password = "blu4jazz";
            String url = "jdbc:mysql:///Music";        
            conn = DriverManager.getConnection (url, userName, password);
        }
       catch (Exception ex)
        {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
        }        	
  
      String [] artists = {"Joan Baez","Bill Perry","Fats Waller","Mark Knopfler","Zac Brown Band","Jack Black","John"};
      String [] albumNames = {"When The Eagle Flies","American Garage","Kinks","The Ultimate Charlie Daniels Band [Disc 1]","Black Jack"};
      String [] albumCovers = {"Alabama_SouthernDrawl.jpg","AnimalOriginalHits.jpg","Zombies_OdesseyOracle.jpg","Fat_Jack.jpg"};
      String [] songs = {"Comfortably Numb","Gloria","Get Over It","Fat_Jack"};
      
      for(String art:artists) {
    	  IsExist artExist = new IsExist();
    	  System.out.println(art + "  "  + artExist.doesArtistExist(art, conn));
      	}
      
      for(String album:albumNames) {
    	  IsExist albumExist = new IsExist();
    	  System.out.println(album + "  "  + albumExist.doesAlbumExist(album, conn));
      	}
      
      for(String cover:albumCovers) {
    	  IsExist albumExist = new IsExist();
    	  System.out.println(cover + "  "  + albumExist.doesAlbumCoverExist(cover, conn));
      	}
      
      for(String song:songs) {
    	  IsExist songExist = new IsExist();
    	  System.out.println(song + "  "  + songExist.doesSongExist(song, conn)); 
      	}

      for(String SongName:songs) {
    	  IsExist songExist = new IsExist();
    	  List<String> myResult = songExist.allSongsLike(SongName, conn); 
    	  for( String s : myResult) {
    		  System.out.println("Song is " + s);
    	  }
      	}

      for(String ArtistName:artists) {
    	  IsExist artistDups = new IsExist();
    	  List<String> myResult = artistDups.allArtistLike(ArtistName, conn); 
    	  for( String s : myResult) {
    		  System.out.println("ArtistLike is " + s);
    	  }
      	}
      }
  }
