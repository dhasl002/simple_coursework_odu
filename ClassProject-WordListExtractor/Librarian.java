package edu.odu.cs.cs350.namex.main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.StringTokenizer;

import edu.odu.cs.extract.wordlists.WordLists;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class Librarian {
	
	/** The instances. */
	Instances instances;
	
	/** The attr info. */
	FastVector attrInfo;
	
	ArffSaver saver;
	
	HashSet<String> inDictionarys;
	HashSet<String> inCitys;
	HashSet<String> inPlaces;
	HashSet<String> knownFirstNames;
	HashSet<String> knownLastNames;
	HashSet<String> DTICFirstNames;
	HashSet<String> DTICLastNames;
	HashSet<String> killWords;
	HashSet<String> honorific;
	HashSet<String> lastNamePrefixes2;
	HashSet<String> lastNameSuffixes2;
	HashSet<String> nonPersonalIdentifierCues2;
	
		
	public Librarian(String inputBlock) throws Exception
	{
		String fileName = inputBlock;
		
        String line = null;
        String text = "";
        
        knownFirstNames = new HashSet<String>();
        for (String s: WordLists.commonFirstNames()) 
        { // commonFirstNames returns Iterable<String>
        	               knownFirstNames.add (s); 
        }
        inDictionarys = new HashSet<String>();
        for (String s: WordLists.englishDictionary()) 
        { // commonFirstNames returns Iterable<String>
        	               inDictionarys.add (s); 
        }
        inCitys = new HashSet<String>();
        for (String s: WordLists.citiesAndStatesUS()) 
        { // commonFirstNames returns Iterable<String>
        	               inCitys.add (s); 
        }
        inPlaces = new HashSet<String>();
        for (String s: WordLists.countriesAndTerritories()) 
        { // commonFirstNames returns Iterable<String>
        	               inPlaces.add (s); 
        }
        knownLastNames = new HashSet<String>();
        for (String s: WordLists.commonLastNames()) 
        { // commonFirstNames returns Iterable<String>
        	               knownLastNames.add (s); 
        }
        DTICFirstNames = new HashSet<String>();
        for (String s: WordLists.firstNames()) 
        { // commonFirstNames returns Iterable<String>
        	DTICFirstNames.add (s); 
        }
        DTICLastNames = new HashSet<String>();
        for (String s: WordLists.lastNames()) 
        { // commonFirstNames returns Iterable<String>
        	DTICLastNames.add (s); 
        }
        killWords = new HashSet<String>();
        for (String s: WordLists.stoplist()) 
        { // commonFirstNames returns Iterable<String>
        	killWords.add (s); 
        }
        honorific = new HashSet<String>();
        for (String s: WordLists.honorifics()) 
        { // commonFirstNames returns Iterable<String>
        	honorific.add (s); 
        }
        lastNamePrefixes2 = new HashSet<String>();
        for (String s: WordLists.lastNamePrefixes()) 
        { // commonFirstNames returns Iterable<String>
        	lastNamePrefixes2.add (s); 
        }
        lastNameSuffixes2 = new HashSet<String>();
        for (String s: WordLists.lastNameSuffixes()) 
        { // commonFirstNames returns Iterable<String>
        	lastNameSuffixes2.add (s); 
        }
        nonPersonalIdentifierCues2 = new HashSet<String>();
        for (String s: WordLists.nonPersonalIdentifierCues()) 
        { // commonFirstNames returns Iterable<String>
        	nonPersonalIdentifierCues2.add (s); 
        }
        
        
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null)
        	text = text + line;
        bufferedReader.close();
        
        String LexicalFeatures[] = {"number", "punctuation mark", "capitalized letter", "capitalized word", "all caps", "new line", "nothing"};
		String PartsOfSpeech[] = {"articles", "conjunction", "period", "comma", "hyphen", "nothing"};
		Attribute inDictionary = new Attribute("inDictionary");
		Attribute inCity = new Attribute("inCity");
		Attribute inPlace = new Attribute("inPlace");
		Attribute inDTICFirstNames = new Attribute("inDTICFirstNames");
		Attribute inDTICLastNames = new Attribute("inDTICLastNames");
		Attribute inCommonFirstNames = new Attribute("inCommonFirstNames");
		Attribute inCommonLastNames = new Attribute("inCommonLastNames");
		Attribute inKillWords = new Attribute("inKillWords");
		Attribute honorifics = new Attribute("honorifics");
		Attribute lastNamePrefixes = new Attribute("lastNamePrefixes");
		Attribute lastNameSuffixes = new Attribute("lastNameSuffixes");
		Attribute nonPersonalIdentifierCues = new Attribute("nonPersonalIdentifierCues");
		Attribute Lexical_Features = new Attribute("LexicalFeatures", fastV(LexicalFeatures));
		Attribute Parts_Of_Speech = new Attribute("PartsOfSpeech", fastV(PartsOfSpeech));
		
		//adding each created attribute to the FastVector
		attrInfo = new FastVector(14);
		attrInfo.addElement(inDictionary);
		attrInfo.addElement(inCity);
		attrInfo.addElement(inPlace);
		attrInfo.addElement(inDTICFirstNames);
		attrInfo.addElement(inDTICLastNames);
		attrInfo.addElement(inCommonFirstNames);
		attrInfo.addElement(inCommonLastNames);
		attrInfo.addElement(inKillWords);
		attrInfo.addElement(Lexical_Features);
		attrInfo.addElement(Parts_Of_Speech);
		attrInfo.addElement(honorifics);
		attrInfo.addElement(lastNamePrefixes);
		attrInfo.addElement(lastNameSuffixes);
		attrInfo.addElement(nonPersonalIdentifierCues);
		
		
        
        
      //reads string and divides it into tokens located in trainingARFF[]
		StringTokenizer tokenizer = new StringTokenizer(text, "<> ");
		int numberOfTokens = tokenizer.countTokens();
		String[] trainingARFF;
		trainingARFF = new String[numberOfTokens];
		
		for (int i = 0; i < numberOfTokens; i++) 
		{
		      String token = tokenizer.nextToken();
		      trainingARFF[i] = token;
		      //System.out.println(token.toString());
		}
      		
      		//creates the instances and adds all single instance to this instances based on interpreting the string into attributes
      		Instances training = new Instances( "output", attrInfo, numberOfTokens);
      		int numAttributes = 14;
      		int shingling = 5;
      		int begin = 0;
      		int end = 0;
      		boolean temp2 = false;
      		
      		String num0 = "0";
      		String num1 = "1";
      		String num2 = "2";
      		String num3 = "3";
      		String num4 = "4";
      		String num5 = "5";
      		String num6 = "6";
      		String num7 = "7";
      		String num8 = "8";
      		String num9 = "9";
      		String punctuationMark1 = ".";
      		String punctuationMark2 = "!";
      		String punctuationMark3 = "?";
      		String punctuationMark4 = "@";
      		String punctuationMark5 = "#";
      		String punctuationMark6 = "$";
      		String punctuationMark7 = "%";
      		String punctuationMark8 = "&";
      		String punctuationMark9 = "*";
      		String punctuationMark10 = ",";
      		String punctuationMark11 = ";";
      		String punctuationMark12 = "-";
      		boolean temp = false;  
        
        
      				
      				for(int k = 0; k < numberOfTokens; k++)
      				{
      					if(numberOfTokens-1 != k && (trainingARFF[k].contains("NER") || trainingARFF[k].contains("/NER")))
    					{
    						k++;
    					}
    					if(numberOfTokens-1 != k && (trainingARFF[k].contains("NER") || trainingARFF[k].contains("/NER")))
    					{
    						k++;
    					}
    					if(trainingARFF[k].contains("PER") || trainingARFF[k].contains("/PER"))
    					{
    						k++;
    						temp2 = true;
    					}
      					//lexical features
      					Instance instance = new Instance(numAttributes);
      					instance.setValue((Attribute)attrInfo.elementAt(8), "nothing");
      					
      					if(trainingARFF[k].startsWith(trainingARFF[k].substring(0, 1).toUpperCase())  && trainingARFF[k].length() > 1)
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(8), "capitalized word");
      					}
      					
      					if(trainingARFF[k].toUpperCase() == trainingARFF[k])
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(8), "all caps");
      					}
      					
      					if(trainingARFF[k].startsWith(trainingARFF[k].substring(0, 1).toUpperCase()) && trainingARFF[k].length() == 1)
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(8), "capitalized letter");
      					}
      					
      					if(trainingARFF[k].contains(punctuationMark1) || trainingARFF[k].contains(punctuationMark2) || trainingARFF[k].contains(punctuationMark3) || trainingARFF[k].contains(punctuationMark4) || trainingARFF[k].contains(punctuationMark5) || trainingARFF[k].contains(punctuationMark6) || trainingARFF[k].contains(punctuationMark7) || trainingARFF[k].contains(punctuationMark8) || trainingARFF[k].contains(punctuationMark9) || trainingARFF[k].contains(punctuationMark10) || trainingARFF[k].contains(punctuationMark11)|| trainingARFF[k].contains(punctuationMark12))
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(8), "punctuation mark");
      					}
      			
      					if(trainingARFF[k].contains(num0) || trainingARFF[k].contains(num1) || trainingARFF[k].contains(num2) || trainingARFF[k].contains(num3) || trainingARFF[k].contains(num4) || trainingARFF[k].contains(num5) || trainingARFF[k].contains(num6) || trainingARFF[k].contains(num7) || trainingARFF[k].contains(num8) || trainingARFF[k].contains(num9))
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(8), "number");
      					}
      					
      					//parts of speech
      					instance.setValue((Attribute)attrInfo.elementAt(9), "nothing");
      					if(trainingARFF[k].equalsIgnoreCase("a") || trainingARFF[k].equalsIgnoreCase("an") || trainingARFF[k].equalsIgnoreCase("the"))
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(9), "articles");
      					}
      					//needs more work for classifying multiple word conjunctions
      					if(trainingARFF[k].equalsIgnoreCase("for") || trainingARFF[k].equalsIgnoreCase("or") || trainingARFF[k].equalsIgnoreCase("but") || trainingARFF[k].equalsIgnoreCase("nor") || trainingARFF[k].equalsIgnoreCase("so") || trainingARFF[k].equalsIgnoreCase("for") || trainingARFF[k].equalsIgnoreCase("yet") || trainingARFF[k].equalsIgnoreCase("after") || trainingARFF[k].equalsIgnoreCase("although") || trainingARFF[k].equalsIgnoreCase("as") || trainingARFF[k].equalsIgnoreCase("long") || trainingARFF[k].equalsIgnoreCase("because") || trainingARFF[k].equalsIgnoreCase("before") || trainingARFF[k].equalsIgnoreCase("even") || trainingARFF[k].equalsIgnoreCase("though") || trainingARFF[k].equalsIgnoreCase("if") || trainingARFF[k].equalsIgnoreCase("once") || trainingARFF[k].equalsIgnoreCase("provided")  || trainingARFF[k].equalsIgnoreCase("since") || trainingARFF[k].equalsIgnoreCase("so") || trainingARFF[k].equalsIgnoreCase("that") || trainingARFF[k].equalsIgnoreCase("till") || trainingARFF[k].equalsIgnoreCase("unless") || trainingARFF[k].equalsIgnoreCase("until") || trainingARFF[k].equalsIgnoreCase("what") || trainingARFF[k].equalsIgnoreCase("when") || trainingARFF[k].equalsIgnoreCase("whenever") || trainingARFF[k].equalsIgnoreCase("wherever") || trainingARFF[k].equalsIgnoreCase("whether")  || trainingARFF[k].equalsIgnoreCase("while") || trainingARFF[k].equalsIgnoreCase("accordingly") || trainingARFF[k].equalsIgnoreCase("also") || trainingARFF[k].equalsIgnoreCase("anyway") || trainingARFF[k].equalsIgnoreCase("besides") || trainingARFF[k].equalsIgnoreCase("consequently") || trainingARFF[k].equalsIgnoreCase("finally") || trainingARFF[k].equalsIgnoreCase("example") || trainingARFF[k].equalsIgnoreCase("instance") || trainingARFF[k].equalsIgnoreCase("further") || trainingARFF[k].equalsIgnoreCase("furthermore") || trainingARFF[k].equalsIgnoreCase("hence") || trainingARFF[k].equalsIgnoreCase("however") || trainingARFF[k].equalsIgnoreCase("incidentally") || trainingARFF[k].equalsIgnoreCase("indeed") || trainingARFF[k].equalsIgnoreCase("in") || trainingARFF[k].equalsIgnoreCase("fact") || trainingARFF[k].equalsIgnoreCase("instead") || trainingARFF[k].equalsIgnoreCase("likewise") || trainingARFF[k].equalsIgnoreCase("meanwhile") || trainingARFF[k].equalsIgnoreCase("moreover") || trainingARFF[k].equalsIgnoreCase("namely") || trainingARFF[k].equalsIgnoreCase("now") || trainingARFF[k].equalsIgnoreCase("of") || trainingARFF[k].equalsIgnoreCase("course") || trainingARFF[k].equalsIgnoreCase("on") || trainingARFF[k].equalsIgnoreCase("contrary") || trainingARFF[k].equalsIgnoreCase("hand") || trainingARFF[k].equalsIgnoreCase("otherwise") || trainingARFF[k].equalsIgnoreCase("nevertheless") || trainingARFF[k].equalsIgnoreCase("next") || trainingARFF[k].equalsIgnoreCase("nonetheless") || trainingARFF[k].equalsIgnoreCase("similarly") || trainingARFF[k].equalsIgnoreCase("far") || trainingARFF[k].equalsIgnoreCase("now") || trainingARFF[k].equalsIgnoreCase("still") || trainingARFF[k].equalsIgnoreCase("then") || trainingARFF[k].equalsIgnoreCase("therefore") || trainingARFF[k].equalsIgnoreCase("thus"))
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(9), "conjunction");
      					}
      					if(trainingARFF[k].contains(punctuationMark1))
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(9), "period");
      					}
      					if(trainingARFF[k].contains(punctuationMark10))
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(9), "comma");
      					}
      					if(trainingARFF[k].contains(punctuationMark12))
      					{
      						instance.setValue((Attribute)attrInfo.elementAt(9), "hyphen");
      					}
      					//gazeteers
      					//dictionary
      					//InputStreamIterator i = new InputStreamIterator(WordLists.englishDictionary());
      					instance.setValue((Attribute)attrInfo.elementAt(0), 0); 
      					
      					String s = "";
  						s = trainingARFF[k];
      						if(inDictionarys.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(0), 1); 
      						}
      					
      					//cities
      					instance.setValue((Attribute)attrInfo.elementAt(1), 0); 
      					
      						if(inCitys.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(1), 1); 
      						}
      					
      					//places
      					instance.setValue((Attribute)attrInfo.elementAt(2), 0); 
      					
      						if(inPlaces.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(2), 1); 
      						}
      					
      					
      					//First Names DTIC
      					instance.setValue((Attribute)attrInfo.elementAt(3), 0); 
      						if(DTICFirstNames.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(3), 1); 
      						}
      					
      					//Last Names DTIC
      					instance.setValue((Attribute)attrInfo.elementAt(4), 0); 
      					
      						if(DTICLastNames.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(4), 1); 
      						}
      					
      					//First Names Common
      					instance.setValue((Attribute)attrInfo.elementAt(5), 0); 
      					
      						if(knownFirstNames.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(5), 1); 
      						}
      					
      					//Last Names Common
      					instance.setValue((Attribute)attrInfo.elementAt(6), 0); 
      					
      						if(knownLastNames.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(6), 1); 
      						}
      					
      					//Kill Words
      					instance.setValue((Attribute)attrInfo.elementAt(7), 0); 
      					
      						if(killWords.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(7), 1); 
      						}
      						instance.setValue((Attribute)attrInfo.elementAt(10), 0); 
      	  					
      						if(honorific.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(10), 1); 
      						}
      					instance.setValue((Attribute)attrInfo.elementAt(11), 0); 
      	  					
      						if(s.length()>2 && lastNamePrefixes2.contains(s.substring(0, 3)))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(11), 1); 
      						}
      					instance.setValue((Attribute)attrInfo.elementAt(12), 0); 
      	  					
      						if(s.length()>2 && lastNameSuffixes2.contains(s.substring(s.length()-3)))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(12), 1); 
      						}
      					instance.setValue((Attribute)attrInfo.elementAt(13), 0); 
      	  					
      						if(nonPersonalIdentifierCues2.contains(s))
      						{
      							instance.setValue((Attribute)attrInfo.elementAt(13), 1); 
      						}

      		        training.add(instance); // Add new instance to training data
      				}
      			
      		
      		//saving instances to file
      		saver = new ArffSaver();
      		 saver.setInstances(training);
      		saver.setFile(new File("inputDataBlock.arff"));
      		 saver.writeBatch();
      
	
	}
	
	public ArffSaver getSaver()
	{
		return saver;
	}
	
	
	
	/**
	 * Fast v.
	 *
	 * @param data the data
	 * @return the fast vector
	 */
	private FastVector fastV(String[] data) {
	      FastVector result = new FastVector(data.length);
	      for (String s: data) {
	          result.addElement(s);
	      }
	      return result;
	  }
	}

