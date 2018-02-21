import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
  // The word being guessed
  private String targetWord;

  // Guesses taken
  private int guessesTaken;

  // How many chances are left
  private int guessesLeft;

  // Hints left
  private int hintsLeft;

  // List of letters correctly guessed
  ArrayList<Character> lettersGuessed;

  // List of letters not yet guessed
  ArrayList<Character> lettersNotGuessed;

  // Scanner for reading lines
  public Scanner sc = new Scanner(System.in).useDelimiter("\n");

  public GameState initialiseGameState(String targetWord, int guesses, int hints) {

    this.setTargetWord(targetWord);

    if (checkHints(hints) && checkGuesses(guesses) && checkTargetWord(targetWord)) {

      lettersNotGuessed = new ArrayList<Character>();
      lettersGuessed = new ArrayList<Character>();

      // Add all letters in word to "not guessed" list
      for (int i = 0; i < targetWord.length(); ++i) {
    	  char currentLetter = Character.toLowerCase(targetWord.charAt(i));
        if (!lettersNotGuessed.contains(currentLetter)) {
          lettersNotGuessed.add(currentLetter);
        }
      }

      // Set guesses so far to zero
      this.setGuessesTaken(0);

      // Set chances/guesses to given number
      this.setGuessesLeft(guesses);

      // Set number of hints to given number
      this.setHintsLeft(hints);

      return this;
    }

    return null;
  }

  // Make sure target word is not blank, an actual word, and a sensible length
  private boolean checkTargetWord(String word) {
    if (word.equals("")) {
      return false;
    }

    if (word.matches("^.*[^a-zA-Z].*$")) {
      return false;
    }

    if (word.length() > 1000) {
      return false;
    }

    return true;
  }

  private boolean checkGuesses(int guesses) {
    return (guesses > 0 && guesses < 1000);
  }

  private boolean checkHints(int hints) {
    return (hints >= 0 && hints < 1000);
  }



  // Prompt for a guess and check if it's correct
  boolean takeAndCheckGuessInput() {
    String guess = recieveUserGuess();
    if (guess.length() > 1) {

      return checkWordGuess(guess);
    }
    if (guess.equals("?")) {
      showHint();
      return false;
    } else {

      char letter = guess.charAt(0);
      return checkLetterGuess(letter);
    }
  }

  // Prompt for guess and return read string.
  String recieveUserGuess() {

    System.out.print("Guess a letter or word (? for a hint): ");

    // Read user input string
    String userInput = sc.next().toLowerCase();

    return userInput;
  }

  // Check a one-letter guess
  boolean checkLetterGuess(char letter) {

    setGuessesTaken(getGuessesTaken() + 1); // One more guess
    setGuessesLeft(getGuessesLeft() - 1); // Decrease chances left

    for (int i = 0; i < lettersNotGuessed.size(); ++i) { // Loop over the not got
      // If the letter is in the "not yet guessed" list
      if (lettersNotGuessed.get(i) == letter) {
        // remove it and add it to the got list
        lettersNotGuessed.remove(i);
        lettersGuessed.add(letter);

        return true;
      }
    }

    return false;
  }

  // If a whole word is typed in, check if it's correct
  boolean checkWordGuess(String guess) {
    setGuessesTaken(getGuessesTaken() + 1); // One more guess
    setGuessesLeft(getGuessesLeft() - 1); // Decrease chances left

    if (guess.equals(getTargetWord())) {
      lettersNotGuessed.clear();
      return true;
    } else {
      return false;
    }
  }
  
  // Show a hint (a random letter from the "not" list)
  void showHint() {
    if (getHintsLeft() <= 0) {
      System.out.println("No more hints allowed");
    } else {
      System.out.print("Try: ");
      System.out.println(lettersNotGuessed.get((int) (Math.random() * lettersNotGuessed.size())));
      setHintsLeft(getHintsLeft() - 1);
    }
  }
  
  // Print word, obscuring letters not guessed yet
  void obscureAndPrintWord() {

    String obscuredWord = obscureWord(getTargetWord(), lettersGuessed);

    System.out.println(obscuredWord);
  }

  // Take a word and return it with only the allowed letters shown
  String obscureWord(String word, ArrayList<Character> allowedLetters) {

    String obscuredWord = "";

    for (int i = 0; i < word.length(); ++i) {
      if (allowedLetters.contains(word.charAt(i))) {
        obscuredWord += word.charAt(i);
      } else {
        obscuredWord += "-";
      }
    }

    return obscuredWord;
  }

  // Determine if game won (no letters not guessed)
  boolean isGameWon() {
    return (lettersNotGuessed.size() <= 0);
  }

  // Determine if game lost (some letters not guessed, no chances left)
  boolean isGameLost() {
    return (lettersNotGuessed.size() > 0 && getGuessesLeft() <= 0);
  }

  public int getGuessesTaken() {
    return guessesTaken;
  }

  public void setGuessesTaken(int guessesTaken) {
    this.guessesTaken = guessesTaken;
  }

  public String getTargetWord() {
    return targetWord;
  }

  public void setTargetWord(String targetWord) {
    this.targetWord = targetWord;
  }

  public int getGuessesLeft() {
    return guessesLeft;
  }

  public void setGuessesLeft(int guessesLeft) {
    this.guessesLeft = guessesLeft;
  }

  public int getHintsLeft() {
    return hintsLeft;
  }

  public void setHintsLeft(int hintsLeft) {
    this.hintsLeft = hintsLeft;
  }
}
