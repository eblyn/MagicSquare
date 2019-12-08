package heuristics.diagonals;

import magicsquare.Adder;
import magicsquare.Evaluator;
import magicsquare.Values;

public class SwapEvaluator {
	
	public static double LineErrorDiag1(int i, int j, Adder adder) {
		int addDiagonal = adder.getSquare().getValues().elementAt(i, adder.n() - i - 1)
						+ adder.getSquare().getValues().elementAt(j, adder.n() - j - 1);
		int swapAdd = adder.getSquare().getValues().elementAt(i, adder.n() - j - 1)
					+ adder.getSquare().getValues().elementAt(j, adder.n() - i - 1);
		double error = adder.getElements().getDiagonalAdd(1) + swapAdd - addDiagonal;
		return Math.abs(error - Evaluator.magicNumber(adder.n()));
	}
	
	public static double lineErrorDiag0(int i, int j, Adder adder) {
		int addDiagonal = adder.getSquare().getValues().elementAt(i, i)
						+ adder.getSquare().getValues().elementAt(j, j);
		int swapAdd = adder.getSquare().getValues().elementAt(i, j)
					+ adder.getSquare().getValues().elementAt(j, i);
		double error = adder.getElements().getDiagonalAdd(0) + swapAdd - addDiagonal;
		return Math.abs(error - Evaluator.magicNumber(adder.n()));
	}

	public static boolean validSwap(Adder adder, int line1, int line2, int column1, int column2, boolean line) {
		Values square = adder.getSquare().getValues();
		int firstAdd = square.elementAt(line1,column1) + 
				(line ? square.elementAt(line1,column2) : square.elementAt(line2,column1));
		int secondAdd = (line ? square.elementAt(line2,column1) : square.elementAt(line1,column2))
				+ square.elementAt(line2,column2);
		if(firstAdd == secondAdd)
			return true;
		return false;
	}
	
	//TODO este método se puede mejorar en tiempo, se puede hacer O(1)
	//El swapper también debería hacerse dentro de Adder además del de square
	public static double swapError(Adder adder, int line1, int line2, int column1, int column2, boolean line) {
		swapAdderValues(adder, line1, line2, column1, column2, line);
		double newError = Evaluator.evaluateDiagonals(adder);
		swapAdderValues(adder, line1, line2, column1, column2, line);
		return newError;
	}
	
	private static void swapAdderValues(Adder adder, int line1, int line2, int column1, int column2, boolean line) {
		adder.getSquare().getSwaper().swapValues(line1, line2, column1, column2, line);
		adder.getElements().addDiagonals(adder.getSquare());
	}
}
