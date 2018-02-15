

import java.util.Scanner;

//Main game: print categories, perform game loop, print win/loss messages
public class HangMan {
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
GameState game; CommandOpts opts;
		boolean correct;
		
		opts = new CommandOpts(args);
		
		//If no command line arguments (no word source file specified)
		if (opts.wordsource == "") {
		
		System.out.println("  1. Counties");
		System.out.println("  2. Countries");
		System.out.println("  3. Cities");

		System.out.print("Pick a category:");

		//Make new game based on given category/source
		 game = new GameState(Words.randomWord(sc.nextInt()), opts.maxguesses, opts.maxhints);
		}
		else {
			game = new GameState(Words.randomWord(opts.wordsource), opts.maxguesses, opts.maxhints);
		}
		
		//Main game loop: while game not won or lost, keep accepting guesses
		while(!game.won() && !game.lost()) {
			//Print word, obscuring letters not guessed so far
			game.showWord();
			
			System.out.println("Guesses remaining: " + game.wrong);
			
			//Read in a guess and decide if it was correct
			 correct = game.guessLetter();
			
			if (correct) System.out.println("Good guess!");
			if (!correct) System.out.println("Wrong guess!");
		}
		
		//Print winning message if won
		if (game.won()) {
			System.out.println("Well done!");
			System.out.println("You took " + game.g + " guesses");
		} else {
			System.out.println("You lost! The word was " + game.word);
		}
	}

}
