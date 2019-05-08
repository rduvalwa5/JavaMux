package jMusic;
import java.io.*;
import java.lang.String;
import java.sql.*;

public class dbConnect {
	
	  public static Connection conn;
	  public static File myMusic;
	  public static String MUSICDB;
	  public static String USER;
	  public static String PASSWD;
	  
	public static void main(String[] args)
		{	
		  String MUSICDB = "jdbc:mysql:///JavaMusic";
		  String USER = "rduval";
		  String PASSWD = "blu4jazz";
		    
		 if (args.length == 0)
		 {		  
		  System.out.println ("DB is " + MUSICDB + " User is " + USER + " PASSWD is " + PASSWD);
		      
		  String data = ("odbc:" + MUSICDB);
		   try{
			   Class.forName("com.mysql.jdbc.Driver").newInstance();
			      conn = DriverManager.getConnection(MUSICDB,"rduval", "blu4jazz");
		  //        conn = DriverManager.getConnection(MUSICDB,USER,PASSWD);
			      System.out.println("Connect to " +  MUSICDB  + "by " + USER);
		      }  catch (Exception e)
		     {  
		      // System.out.println("Query Error"); 
		       System.out.println("Error - " + e.toString());
		     }
		 
	   finally
       {
           if (conn != null)
           {
               try
               {
                   System.out.println("\n***** Let terminate the Connection *****");
				   conn.close ();					   
                   System.out.println ("\nDatabase connection to " + MUSICDB +" terminated...");
               }
               catch (Exception ex)
			   {
			   System.out.println ("Error in connection termination!");
			   }
           }
       }
}}}
