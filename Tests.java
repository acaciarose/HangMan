
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class Tests {

	///////////////////////////////////////////////////////////
	//Game State Tests - game state creation with various parameters.
	///////////////////////////////////////////////////////////
	
	@Test
	public void gameStateTest() {
		GameState testgamestate = new GameState();
		testgamestate.initialiseGameState("banana", 5, 6);
		assertEquals(testgamestate.getGuessesLeft(), 5);
		assertEquals(testgamestate.getHintsLeft(), 6);
		assertEquals(testgamestate.getGuessesTaken(), 0);
		assertEquals(testgamestate.getTargetWord(), "banana");
		assertEquals(testgamestate.lettersNotGuessed.size(), 3);
		assertEquals(testgamestate.lettersGuessed.size(), 0);
	}

	@Test
	public void zeroGuessesAndHintsGameStateTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 0, 0);
		assertNull(testgamestate);

	}
	@Test
	public void tooLargeGuessesAndHintsGameStateTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 10000000, 100000000);
		assertNull(testgamestate);

	}
	@Test
	public void negativeIntegerGuessesAndHintsGameStateTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", -100, -20);
		assertNull(testgamestate);
	}
	
	@Test
	public void blankWordGameStateTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("", 5, 5);
		assertNull(testgamestate);
	}
	
	@Test
	public void nonAlphaWordGameStateTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("6", 5, 5);
		assertNull(testgamestate);
	}
	


	///////////////////////////////////////////////////////////
	//Guessing tests - guessing words and letters and checking correct guess/hint numbers 
	///////////////////////////////////////////////////////////
	@Test
	public void guessLettersTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 5, 6);
		assertFalse(testgamestate.checkLetterGuess('c'));
		assertTrue(testgamestate.checkLetterGuess('b'));		
	}
	@Test
	public void guessWordsTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 5, 6);
		assertFalse(testgamestate.checkWordGuess("cat"));
		assertTrue(testgamestate.checkWordGuess("banana"));		
	}
	@Test
	public void correctHintsNumberAfterGuessTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 5, 6);
		testgamestate.checkLetterGuess('c');
		testgamestate.checkLetterGuess('e');	
		assertEquals(testgamestate.getHintsLeft(), 6);

	}
	@Test
	public void correctGuessesNumberAfterLetterGuessTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 5, 6);
		testgamestate.checkLetterGuess('c');
		testgamestate.checkLetterGuess('e');	
		assertEquals(testgamestate.getGuessesTaken(), 2);
		assertEquals(testgamestate.getGuessesLeft(), 3);	
	}

	@Test
	public void correctGuessesNumberAfterWordGuessTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 5, 6);
		testgamestate.checkWordGuess("cat");
		testgamestate.checkWordGuess("apple");	
		assertEquals(testgamestate.getGuessesTaken(), 2);
		assertEquals(testgamestate.getGuessesLeft(), 3);	
	}

	///////////////////////////////////////////////////////////
	//Hint tests - checking correct hint behaviour (zero hints, decrementing hints)
	///////////////////////////////////////////////////////////

	@Test
	public void correctHintsNumberAfterHintsTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 5, 6);
		testgamestate.showHint();
		testgamestate.showHint();
		assertEquals(testgamestate.getHintsLeft(), 4);	
	}

	@Test
	public void zeroHintsLeftTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 5, 2);
		testgamestate.showHint();
		testgamestate.showHint();
		testgamestate.showHint();
		testgamestate.showHint();
		assertEquals(testgamestate.getHintsLeft(), 0);	
	}

	///////////////////////////////////////////////////////////
	//Game Loss and Win Tests - win and loss conditions
	///////////////////////////////////////////////////////////

	@Test
	public void gameLostTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 2, 6);
		testgamestate.checkLetterGuess('c');
		testgamestate.checkLetterGuess('m');
		assertFalse(testgamestate.isGameWon());	
		assertTrue(testgamestate.isGameLost());	
	}

	@Test
	public void gameWonTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 10, 6);
		testgamestate.checkLetterGuess('b');
		testgamestate.checkLetterGuess('a');
		testgamestate.checkWordGuess("banana");
		assertTrue(testgamestate.isGameWon());	
		assertFalse(testgamestate.isGameLost());	
	}

	///////////////////////////////////////////////////////////
	//Word Printing Tests - check words are correctly obscured
	///////////////////////////////////////////////////////////

	@Test
	public void correctPrintingOfWordTest() {
		GameState testgamestate = new GameState();
		testgamestate = testgamestate.initialiseGameState("banana", 10, 6);
		assertEquals(testgamestate.obscureWord(testgamestate.getTargetWord(), testgamestate.lettersGuessed), "------");	
		testgamestate.checkLetterGuess('b');
		testgamestate.checkLetterGuess('a');
		assertEquals(testgamestate.obscureWord(testgamestate.getTargetWord(), testgamestate.lettersGuessed), "ba-a-a");	
		testgamestate.checkLetterGuess('n');
		assertEquals(testgamestate.obscureWord(testgamestate.getTargetWord(), testgamestate.lettersGuessed), "banana");	


	}

	///////////////////////////////////////////////////////////
	//Command Line Tests - checking for correct extraction of parameters from command line
	///////////////////////////////////////////////////////////

	@Test
	public void commandLineArgsExtractionTest() {
		String[] testargs = {"--guesses", "5", "--hints", "6", "words.txt"};
		CommandLineOptions userOptions = new CommandLineOptions(testargs);
		assertEquals(userOptions.getMaxguesses(), 5);
		assertEquals(userOptions.getMaxhints(), 6);
		assertEquals(userOptions.wordsource, "words.txt");

	}

	@Test
	public void commandLineArgsOrderExtractionTest() {
		String[] testargs = {"--guesses", "5", "words.txt", "--hints", "6" };
		CommandLineOptions userOptions = new CommandLineOptions(testargs);
		assertEquals(userOptions.getMaxguesses(), 5);
		assertEquals(userOptions.getMaxhints(), 6);
		assertEquals(userOptions.wordsource, "words.txt");

	}

	@Test
	public void commandLineArgsExtractionMissingValuesTest() {
		String[] testargs = {"--guesses", "5", "--hints", "words.txt"};
		CommandLineOptions userOptions = new CommandLineOptions(testargs);
		assertEquals(userOptions.getMaxguesses(), 5);
		assertEquals(userOptions.getMaxhints(), 2); //default hints number
		assertEquals(userOptions.wordsource, "words.txt");

	}

	@Test
	public void commandLineArgsExtractionExtraValuesTest() {
		String[] testargs = {"--guesses", "5", "9", "--hints", "6", "7", "words.txt"};
		CommandLineOptions userOptions = new CommandLineOptions(testargs);
		assertEquals(userOptions.getMaxguesses(), 5);
		assertEquals(userOptions.getMaxhints(), 6); //default hints number
		assertEquals(userOptions.wordsource, "words.txt");

	}

	@Test
	public void commandLineArgsExtractionNoValuesTest() {
		String[] testargs = {};
		CommandLineOptions userOptions = new CommandLineOptions(testargs);
		assertEquals(userOptions.getMaxguesses(), 10); //default guesses number
		assertEquals(userOptions.getMaxhints(), 2); //default hints number
		assertEquals(userOptions.wordsource, "");

	}

	///////////////////////////////////////////////////////////
	//Word Source Tests - checking correct reading of word source files
	///////////////////////////////////////////////////////////
	@Test
	public void wordSourceDuplicateTest() {
		WordsChooser wc = new WordsChooser();
		ArrayList test = new ArrayList<String>(Arrays.asList("word", "word", "word", "word"));

		assertEquals(wc.readWordsFromSourceFile("words.txt"), test); //default guesses number

	}
	
	@Test
	public void wordSourceImageFileTest() {
		WordsChooser wc = new WordsChooser();

		assertNull(wc.readWordsFromSourceFile("cursor.png")); //default guesses number

	}
	
	@Test
	public void wordSourceBlankTest() {
		WordsChooser wc = new WordsChooser();

		assertNull(wc.readWordsFromSourceFile("blank.txt")); //default guesses number

	}
	
	@Test
	public void wordSourceNonAlphabeticTest() {
		WordsChooser wc = new WordsChooser();

		assertNull(wc.readWordsFromSourceFile("nonalphabetic.txt")); //default guesses number

	}
	
	@Test
	public void wordSourceCorruptedTextTest() {
		WordsChooser wc = new WordsChooser();

		assertNull(wc.readWordsFromSourceFile("symbols.txt")); //default guesses number

	}
	
	@Test
	public void wordSourceStrangeFormatTest() {
		WordsChooser wc = new WordsChooser();

		ArrayList test = new ArrayList<String>(Arrays.asList("cat", "dog", "fish"));

		assertEquals(wc.readWordsFromSourceFile("format.txt"), test); //default guesses number

	}
	
	@Test
	public void wordSourceOneWordTest() {
		WordsChooser wc = new WordsChooser();

		ArrayList test = new ArrayList<String>(Arrays.asList("word"));

		assertEquals(wc.readWordsFromSourceFile("oneword.txt"), test); //default guesses number

	}
	

	///////////////////////////////////////////////////////////
	//Word Choosing Tests - checking correct choice of words from word lists/categories
	///////////////////////////////////////////////////////////

	@Test
	public void wordChooseScottishAreasTest() {
		WordsChooser wc = new WordsChooser();
		String chosen = wc.getRandomWordInCategory(1);

		assertTrue(Arrays.asList(wc.getScottishAreasWordList()).contains(chosen) ); 

	}
	
	@Test
	public void wordChooseEuropeanCountriesTest() {
		WordsChooser wc = new WordsChooser();
		String chosen = wc.getRandomWordInCategory(2);

		assertTrue(Arrays.asList(wc.getEuropeanCountriesWordList()).contains(chosen) ); 

	}
	
	@Test
	public void wordChooseScottishTownsWordListTest() {
		WordsChooser wc = new WordsChooser();
		String chosen = wc.getRandomWordInCategory(3);

		assertTrue(Arrays.asList(wc.getScottishTownsWordList()).contains(chosen) ); 

	}
	
	@Test
	public void wordChooseIncorrectOptionWordListTest() {
		WordsChooser wc = new WordsChooser();
		String chosen = wc.getRandomWordInCategory(10);

		assertEquals(chosen, ""); 

	}
	
	@Test
	public void wordChooseSourceWordListTest() {
		WordsChooser wc = new WordsChooser();
		ArrayList test = new ArrayList<String>(Arrays.asList("word", "word", "word", "word"));

		assertTrue(test.contains(wc.getRandomWordFromSourceFile("words.txt"))); //default guesses number

	}
	
	@Test
	public void wordChooseInvalidSourceWordListTest() {
		WordsChooser wc = new WordsChooser();

		assertNull(wc.getRandomWordFromSourceFile("symbols.txt")); //default guesses number

	}
	
	@Test
	public void wordChooseNonExistentSourceWordListTest() {
		WordsChooser wc = new WordsChooser();

		assertNull(wc.getRandomWordFromSourceFile("doesntexist.txt")); //default guesses number

	}
	


}
	

