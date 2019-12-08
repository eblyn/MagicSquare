package experiments;

import java.util.Iterator;
import java.util.TreeMap;
import magicsquare.Adder;

public class ExperimentsResults {
	protected Adder bestSolution;
	protected double bestEvaluation;
	protected TreeMap<Integer, Double> results;
	
	public ExperimentsResults(){
		bestSolution = null;
		bestEvaluation = Double.MAX_VALUE;
		results = new TreeMap<Integer, Double>();
	}

	public Adder getBestSolution() {
		return bestSolution;
	}

	public double getBestEvaluation() {
		return bestEvaluation;
	}
	
	public void updateBest(double evaluation, Adder bestLocalSolution, int cycle) {
		bestEvaluation = evaluation;
		bestSolution = bestLocalSolution;
		results.put(cycle, evaluation);
	}
	
	public String toString() {
		String str = "";
		Iterator<Integer> it = results.keySet().iterator();
		while(it.hasNext()){
			Integer key = it.next();
			str += key + " " + results.get(key) + "\n";
		}
		return str;
	}
	
	public String lastResult() {
		int key = results.lastKey();
		return key + "," + results.get(key);
	}
	
	public int lastCycle() {
		return results.lastKey();
	}
}
