import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// Contains all available words for guessing, and methods for returning random words

public class WordsChooser {

  private static final int SCOTTISH_AREAS = 1;
  private static final int EUROPEAN_COUNTRIES = 2;
  private static final int SCOTTISH_TOWNS = 3;

  private final String[] scottishAreasWordList = {
    "Argyll and Bute",
    "Caithness",
    "Kingdom of Fife",
    "East Lothian",
    "Highland",
    "Dumfries and Galloway",
    "Renfrewshire",
    "Scottish Borders",
    "Perth and Kinross"
  };
  private final String[] europeanCountriesWordList = {
    "Scotland",
    "England",
    "Wales",
    "Northern Ireland",
    "Ireland",
    "France",
    "Germany",
    "Netherlands",
    "Spain",
    "Portugal",
    "Belgium",
    "Luxembourg",
    "Switzerland",
    "Italy",
    "Greece"
  };
  private final String[] scottishTownsWordList = {
    "St Andrews",
    "Edinburgh",
    "Glasgow",
    "Kirkcaldy",
    "Perth",
    "Dundee",
    "Stirling",
    "Inverness",
    "Aberdeen",
    "Falkirk"
  };

  // Return a random word for given category
  public String getRandomWordInCategory(int category) {
    switch (category) {
      case (SCOTTISH_AREAS):
        return getScottishAreasWordList()[new Random().nextInt(getScottishAreasWordList().length)];
      case (EUROPEAN_COUNTRIES):
        return getEuropeanCountriesWordList()[
            new Random().nextInt(getEuropeanCountriesWordList().length)];
      case (SCOTTISH_TOWNS):
        return getScottishTownsWordList()[new Random().nextInt(getScottishTownsWordList().length)];
      default:
        return "";
    }
  }

  // Return a random word, using a given file of words as a source
  public String getRandomWordFromSourceFile(String wordsource) {

    // Read words from source file into list
    ArrayList<String> userWordList = readWordsFromSourceFile(wordsource);

    if (userWordList == null) {
      return null;
    } else {
      // Return random item from word list
      int randomIndex = new Random().nextInt(userWordList.size());
      return userWordList.get(randomIndex);
    }
  }

  // Read in the given source file and extract its words.
  public ArrayList<String> readWordsFromSourceFile(String wordsource) {
    String currentLine;
    ArrayList<String> userWordList = new ArrayList<String>();

    try {
      // Read words file
      FileReader wordSourceFile = new FileReader(wordsource);
      BufferedReader reader = new BufferedReader(wordSourceFile);
      while ((currentLine = reader.readLine()) != null) {
        // Invalid input (alphabet letters only) recieved, return null
        if (checkForNonAlphabeticCharacters(currentLine)) {
          reader.close();
          return null;
        }

        if (checkLineNotEmpty(currentLine)) {
          userWordList.add(currentLine);
        }
      }

      reader.close();

      // Disallow empty input files
      if (userWordList.isEmpty()) {
        return null;
      }
      return userWordList;

    } catch (FileNotFoundException e) {
      System.out.println("File error");
      return null;
    } catch (IOException e) {
      System.out.println("IO error");
      return null;
    }
  }

  public boolean checkForNonAlphabeticCharacters(String line) {
    return line.matches("^.*[^a-zA-Z].*$");
  }

  public boolean checkLineNotEmpty(String line) {
    return !(line.equals(""));
  }

  public String[] getScottishAreasWordList() {
    return scottishAreasWordList;
  }

  public String[] getScottishTownsWordList() {
    return scottishTownsWordList;
  }

  public String[] getEuropeanCountriesWordList() {
    return europeanCountriesWordList;
  }
}
