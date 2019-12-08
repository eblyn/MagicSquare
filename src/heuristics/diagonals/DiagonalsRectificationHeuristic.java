package heuristics.diagonals;

import heuristics.Heuristic;
import apcs.Action;
import magicsquare.Adder;

public class DiagonalsRectificationHeuristic extends Heuristic {

	public DiagonalsRectificationHeuristic(double heuristicProbability) {
		super(heuristicProbability);
	}

	public DiagonalsRectificationHeuristic() {
		super(1);
	}

	private Action diagonalRectification(Adder adder) {
		Diagonal0Rectification diagonalRectification = new Diagonal0Rectification(adder);
		if(diagonalRectification.generateRectification())
			return diagonalRectification.getAction(adder);
		Diagonal1Rectification diagonal1Rectification = new Diagonal1Rectification(adder);
		if(diagonal1Rectification.generateRectification())
			return diagonal1Rectification.getAction(adder);
		return null;
	}
	
	@Override
	public Action getAction(Adder adder) {
		return diagonalRectification(adder);
	}

	@Override
	public Action getNextProbableAction(Adder adder) {
		// TODO Auto-generated method stub
		return null;
	}
}
