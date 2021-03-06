import java.util.InputMismatchException;
import java.util.Scanner;

// Main game: print categories, perform game loop, print win/loss messages

public class HangMan {

  public static void main(String[] args) {

    GameState game = new GameState();
    CommandLineOptions userOptions;
    WordsChooser wc = new WordsChooser();

    userOptions = new CommandLineOptions(args);

    // If no command line arguments (no word source file specified)
    if (userOptions.wordsource == "") {

      System.out.println("  1. Counties");
      System.out.println("  2. Countries");
      System.out.println("  3. Cities");

      System.out.print("Pick a category:");

      int category = getCategoryInput();

      String word = wc.getRandomWordInCategory(category);

      // Make new game based on given category
      game = game.initialiseGameState(word, userOptions.getMaxguesses(), userOptions.getMaxhints());
    } else {
      // Make new game based on given word source file
      game =
          game.initialiseGameState(
              wc.getRandomWordFromSourceFile(userOptions.wordsource),
              userOptions.getMaxguesses(),
              userOptions.getMaxhints());
    }

    if (game == null) {
      System.out.println("Incorrect parameters supplied. Please make sure:");
      System.out.println("Target word category must be 1, 2, or 3.");
      System.out.println(
          "Target source file is non-empty, contains only words, and is a text file");
      System.out.println("Number of guesses is larger than zero and smaller than 1000");
      System.out.println("Number of hints is larger than or equal to zero and smaller than 1000");

    } else {

      performMainGameLoop(game);
    }
  }

  private static void performMainGameLoop(GameState game) {
    // Main game loop: while game not won or lost, keep accepting guesses
    while (!game.isGameWon() && !game.isGameLost()) {
      // Print word, obscuring letters not guessed so far
      game.obscureAndPrintWord();

      System.out.println("Guesses remaining: " + game.getGuessesLeft());
      System.out.println("Hints remaining: " + game.getHintsLeft());

      // Read in a guess and decide if it was correct
      boolean wasGuessCorrect = game.takeAndCheckGuessInput();

      if (wasGuessCorrect) {
        System.out.println("Good guess!");
      }
      if (!wasGuessCorrect) {
        System.out.println("Try again!");
      }
    }

    // Print winning message if won
    if (game.isGameWon()) {
      System.out.println("Well done!");
      System.out.println("You took " + game.getGuessesTaken() + " guesses");
    } else {
      System.out.println("You lost! The word was " + game.getTargetWord());
    }
  }

  private static int getCategoryInput() {

    try {
      Scanner sc = new Scanner(System.in);
      int category = sc.nextInt();
      sc.nextLine(); // Consumes "\n"
      return category;
    } catch (InputMismatchException e) {
      return -1;
    }
  }
}
