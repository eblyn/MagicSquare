package apcs;

import java.util.Vector;

public class RuleCovering {
	protected Vector<Integer> matchTimes;
	protected int totalMatch;
	protected int coveringTime;
	protected int lastMatchs;
	
	public RuleCovering(int coveringTime) {
		this.coveringTime = coveringTime;
		matchTimes = new Vector<Integer>();
	}
	
	public void incrementMatch() {
		lastMatchs++;
		totalMatch++;
	}
	
	public void nextGeneration() {
		matchTimes.add(lastMatchs);
		lastMatchs = 0;
		while(matchTimes.size() > coveringTime) {
			totalMatch -= matchTimes.firstElement();
			matchTimes.remove(0);
		}		
	}

	public int getTotalMatch() {
		return totalMatch;
	}

	public int getCoveringTime() {
		return coveringTime;
	}

	public void restart(int matchFill) {
		matchTimes.removeAllElements();
		totalMatch = lastMatchs = matchFill * coveringTime;
		nextGeneration();
	}	
}
