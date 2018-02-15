
import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.*;
	
public class Tests {

		@Test
		public void normalOperationTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
//			assertEquals(testgamestate.guessesLeft, 5);
//			assertEquals(testgamestate.hintsLeft, 6);
//			assertEquals(testgamestate.guessesTaken, 0);
//			assertEquals(testgamestate.targetWord, "banana");
//			assertEquals(testgamestate.not.size(), 3);
//			assertEquals(testgamestate.lettersGuessed.size(), 0);

		}
		
		@Test
		public void zeroGuessesAndHintsTest() {

			GameState testgamestate = new GameState("banana", 0, 0);
			assertEquals(testgamestate,null);

		}
		@Test
		public void tooLargeGuessesAndHintsTest() {

			GameState testgamestate = new GameState("banana", 10000000, 100000000);
			assertEquals(testgamestate,null);

		}
		@Test
		public void negativeIntegerGuessesAndHintsTest() {

			GameState testgamestate = new GameState("banana", -100, -20);
			assertEquals(testgamestate,null);
		}
		/////////////////////////////
		
		@Test
		public void optionsTest() {
			String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
			CommandOpts opts = new CommandOpts(args);
			assertEquals(opts.maxguesses, 2);
			assertEquals(opts.maxhints, 4);
			assertEquals(opts.wordsource, "words.txt");
		}
		
		///////////////////////////////////////////////////////////
		@Test
		public void guessTest() {
			//note: it's really hard to test guesses because of uder input
			//solution: change, so user input is fed into separate guess function.
			
		}


	}


