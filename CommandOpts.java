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
			if (args[i].equals("--guesses")) {
				setMaxguesses(Integer.parseInt(args[i + 1]));
				i++;
			} else if (args[i].equals("--hints")) {
				setMaxhints(Integer.parseInt(args[i + 1]));
				i++;
			} else
				wordsource = args[i];
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
