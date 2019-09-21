package edu.odu.cs.cs350.namex.main.java;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	     
		
	public static void main(String[] args) throws Exception {
		 
		
		/**
		 * scans in the command line input file
		 */
		InputStream inputfile = System.in;
		if(inputfile.available() > 0)
		{
		  Scanner inputFileScanner = new Scanner(inputfile).useDelimiter("\\s*\n\\s*");
		
		
		  String inputFile = inputFileScanner.next();
		 		
		  while (inputFileScanner.hasNext())
			    {
		    	  inputFile = inputFile + inputFileScanner.next()  + '\n'; 		    	  
			    }
		 /**
		  * creates inputBlock.txt file which is used by the PNE to classify Instances
		  * and mark-up the output file
		  */		      
	      String outputfile = "inputBlock.txt";		
	      FileWriter outputWriter = new FileWriter(outputfile);		 
	      BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);		
	      //bufferedWriter.write(inputFile);		 
	      bufferedWriter.close(); 
	     
	      PNE myPNE = new PNE();
	     
	     /**
	      * reads the markedOutput.txt file that was created by PNE, and outputs it
	      * to the command line output file
	      */
	      BufferedReader reader = new BufferedReader( new FileReader ("markedOutput.txt"));
	      String line = null;
	      String outputFlieLine = null;
	      StringBuilder stringBuilder = new StringBuilder();
	      String ls = System.getProperty("line.separator");

          while( ( line = reader.readLine() ) != null ) 
              {
	             stringBuilder.append( line );
	             stringBuilder.append( ls );
	          }

          outputFlieLine = stringBuilder.toString();
	      reader.close();       
	      System.out.println(outputFlieLine);
	      PrintStream original = System.out;
		  System.setOut(original);
		  System.out.println("test");
	   }
	}
	
	

}