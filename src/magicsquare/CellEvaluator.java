package magicsquare;

public class CellEvaluator {
	public static int cellError(int cell, Adder adder) {
		int x = adder.getSquare().getX(cell);
		int y = adder.getSquare().getY(cell);
		int error = getCellLineColumnError(adder, x, y);
		error += getCellDiagonalsError(adder, x, y);
		return Math.abs(error);
	}

	public static int getCellLineColumnError(Adder adder, int x, int y) {
		int n = adder.n();
		int error = getDiference(adder.getElements().getLineAdd(x), n) 
					+ getDiference(adder.getElements().getColumnAdd(y), n);
		return error;
	}
	
	public static int getCellDiagonalsError(Adder adder, int x, int y) {
		int n = adder.n();
		int error = 0;
		if (x == y)  
			error += getDiference(adder.getElements().getDiagonalAdd(0), n);
		if (x + y == n - 1) 	
			error += getDiference(adder.getElements().getDiagonalAdd(1), n);
		return error;
	}
	
	public static int getDiference(int value, int n) {
		return value - Evaluator.magicNumber(n);
	}
}
