
import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.*;
	
public class Tests {

		@Test
		public void normalOperationTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			assertEquals(testgamestate.getGuessesLeft(), 5);
			assertEquals(testgamestate.getHintsLeft(), 6);
			assertEquals(testgamestate.getGuessesTaken(), 0);
			assertEquals(testgamestate.getTargetWord(), "banana");
			assertEquals(testgamestate.lettersNotGuessed.size(), 3);
			assertEquals(testgamestate.lettersGuessed.size(), 0);
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
		public void correctGuessesNumberAfterLetterGuessTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			testgamestate.checkLetterGuess('c');
			testgamestate.checkLetterGuess('e');	
			assertEquals(testgamestate.getGuessesTaken(), 2);
			assertEquals(testgamestate.getGuessesLeft(), 3);	
		}
		
		@Test
		public void correctGuessesNumberAfterWordGuessTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			testgamestate.checkWordGuess("cat");
			testgamestate.checkWordGuess("apple");	
			assertEquals(testgamestate.getGuessesTaken(), 2);
			assertEquals(testgamestate.getGuessesLeft(), 3);	
		}
		
		//////////////////////////////////////////////////////////
		
		@Test
		public void correctHintsNumberAfterHintsTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			testgamestate.showHint();
			testgamestate.showHint();
			assertEquals(testgamestate.getHintsLeft(), 4);	
		}
		
		@Test
		public void zeroHintsLeftTest() {
			GameState testgamestate = new GameState("banana", 5, 2);
			testgamestate.showHint();
			testgamestate.showHint();
			testgamestate.showHint();
			testgamestate.showHint();
			assertEquals(testgamestate.getHintsLeft(), 0);	
		}
		
		////////////////////////////////////
		
		@Test
		public void gameLostTest() {
			GameState testgamestate = new GameState("banana", 2, 6);
			testgamestate.checkLetterGuess('c');
			testgamestate.checkLetterGuess('m');
			assertFalse(testgamestate.isGameWon());	
			assertTrue(testgamestate.isGameLost());	
		}
		
		@Test
		public void gameWonTest() {
			GameState testgamestate = new GameState("banana", 10, 6);
			testgamestate.checkLetterGuess('b');
			testgamestate.checkLetterGuess('a');
			testgamestate.checkWordGuess("banana");
			assertTrue(testgamestate.isGameWon());	
			assertFalse(testgamestate.isGameLost());	
		}
		
		//////////////////////////////////////////////////
		
		@Test
		public void correctPrintingOfWordTest() {
			GameState testgamestate = new GameState("banana", 10, 6);
			assertEquals(testgamestate.obscureWord(testgamestate.getTargetWord(), testgamestate.lettersGuessed), "------");	
			testgamestate.checkLetterGuess('b');
			testgamestate.checkLetterGuess('a');
			assertEquals(testgamestate.obscureWord(testgamestate.getTargetWord(), testgamestate.lettersGuessed), "ba-a-a");	
			testgamestate.checkLetterGuess('n');
			assertEquals(testgamestate.obscureWord(testgamestate.getTargetWord(), testgamestate.lettersGuessed), "banana");	

		
		}
		
		
		
		


	}


