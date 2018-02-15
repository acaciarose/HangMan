
import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.*;
	
public class Tests {

		@Test
		public void normalOperationTest() {
			GameState testgamestate = new GameState("banana", 5, 6);
			assertEquals(testgamestate.wrong, 5);
			assertEquals(testgamestate.h, 6);
			assertEquals(testgamestate.g, 0);
			assertEquals(testgamestate.word, "banana");
			assertEquals(testgamestate.not.size(), 3);
			assertEquals(testgamestate.got.size(), 0);

		}
		
		@Test
		public void zeroGuessesAndHintsTest() {

			GameState testgamestate = new GameState("banana", 0, 0);
			assertEquals(testgamestate,null);

		}
		@Test
		public void nonNumericGuessesAndHintsTest() {

			GameState testgamestate = new GameState("banana", 10000000, 100000000);
			assertEquals(testgamestate,null);

		}
		@Test
		public void negativeIntegerGuessesAndHintsTest() {

			GameState testgamestate = new GameState("banana", -100, -20);
			assertEquals(testgamestate,null);
		}
		
		@Test
		public void optionsTest() {
			String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
			CommandOpts opts = new CommandOpts(args);
			assertEquals(opts.maxguesses, 2);
			assertEquals(opts.maxhints, 4);
			assertEquals(opts.wordsource, "words.txt");
		}


	}


