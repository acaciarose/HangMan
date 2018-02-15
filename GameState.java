

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
	//The word being guessed
	private String targetWord;

	//Guesses taken
	private int guessesTaken;

	//How many chances are left
	private int guessesLeft;

	//Hints left
	private int hintsLeft;
	
	//List of letters correctly guessed
	ArrayList<Character> lettersGuessed;
	//List of letters not yet guessed
	ArrayList<Character> lettersNotGuessed;
	
	//Scanner for reading lines
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	

	// Make new game state
	public GameState(String targetWord, int guesses, int hints) {
		this.setTargetWord(targetWord);
		lettersNotGuessed = new ArrayList<Character>();
		lettersGuessed = new ArrayList<Character>();

		// Add all letters in word to "not guessed" list
		for (int i = 0; i < targetWord.length(); ++i) {
			if (!lettersNotGuessed.contains(Character.toLowerCase(targetWord.charAt(i))))
				lettersNotGuessed.add(Character.toLowerCase(targetWord.charAt(i)));
		}

		// Set guesses so far to zero
		this.setGuessesTaken(0);

		// Set chances/guesses to given number
		setGuessesLeft(guesses);

		// Set number of hints to given number
		this.hintsLeft = hints;
	}
	
	//Print word, obscuring letters not guessed yet
	void showWord() {
		for (int i = 0; i < getTargetWord().length(); ++i) {
			if (lettersGuessed.contains(getTargetWord().charAt(i))) {
				System.out.print(getTargetWord().charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
	}
	
	// Prompt for a guess and check if it's correct
	boolean takeAndCheckGuessInput() {
		String guess = recieveUserGuess();
		if (guess.length() > 1) {
			return checkWordGuess(guess);
		}
		if (guess.equals("?")) {
			hint();
			return false;
		} 	
		else {
			char letter = guess.charAt(0);
			return checkLetterGuess(letter);
		}
	}
	
	//Prompt for guess and return read string.
	String recieveUserGuess() {
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		//Read user input string
		String userInput = sc.next().toLowerCase();
		
		return userInput;
	
	}
	
	//Check a one-letter guess
	boolean checkLetterGuess(char letter) {
	
		for(int i = 0; i < lettersNotGuessed.size(); ++i) { // Loop over the not got
			//If the letter is in the "not yet guessed" list
			if (lettersNotGuessed.get(i) == letter) {
				//remove it and add it to the got list
				lettersNotGuessed.remove(i);
				lettersGuessed.add(letter);
				//Increase guess counter
				setGuessesTaken(getGuessesTaken() + 1);
				return true;
			}
		}

		setGuessesTaken(getGuessesTaken() + 1); // One more guess
		setGuessesLeft(getGuessesLeft() - 1);//Decrease chances left
		return false;
	
	}

	//If a whole word is typed in, check if it's correct
	boolean checkWordGuess(String guess) {
					
			if (guess.equals(getTargetWord())) {
				lettersNotGuessed.clear();
				return true;
			} else return false;	

	}
	
	//Determine if game won (no letters not guessed)
	boolean won() {
		if (lettersNotGuessed.size() == 0) return true; else return false;
	}

	//Determine if game lost (some letters not guessed, no chances left)
	boolean lost() {
		if (lettersNotGuessed.size() > 0 && getGuessesLeft() == 0) return true; else return false;
	}

	//Show a hint (a random letter from the "not" list)
	void hint() {
		//possible bug here?
		if (hintsLeft == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		System.out.println(lettersNotGuessed.get((int)(Math.random()*lettersNotGuessed.size())));
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
}
