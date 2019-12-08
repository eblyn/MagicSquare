package solvers;

import magicsquare.Adder;
import magicsquare.Evaluator;
import magicsquare.Square;

public class DiagonalEvaluation extends SolverEvaluation {
	
	public DiagonalEvaluation(Adder adder) {
		super(adder);
	}

	public DiagonalEvaluation(Adder adder, SolverValidator validator) {
		super(adder, validator);
	}
	
	@Override
	public double evaluate(Adder adder) {
		return Evaluator.evaluateDiagonals(adder);
	}
	
	@Override
	protected Square getNewSquare(Adder adder, int pos1, int pos2) {
		Square newSquare = adder.getSquare().clone();
		swap(newSquare, pos1, pos2);
		return newSquare;
	}
	
	protected void swap(Square square, int pos1, int pos2) {
		int n = square.getValues().n();
		int line1 = pos1/n;
		int line2 = pos2/n;
		int column1 = pos1 % n;
		int column2 = pos2 % n;
		swapValues(square, line1, line2, column1, column2);
	}
	
	private void swapValues(Square square, int line1, int line2, int column1, int column2) {
		boolean line = Integer.signum(line1 - line2) == Integer.signum(column1 - column2) || column1 == column2;
		if(square.getSwaper().equalAdd(line1, line2, column1, column2, line))
			square.getSwaper().swapValues(line1, line2, column1, column2, line);
		else if(line) 
			square.getSwaper().swapLines(line1, line2);
		else
			square.getSwaper().swapColumns(column1, column2);
	}
	
	@Override
	protected void swap(Adder adder, int pos1, int pos2) {
		swap(adder.getSquare(), pos1, pos2);
		adder.getElements().addDiagonals(adder.getSquare());
	}
}
