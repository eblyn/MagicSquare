package experiments;

public class Main {

	/**
	 * @param args: args[0] Parameters input file
	 * args[1] Results output file, if no exist print to Console output
	 * 
	 */
	public static void main(String[] args) {
		if(args.length == 0)
			throw new IllegalArgumentException("No params file argument");
		try{
			String output = args.length > 1 ? args[1] : "";
			Experimentation.runExperiment(args[0], null, output);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
