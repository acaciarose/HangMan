
import java.io.*;
import java.util.ArrayList;

//Contains all available words for guessing, and methods for returning random words

enum WordType {
	SCOTTISHAREAS, EUCOUNTRIES, SCOTTISHTOWNS
};

public class WordsChooser {

	public WordsChooser() {

	}

	private final String[] scottishAreasWordList = { "Argyll and Bute", "Caithness", "Kingdom of Fife", "East Lothian",
			"Highland", "Dumfries and Galloway", "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
	private final String[] europeanCountriesWordList = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
			"France", "Germany", "Netherlands", "Spain", "Portugal", "Belgium", "Luxembourg", "Switzerland", "Italy",
			"Greece" };
	private final String[] scottishTownsWordList = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
			"Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };

	// Return a random word for given category
	public String getRandomWordInCategory(int category) {
		switch (category) {
		case (1):
			return scottishAreasWordList[(int) (Math.random() * 9)];
		case (2):
			return europeanCountriesWordList[(int) (Math.random() * 15)];
		default:
			return scottishTownsWordList[(int) (Math.random() * 10)];
		}

	}

	// return a random word, using a given file of words as a source
	public String getRandomWordFromSourceFile(String wordsource) {
			ArrayList<String> userWordList = readWordsFromSourceFile(wordsource);
					
			return userWordList.get((int) (Math.random() * userWordList.size()));

	}
	
	
	
	public ArrayList<String> readWordsFromSourceFile(String wordsource) {
		String currentLine;
		ArrayList<String> userWordList = new ArrayList<String>();
		
		try {
			// Read words file
			FileReader wordSourceFile = new FileReader(wordsource);
			BufferedReader reader = new BufferedReader(wordSourceFile);
			while ((currentLine = reader.readLine()) != null) {
				userWordList.add(currentLine);
			}
			// Return random word from given words file
			reader.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File error");
		} catch (IOException e) {
			System.out.println("IO error");
		}
		
		
		return userWordList;
		
		
	}
}
