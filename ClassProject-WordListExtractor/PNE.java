/*
 * 
 */
package edu.odu.cs.cs350.namex.main.java;

/**
 * The Class PNE is the public interface for the project. This class begins the process by creating an object librarians brain. I instructs this instance to create an output file.
 */
public class PNE
{
	LibrariansBrain librarianBrain;
	Trainer myTrainer;
	
	public PNE() throws Exception
	{
	 String trainingBlockTxt = "trainingBlockTxt.txt";
	 String trainingSet = "trainingSet.arff";
	 String inputData ="inputDataBlock.arff";
	 
	 myTrainer = new Trainer(trainingBlockTxt);
	 librarianBrain = new LibrariansBrain(trainingSet);
	 librarianBrain.classify(inputData);
	 librarianBrain.createMarkedOutputFile();
	 
	}
	
	public LibrariansBrain getBrain()
	{
		return librarianBrain;
	}
}