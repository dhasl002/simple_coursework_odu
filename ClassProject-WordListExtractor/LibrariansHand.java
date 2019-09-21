
/**
 * A LibrariansHand receives index values, and uses these values to mark-up a text file 
 * annotating the beginning and end of an authors name.
 * 
 *
 */
package edu.odu.cs.cs350.namex.main.java;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


// TODO: Auto-generated Javadoc
/**
 * The Class LibrariansHand.
 */
public class LibrariansHand {
	
	/** The classification sequenced. */
	String classificationSequenced;

/**
 * The LibrariansHand class constructor .
 *
 * @param inputFileBlock_txt the input file block_txt
 * @param classificationSeq the classification seq
 * @throws IOException Signals that an I/O exception has occurred.
 */	
public LibrariansHand (String inputFileBlock_txt, String classificationSeq) throws IOException 
	{ 
	   inputFileBlock = inputFileBlock_txt;	   
	   classificationSequenced = classificationSeq;
	   markOutputBlock(inputFileBlock);
    }

/**
 * markOutputBlock(String inputFileBlock) marks the locations within inputFileBlock file
 * where an authors name exists, then save the marked text to an output file called
 * markedOutput.txt
 *
 * @param inputFileBlock the input file block
 * @throws IOException Signals that an I/O exception has occurred.
 */
private	void markOutputBlock(String inputFileBlock) throws IOException
	{
	 Vector<String> fileTokens = new Vector<String>();
	 Vector<Integer> endOfLineIndexes = new Vector<Integer>();
	 Vector<Character> classificationValues = new Vector<Character>();
	 //String endOfLineIndexes = null;
	 Scanner inputFileScanner;
	 String line = null;
	 int i = 0;
	 int marker = 0;
	 
	 /**
	  * collect classification values in a vector
	  */
	 for(int k = 0; k < classificationSequenced.length(); ++ k )
	 {
		 classificationValues.add(k, classificationSequenced.charAt(k));
	 }
	 
	 //System.out.format(classificationValues.toString());
	 
	 FileReader inputFileBlockReader = new FileReader(inputFileBlock);
     BufferedReader bufferedReader = new BufferedReader(inputFileBlockReader);
     
     /**
      * loads each token of the input file into a vector call
      * fileTokens
      */
     line = bufferedReader.readLine();
     while(line != null)
       {
    	  int numWordsInLine = 0; 
    	 inputFileScanner = new Scanner(line);    	 
    	 
    	while (inputFileScanner.hasNext() )
    	{
    	 fileTokens.add( i, inputFileScanner.next() ); 
    	 ++i; 
    	 ++numWordsInLine;
    	 
    	}
    	
    	/**
    	 * collect the end of line markers in a vector
    	 */
    	 endOfLineIndexes.add(numWordsInLine);
    	 
    	
    	 line = bufferedReader.readLine();
       }            
      
      //System.out.format(endOfLineIndexes.toString());
     
      
      
      /**
       * insert <per> and </per> markers
       */
     char previousValue;
     char currentValue;
     int adjSize = 0;
      for(int m = 1; m + 1 < classificationValues.size(); ++m )
      {
    	  currentValue = classificationValues.get(m);    	  
    	  previousValue = classificationValues.get(m - 1);
    		  
    	  if(classificationValues.get(m) == '0')
    	    {
    		  fileTokens.add(m + adjSize,"<PER>"); 
    		  ++adjSize;
    	    }
    	  
    	  if(currentValue == '2' & previousValue == '1')
    	    {
    		  fileTokens.add(m + adjSize,"</PER>") ;
    		  ++adjSize;
    	    }    	  
        }
      
      //System.out.format(fileTokens.toString());
      
      /**
       * format output string
       */
     formatedOutputString = null;
     for(int j = 0; j < fileTokens.size(); ++j )
      {
    	 formatedOutputString = formatedOutputString + " " + fileTokens.get(j);
    	 if((fileTokens.get(j)).contains("</NER>") )
    		 formatedOutputString = formatedOutputString + "\n"; 
      } 
      
    // System.out.format(formatedOutputString);      
      
       bufferedReader.close();
           
       String outputfile = "markedOutput.txt";		
       FileWriter outputWriter = new FileWriter(outputfile);		 
       BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);		
       bufferedWriter.write(formatedOutputString);		 
       bufferedWriter.close(); 
	}

/** The input file block. */
private String inputFileBlock;

/** The formated output string. */
public String formatedOutputString;
}

