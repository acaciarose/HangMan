import java.io.File;

//Read in given command line options (no.guesses/no.hints)
public class CommandOpts {
	static final int DEFAULT_MAX_GUESSES = 10;
	static final int DEFAULT_MAX_HINTS = 2;	

	private int maxguesses;
	private int maxhints;

	String wordsource;

	CommandOpts(String[] args) {
		// Default values
		setMaxguesses(DEFAULT_MAX_GUESSES);
		setMaxhints(DEFAULT_MAX_HINTS);
		wordsource = "";
		
		extractOptionsFromCommandLineArgs(args);


	}
		
	// Extract guesses, hints and wordsources from command line args
	void extractOptionsFromCommandLineArgs(String[] args) {		
		for (int i = 0; i < args.length; ++i) {
			System.out.println(args[i]);
			if (args[i].equals("--guesses")) {
				int parsedGuesses = parseGuessesInput(args[i+1]);
				setGuesses(parsedGuesses);	
				if (parsedGuesses != -1) {
				i++;
				}
			} else if (args[i].equals("--hints")) {
				int parsedHints = parseHintsInput(args[i+1]);
				setHints(parsedHints);
				if (parsedHints != -1) {
				i++;
				}
			} else
				if (checkWordSourceFileIsValid(args[i])) {
					wordsource = args[i];
					
				}
		}
		
	}
	
	//Make sure hints input is sensible.
	int parseHintsInput(String hint) {
		try {
			int parsedHint = Integer.parseInt(hint);
			if (parsedHint >= 0) {
				return parsedHint;
			}
			else {
				return -1;
			}
			
		}
		catch (NumberFormatException e) {
			return -1;
		}
		

	
	}
	
	//Make sure the word source file given exists and is a file
	boolean checkWordSourceFileIsValid(String wordsource) {
		File f = new File(wordsource);
		System.out.print(f.exists());
		return (f.isFile());
		
	}
	
	//Make sure guesses input is sensible. 
	int parseGuessesInput(String guess) {
		try {
			int parsedGuess = Integer.parseInt(guess);
			if (parsedGuess >= 0) {
				return parsedGuess;
			}
			else {
				return -1;
			}
			
		}
		catch (NumberFormatException e) {
			return -1;
		}
	
	}
	
	void setGuesses(int guesses) {
		if (guesses == -1) {
			System.out.println("Not a valid guess! Guesses value set to default...");
		}
		
		else {
			setMaxguesses(guesses);
		}
		
	}
	
	void setHints(int hints) {
		if (hints == -1) {
			System.out.println("Not a valid hint! Hint value set to default...");
		}
		
		else {
			setMaxhints(hints);
		}
		
	}

	public int getMaxguesses() {
		return maxguesses;
	}

	public void setMaxguesses(int maxguesses) {
		this.maxguesses = maxguesses;
	}

	public int getMaxhints() {
		return maxhints;
	}

	public void setMaxhints(int maxhints) {
		this.maxhints = maxhints;
	}
}
