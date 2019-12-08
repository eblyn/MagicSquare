package solvers;

import magicsquare.Square;

public class SolverSolutions {
	protected Square first;
	protected Square best;
	protected double firstEvaluation;
	protected double bestEvaluation;
	
	public SolverSolutions(Square first, double firstEvaluation2) {
		this.first = first.clone();
		this.firstEvaluation = firstEvaluation2;
		bestEvaluation = -1;
	}

	public Square getFirst() {
		return first;
	}

	public Square getBest() {
		return best;
	}

	public double getFirstEvaluation() {
		return firstEvaluation;
	}

	public double getBestEvaluation() {
		return bestEvaluation;
	}
	
	public double updateBest(double newEvaluation, Square square){
		if(newEvaluation > bestEvaluation && bestEvaluation != -1) 
			return 0;
		if(bestEvaluation == -1 || bestEvaluation > firstEvaluation)
			bestEvaluation = (newEvaluation <= firstEvaluation) ? firstEvaluation : newEvaluation;
		double evaluation = bestEvaluation - newEvaluation;
		bestEvaluation = newEvaluation;
		best = square.clone();
		return evaluation;
	}
	
	public void reset(){
		bestEvaluation = -1;
		best = null;
	}
	
	public void reset(Square first, double firstEvaluation) {
		this.first = first;
		this.firstEvaluation = firstEvaluation;
		reset();
	}
}
