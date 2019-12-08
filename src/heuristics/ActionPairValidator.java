package heuristics;

import magicsquare.Square;
import solvers.SolverValidator;
import solvers.ValidatorElements;

public class ActionPairValidator extends SolverValidator {
	protected ValidatorElements<ActionPair> visitedPairs;
	
	public ActionPairValidator(){
		visitedPairs = new ValidatorElements<ActionPair>();
	}
	
	@Override
	public void addVisited(int pos1, int pos2, Square square) {
		visitedPairs.addVisited(new ActionPair(pos1, pos2));
	}

	@Override
	public void reset() {
		visitedPairs = new ValidatorElements<ActionPair>();
	}

	@Override
	public void reset(Square square) {
		reset();
	}

	@Override
	public boolean validSwap(int pos1, int pos2, Square square) {
		return visitedPairs.validSwap(new ActionPair(pos1, pos2));
	}
}
