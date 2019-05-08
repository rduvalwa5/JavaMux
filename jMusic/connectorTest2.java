/*
 * https://www.w3resource.com/mysql/mysql-java-connection.php
 */
package jMusic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connectorTest2 
   {
       public static void main (String[] args)
       {
           System.out.println("\n\n***** MySQL JDBC Connection Testing *****");
		   Connection conn = null;
           try
           {
//		   Class.forName ("com.MySQL.jdbc.Driver").newInstance ();
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
               String userName = "rduval";
               String password = "blu4jazz";
              // 	      con = DriverManager.getConnection("jdbc:mysql:///JavaMusic","rduval", "blu4jazz"); 
               String url = "jdbc:mysql:///JavaMusic";        
               conn = DriverManager.getConnection (url, userName, password);
               System.out.println ("\nDatabase Connection Established...");
           }
          catch (Exception ex)
           {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
           }      
		   
		   finally
           {
               if (conn != null)
               {
                   try
                   {
                       System.out.println("\n***** Let terminate the Connection *****");
					   conn.close ();					   
                       System.out.println ("\nDatabase connection terminated...");
                   }
                   catch (Exception ex)
				   {
				   System.out.println ("Error in connection termination!");
				   }
               }
           }
       }
   }