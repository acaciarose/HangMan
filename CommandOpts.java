//Read in given command line options (no.guesses/no.hints)
public class CommandOpts {

	public int maxguesses;
	public int maxhints;
	
	String wordsource;
	
	CommandOpts(String[] args) {
		//Default values
		maxguesses = 10;
		maxhints = 2;
		
		wordsource = "";
		
		//Extract guesses, hints and wordsources from command line args
		for(int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guesses")) {
				maxguesses = Integer.parseInt(args[i+1]);
				i++;
			}
			else if (args[i].equals("--hints")) {
				maxhints = Integer.parseInt(args[i+1]);
				i++;
			}
			else wordsource = args[i];
		}
	}
}
