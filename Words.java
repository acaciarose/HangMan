

import java.io.*;
import java.util.ArrayList;

//Contains all available words for guessing, and methods for returning random words
public class Words {

	static String[] words1 = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
			            "East Lothian", "Highland", "Dumfries and Galloway",
			            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
	static String[] words2 = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland", 
			            "France", "Germany", "Netherlands", "Spain", "Portugal",
			            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
	static String[] words3 = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };
			
	static ArrayList<String> customwords;
	
	//Return a random word for given category
	public static String randomWord(int category) {
		if (category == 1)
			return words1[(int)(Math.random()*9)];
		if (category == 2)
			return words2[(int)(Math.random()*15)];
		return words3[(int)(Math.random()*10)];
	}
	
	//return a random word, using a given file of words as a source
	public static String randomWord(String wordsource) {
		String line;
		customwords = new ArrayList<String>();
		
		try {
			//Read words file
			FileReader file = new FileReader(wordsource);
			BufferedReader reader = new BufferedReader(file);
			while((line = reader.readLine()) != null) {
                customwords.add(line);
			}
			//Return random word from given words file
			return customwords.get((int)(Math.random()*customwords.size()));
		} catch(FileNotFoundException e) {
			System.out.println("File error");
			return "";
		} catch(IOException e) {
		System.out.println("IO error");
		return "";
	}
	}
}

