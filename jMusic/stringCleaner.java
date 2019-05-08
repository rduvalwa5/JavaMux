/*
 * stringCleaner.java
 *This program cleans up string characters that may be encountered by an SQL
 *query statement insert or update statements do not have this problem
 * Created on November 8, 2004, 11:22 AM
 */
package jMusic;
//import java.io.*;
import java.lang.String;

//package stringCleaner;

/**
 *
 * @author  Randall Duval
 */
public class stringCleaner {
    public String Out;
    int Len;
    /** Creates a new instance of stringCleaner */
    public stringCleaner(String In) {
        
        int len = In.length();
        Len = len;
        //System.out.println("Length is " + len);
        StringBuffer in = new StringBuffer(In);
        
        char a;
        //System.out.println(in);
        for(int i = 0; i < len ; i++)
        {
            a = in.charAt(i);
            //if((a == '\'')||(a == '"') || (a == '`'))
            if(a == '\'')
            {
              //System.out.println("Char is " + a);  
              //in.deleteCharAt(i);
              //in.replace(i,i," ");
              in.insert(i,'\'');
              i++;
            }
            
        }
        Out = in.toString();
        //System.out.println(in);
        
        //System.out.println("Out is " + Out);
        
    }
//For test
   
    public static void main(String[] args)
    { 
      stringCleaner A = new stringCleaner(args[0]);
      System.out.println(" String len is " + A.Len); 
      System.out.println("Argument is " + args[0]);
      System.out.println("Cleaned sting is " + A.Out);
    }

}
