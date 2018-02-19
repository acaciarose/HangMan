
import java.util.Scanner;

//Main game: print categories, perform game loop, print win/loss messages
public class HangMan {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GameState game;
		CommandOpts userOptions;
		WordsChooser wc = new WordsChooser();

		userOptions = new CommandOpts(args);

		// If no command line arguments (no word source file specified)
		if (userOptions.wordsource == "") {

			System.out.println("  1. Counties");
			System.out.println("  2. Countries");
			System.out.println("  3. Cities");

			System.out.print("Pick a category:");

			// Make new game based on given category/source
			game = new GameState(wc.getRandomWordInCategory(sc.nextInt()), userOptions.getMaxguesses(),
					userOptions.getMaxhints());
		} else {
			game = new GameState(wc.getRandomWordFromSourceFile(userOptions.wordsource), userOptions.getMaxguesses(),
					userOptions.getMaxhints());
		}

		// Main game loop: while game not won or lost, keep accepting guesses
		while (!game.isGameWon() && !game.isGameLost()) {
			// Print word, obscuring letters not guessed so far
			game.obscureAndPrintWord();

			System.out.println("Guesses remaining: " + game.getGuessesLeft());

			// Read in a guess and decide if it was correct
			boolean wasGuessCorrect = game.takeAndCheckGuessInput();

			if (wasGuessCorrect)
				System.out.println("Good guess!");
			if (!wasGuessCorrect)
				System.out.println("Wrong guess!");
		}

		// Print winning message if won
		if (game.isGameWon()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.getGuessesTaken() + " guesses");
		} else {
			System.out.println("You lost! The word was " + game.getTargetWord());
		}
	}

}
