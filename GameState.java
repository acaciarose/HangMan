

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
	//The word being guessed
	public String word;

	//Guesses taken
	public int g;

	//How many chances are left
	public int wrong;

	//Hints left
	public int h;
	
	//List of letters correctly guessed
	ArrayList<Character> got;
	//List of letters not yet guessed
	ArrayList<Character> not;
	
	//Scanner for reading lines
	public Scanner sc = new Scanner(System.in).useDelimiter("\n");
	

	//Make new game state
	public GameState(String target, int g, int h) {
		this.word = target;
		not = new ArrayList<Character>();
		   got = new ArrayList<Character>();
		
		   //Add all letters in word to "not guessed" list
		for(int i = 0; i < target.length(); ++i) {
			if (!not.contains(Character.toLowerCase(target.charAt(i))))
			not.add(Character.toLowerCase(target.charAt(i)));
		}

		//??????
		//System.out.println(missing);
		
		//Set guesses so far to zero
		this.g = 0;

		//Set chances/guesses to given number
		wrong = g;

		//Set number of hints to given number
		this.h = h;
	}
	
	//Print word, obscuring letters not guessed yet
	void showWord() {
		for (int i = 0; i < word.length(); ++i) {
			if (got.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
		// System.out.println(missing); //????
	}
	
	//Prompt for a guess and check if it's correct
	boolean guessLetter() {
		int i;
		char letter;
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		//Read user input string
		String str = sc.next().toLowerCase();
		
		//If a whole word is typed in, check if it's correct
		if (str.length() > 1) {
			if (str==word) {
				not.clear();
				return true;
			} else return false;
		}
		
		//Take first letter of string
		letter = str.charAt(0);
		
		//Give hint
		if (letter == '?') {
			hint();
			return false;
		}
		

		for(i = 0; i < not.size(); ++i) { // Loop over the not got
			//If the letter is in the "not yet guessed" list
			if (not.get(i) == letter) {
				//remove it and add it to the got list
				not.remove(i);
				got.add(letter);
				//Increase guess counter
				g++;
				return true;
			}
		}

		g++; // One more guess
		wrong--;//Decrease chances left
		return false;
	}
	
	//Determine if game won (no letters not guessed)
	boolean won() {
		if (not.size() == 0) return true; else return false;
	}

	//Determine if game lost (some letters not guessed, no chances left)
	boolean lost() {
		if (not.size() > 0 && wrong == 0) return true; else return false;
	}

	//Show a hint (a random letter from the "not" list)
	void hint() {
		if (h == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		System.out.println(not.get((int)(Math.random()*not.size())));
	}
}
