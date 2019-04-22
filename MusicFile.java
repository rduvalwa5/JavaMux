/*
 * Main.java
 *
 * Created on June 18, 2005, 9:39 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package mux;
import java.io.*;
import java.lang.String;
import java.sql.*;
//import mux.*;

/**
 *
 * @author RDuval
 */
public class MusicFile
{
  
  public static Connection conn;
  public static File myMusic;
  public static String MUSICDB;
  public static String USER;
  public static String PASSWD;

    /** Creates a new instance of Main */
    public MusicFile() {
    }
    
    /**
     * @param args the command line arguments
     */
public static void main(String[] args)
{
  File myMusic = new File(args[0]);
  String MUSICDB = args[1];
  String USER = args[2];
  String PASSWD = args[3];
  int Trys = 0;
    
 if (args.length == 4)
 {
  //System.out.println(args[0]);
  
  
  System.out.println ("DB is " + MUSICDB + "User is " + USER + "PASSWD is " + PASSWD);
      
  String data = ("jdbc:odbc:" + MUSICDB);
   try{
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       conn = DriverManager.getConnection(data,USER,PASSWD);
      }  catch (Exception e)
     {  
      // System.out.println("Query Error"); 
       System.out.println("Error - " + e.toString());
     }
        
//Create object that is a Directory
      
 System.out.println(myMusic + (myMusic.isDirectory()?" is ": "is not ") + "a direcotry");

  //Create File object
        
File[] Artist = myMusic.listFiles();
if(Artist != null)
{  //System.out.println("Reached contents not null");
           
   // this section takes the path and identifies directiries as genre types
for (int i = 0; i < Artist.length; i++)
{ //System.out.println("For loop 1 of contents not null");
      //String A = toString(contents[i].isDirectory());
	 
 if (Artist[i].isDirectory() == true)
 {
    String Genre_Path = Artist[i].getPath();
    String Artist_Name = Artist[i].getName();
    System.out.println("Artist is " + Artist_Name);
 //start Artist information 
    QueryArtist myArtist = new QueryArtist(Artist_Name,conn );
    int Artist_Id = myArtist.ID;
    int Artist_trys = 0;
    System.out.println("FIRST Artist_Id is " + Artist_Id);
    if(Artist_Id > 0)
     {    
      System.out.println("Artist_Id is found and is " + Artist_Id);
      }
     else
     {
       System.out.println("Artist_Id is not found and is " + Artist_Id);
       while ((Artist_Id < 1)&& (Artist_trys < 1))
       {
         System.out.println("Genre_Name is" + Genre_Name + "and ID is " + Genre_Id);
         GenreSQL insertGen = new GenreSQL(Genre_Name,conn);
         QueryGenre nextAttempt = new QueryGenre(Genre_Name,conn);
         Genre_Id = nextAttempt.ID;
         Genre_trys ++;
         System.out.println("Tries = "+ Genre_trys);
        }
       System.out.println("Genre_Id is    NOW   " + Genre_Id);
      }
    System.out.println("Start Artist_Name");
 // start Artist     
    File Artist = new File(Genre_Path);
    File[] Artist_Name = Artist.listFiles();
    
    if(Artist_Name != null)
    {
     //System.out.println("Starting next directory");
     for (int j = 0; j < Artist_Name.length; j++)
      {
        if ((Artist_Name[j].isHidden() == false)&& (Artist_Name[j].isDirectory() == true))
        {  
         int Art_trys = 0; 
         String Art_name = Artist_Name[j].getName();
         String Art_Path = Artist_Name[j].getPath();
         System.out.println("Artist is " + Art_name);
//Start Musicain information
//QueryArtist
         QueryArtist thisArtist = new QueryArtist(Art_name,conn);
         int Art_Id = thisArtist.ID;
         if(Art_Id > 0)
         {    
           System.out.println("Art_Id is found and is " + Art_Id);
          }
         else
         {
          System.out.println("Art_Id is not found and is " + Art_Id);
          while((Art_Id < 1)&&(Art_trys < 1))
          {
           System.out.println("Artist_Name is" + Art_name + "and ID is " + Art_Id);
//Insert Artist    
           ArtistSQL insertArtist = new ArtistSQL(Art_name,conn);
           QueryArtist nextAttempt = new QueryArtist(Art_name,conn);
           Art_Id = nextAttempt.ID;
           Art_trys ++;
           System.out.println("Tries = "+ Art_trys);
          }
           System.out.println("Art_Id is    NOW   " + Art_Id);
          }
 // Finish insert Musician information
     File Album = new File(Art_Path);
     File[] Album_Name = Album.listFiles();
     if(Album_Name != null)
       {
        for (int k = 0; k < Album_Name.length; k++)
        {
         if ((Album_Name[k].isHidden() == false)&& (Album_Name[k].isDirectory() == true))
         {   
            String Alb_name = Album_Name[k].getName();
            String Alb_Path = Album_Name[k].getPath();
            System.out.println("Album is " + Alb_name);
            System.out.println("Album Path is " + Alb_Path);
                                 
 // Start insert Album information                                   
            queryAlbum thisAlbum = new queryAlbum(Alb_name,conn);
            int Album_Id = thisAlbum.ID;
            int Album_trys = 0;
            if(Album_Id > 0)
            {    
             System.out.println("Album_Id is found and is " + Album_Id);
             if (thisAlbum.GEN != Genre_Id)
             {
              System.out.println("DB Album Genre is " + thisAlbum.GEN + "Genre is "+ Genre_Id);
              while (Album_trys < 3)
              {
               updateAlbum theAlbum = new updateAlbum(Album_Id, Genre_Id, conn);
               if(theAlbum.upDate == true)
               {
                Album_trys = 3;
               }
               else 
               {
                Album_trys ++;
                }
              }
             }
            }
         else
         {
           System.out.println("Album_Id is not found and is " + Album_Id);
           while ((Album_Id < 1))
            {
            System.out.println("Album_Name is" + Alb_name + "and ID is " + Album_Id);
            AlbumSQL insertGen = new AlbumSQL(Alb_name,Genre_Id,Art_Id, conn);
            queryAlbum nextAttempt = new queryAlbum(Alb_name, conn);
            Album_Id = nextAttempt.ID;
            Album_trys ++;
            System.out.println("Tries = "+ Album_trys);
            }
           if(Album_Id < 0)
            {
             Album_Id = 99;
             }
          }
         System.out.println("Album_Id is    NOW   " + Album_Id);                                
                                    
  //  Finsish insert Album information                                  
         File Song = new File(Alb_Path);
         File[] Song_Names = Song.listFiles();
         if(Song_Names != null)
         {
          for (int l = 0; l < Song_Names.length; l++)
          {
           if ((Song_Names[l].isHidden() == false)&& (Song_Names[l].isDirectory() == false))
           {   
             String Song_Name = Song_Names[l].getName();
             String Song_Path = Song_Names[l].getPath();
             System.out.println(Song_Name);
 //Start Insert Song                                                   
             SongObject thisSong = new SongObject();
             thisSong.Song(Song_Name,Song_Path,Album_Id,Genre_Id, Art_Id,conn);
             thisSong.querySong(Song_Name,Song_Path,Album_Id,Genre_Id, Art_Id,conn);
             int Song_trys = 0;
             if(thisSong.AlreadyExist == true)
             {    
               System.out.println("Song " + Song_Name + "already exist");
               if(thisSong.upDate == true)
               {
                System.out.println("Song " + Song_Name + "was updated");  
                }
             }
             else
             {
              System.out.println(Song_Name + " is not found");
               while ((thisSong.insertSuccess == false) && (Song_trys < 3))
               {
                thisSong.insertSong(Song_Name,Song_Path,Album_Id,Genre_Id, Art_Id,conn);
                Song_trys ++;
                System.out.println("Tries = "+ Song_trys);
                }
               if ((Song_trys == 3) && (thisSong.insertSuccess == false))
               {    
                System.out.println("SONG " + Song_Name + " IS NOT INSERTED");
                if (thisSong.insertSuccess == true)
                {
                 System.out.println("SONG " + Song_Name + " IS INSERTED");
                 }
                }
               }
           }
          }
         } //Song_Names != null
        }
       }//for Album_Name.length
      }//Album_Name != null
     }//Artist_Name[j].isHidden
    } //for Artist_Name.length
   }//if(Artist_Name
  } //if Genre[i].isDirectory
//------- everything happens before this ----------
  else 
   {
     if (Genre[i].isHidden() == false)
     {    
      System.out.println("Reached esle statement");
      System.out.println(Genre[i] + "  " +(Genre[i].isDirectory() ? "directory" : "file"));
     }
    }
   }//for (int i = 0; i < Genre.length; i++)
 }//if(Genre != null)
}//if (args.length == 4)
if (args.length < 4)
   { System.out.println("Syntax is musicFile <directory path> <Database> <DB UUID> <DB Passwd>");
    }
   return;
  }//void main
}//class MusicFile
 
