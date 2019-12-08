package heuristics;

import magicsquare.Adder;
import apcs.Action;

public abstract class Heuristic {
	protected double heuristicProbability;
	
	public Heuristic(double heuristicProbability) {
		this.heuristicProbability = heuristicProbability;
	}
	
	public Action getProbableAction(Adder adder, double randomValue) {
		if(randomValue < heuristicProbability)
			return getAction(adder);
		return null;
	}
	
	protected abstract Action getAction(Adder adder);
	
	public double getProbability() {
		return heuristicProbability;
	}
	
	public abstract Action getNextProbableAction(Adder adder);
}
