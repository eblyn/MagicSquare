package solvers;

import magicsquare.Square;

public class SquareValidator extends SolverValidator {
	protected ValidatorElements<Square> visitedSquares;
	
	public SquareValidator(Square firstSquare) {
		visitedSquares = new ValidatorElements<Square>(firstSquare);
	}
	
	@Override
	public void addVisited(int pos1, int pos2, Square square) {
		visitedSquares.addVisited(square);
	}

	@Override
	public void reset() {
		reset(visitedSquares.getElement(0));
	}

	@Override
	public void reset(Square firstSquare) {
		visitedSquares = new ValidatorElements<Square>(firstSquare);
	}

	@Override
	public boolean validSwap(int pos1, int pos2, Square square) {
		return visitedSquares.validSwap(square);
	}

}
