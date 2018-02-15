
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
		public void guessLettersTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			assertFalse(testgamestate.checkLetterGuess('c'));
			assertTrue(testgamestate.checkLetterGuess('b'));		
		}
		@Test
		public void guessWordsTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			assertFalse(testgamestate.checkWordGuess("cat"));
			assertTrue(testgamestate.checkWordGuess("banana"));		
		}
		@Test
		public void correctHintsNumberAfterGuessTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			testgamestate.checkLetterGuess('c');
			testgamestate.checkLetterGuess('e');	
			assertEquals(testgamestate.getHintsLeft(), 6);

		}
		@Test
		public void correctGuessesNumberAfterGuessTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			testgamestate.checkLetterGuess('c');
			testgamestate.checkLetterGuess('e');	
			assertEquals(testgamestate.getGuessesTaken(), 2);
			assertEquals(testgamestate.getGuessesLeft(), 3);	
		}
		
		//////////////////////////////////////////////////////////
		
		@Test
		public void correctHintsNumberAfterHintsTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			testgamestate.hint();
			testgamestate.hint();
			assertEquals(testgamestate.getHintsLeft(), 4);	
		}
		
		@Test
		public void zeroHintsLeftTest() {
			GameState testgamestate = new GameState("banana", 5, 2);
			testgamestate.hint();
			testgamestate.hint();
			testgamestate.hint();
			testgamestate.hint();
			assertEquals(testgamestate.getHintsLeft(), 0);	
		}
		
		
		


	}


