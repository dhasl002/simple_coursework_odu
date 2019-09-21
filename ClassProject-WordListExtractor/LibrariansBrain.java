/*
 * 
 */
package edu.odu.cs.cs350.namex.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.classifiers.functions.SMO;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;


/**
 * The Class LibrariansBrain.
 */
public class LibrariansBrain {	
	
	/** The smo learning machine. */
	public SMO smoLearningMachine; 
	
	/** The training set file. */
	String trianingSetFile;
	
	/** The sequenced classification codes. */
	String classificationSequenced = "";
	
	LibrariansHand hand = null;
	
	Librarian lib;
	
	/**
	 * Construct and train a librarians brain SMO classifier.
	 *
	 * @param trianingFile the training file
	 * @throws Exception the exception
	 */
 public LibrariansBrain(String trianingFile) throws Exception
	{
	 	lib = new Librarian("inputBlock.txt");
		trianingSetFile = trianingFile;
		BufferedReader breader = null;
		breader = new BufferedReader(new FileReader(trianingSetFile));
		Instances training_data = new Instances(breader);
		training_data.setClassIndex(training_data.numAttributes() - 1);	
		breader.close();	
		/**
		 * epsilon and C values are use to improve the accuracy
		 * of the SMO Classifier
		 */
		final double epsilon = 0.0000001; // initial guess 0.01
		final double C = 1.0;      // initial guess 1.0
		String[] options = {"-N", "-0", "-V", "-1"};

	    smoLearningMachine = new SMO();
		smoLearningMachine.setOptions(options);     
		smoLearningMachine.setC(C);
		smoLearningMachine.setEpsilon (epsilon);
		smoLearningMachine.buildClassifier(training_data); 
	} 
 
 
 /**
  * Gets the sequenced classification codes.
  *
  * @return the sequenced classification codes
  */
 public String getsequencedClassificationCodes() 
 {
	 return classificationSequenced;
 }
 
	
 /**
  * Gets the training set.
  *
  * @param trianingSetFile the trianing set file
  * @return the training set
  * @throws IOException Signals that an I/O exception has occurred.
  */
 public Instances getTrainingSet(String trianingSetFile ) throws IOException
	{
		 BufferedReader trainingSetreader = null;
		 trainingSetreader = new BufferedReader(new FileReader(trianingSetFile));
		 Instances trainingData = new Instances(trainingSetreader);
		 trainingData.setClassIndex(trainingData.numAttributes() - 1);	
		 trainingSetreader.close();
		  
		 return trainingData;		 
	}

 
 public void createMarkedOutputFile() throws IOException
 {
	 hand = new LibrariansHand("inputBlock.txt" ,classificationSequenced);
 }
 
  		
 /**
   * Classify takes as input an arff formated file which contains Instances to be classified,
   * outputs a string of classification values that correlates to the input instances.
   *
   * @param inputFileName the input file name
   * @return the string
   * @throws Exception the exception
   */
  public String classify(String inputFileName) throws Exception
   {
		Instances input_data = null;
		Instance idea = new Instance(100) ;
		double predictedCategory = 0;
		Instance toClassify = null;
		String fileName = inputFileName;
		
		/**
		 * Get the trainingSet so that it's class attribute can be
	     * extracted and used to form the toClassfy Instance. 
	     */
		Instances trainingSet = getTrainingSet(trianingSetFile);
	   // System.out.println (trainingSet); 
		
		
	   /**
	    *  Extract the attributes from the input data arff file. Note: that The input 
	    * data arff file does not contain a classification attribute. Therefore, a
	    * classification attribute must be added before an Instance can be classified.
	    */	
	         
	   BufferedReader instancesReader = new BufferedReader(new FileReader(fileName));
	   ArffReader arff = new ArffReader(instancesReader,1000);
	   input_data = arff.getStructure();
	    
	   /**
	    * Reads each Instance from the input arff file and adds a classification 
	    * attribute. Then classifies each Instance, and forms a string that records 
	    * the classification values of each Instance classified. Note: the 
	    * predictedCategoryCode string can easily be used by LibrariansHand to mark-up
	    * the output file because it represent the correct sequence of token classifications.
	    */
	   boolean temp3 = false;
	   int lastClassification = 2;
       while(true)
	   {
		 if((idea = arff.readInstance(input_data)) == null)
			break;
		 //System.out.println(input_data);
	     input_data.add(idea); 
		 trainingSet.add(idea);
		 //System.out.println(trainingSet);	     
		 for(int j = trainingSet.numInstances(); j > 1; --j )
		  trainingSet.delete(0);
		 //System.out.println (trainingSet);		   
		 toClassify = trainingSet.firstInstance(); 
		System.out.println(toClassify);
		
				 
		 predictedCategory = smoLearningMachine.classifyInstance(toClassify);	
		 
		 
		 
		 if(((int)predictedCategory) == 1 && lastClassification == 2)
		 {
			// System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			 classificationSequenced = classificationSequenced + String.format( "%d", 2);
			 lastClassification = 2;
			 temp3 = true;
		 }
		 else if(((int)predictedCategory) == 0 && lastClassification == 0)
		 {
			 classificationSequenced = classificationSequenced + String.format( "%d", 1);
			 lastClassification = 1;
			 temp3 = true;
		 }
		 else{
		 classificationSequenced = classificationSequenced + String.format( "%d", (int)predictedCategory);
		 }
		 
		 if(((int)predictedCategory)==1 && temp3!=true)
		 {
			 lastClassification = 1;
		 }
		 if(((int)predictedCategory)==0 && temp3!=true)
		 {
			 lastClassification = 0;
		 }
		 if(((int)predictedCategory)==2 && temp3!=true)
		 {
			 lastClassification = 2;
		 }
		 
		 temp3 = false;
		 
		 System.out.println ("The classified instance are: " + classificationSequenced); 
		
       
	   }  
       
		return (classificationSequenced);		
   }
  public LibrariansHand getHand(){
	  return hand;
  }
}
