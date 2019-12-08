package magicsquare;

public class Evaluator {
	
	public static int magicNumber(int n) {
		return (n * (n*n + 1)) / 2;
	}
	
	public static int evaluate(Adder adder) {
		int adition = evaluateLineColumn(adder);
		adition += evaluateDiagonals(adder);		
		return adition;
	}

	public static int error(int n, int adition) {
		return Math.abs(adition - magicNumber(n));
	}
	
	public static int evaluateLineColumn(Adder adder) {
		int adition = 0;
		int n = adder.n();
		for(int i = 0; i < n; i++)
			adition += error(n, adder.getElements().getLineAdd(i)) 
					+ error(n, adder.getElements().getColumnAdd(i));	
		return adition;
	}
	
	public static int evaluateDiagonals(Adder adder) {
		int n = adder.n();
		int adition = 0;
		adition += error(n, adder.getElements().getDiagonalAdd(0)) 
				+ error(n, adder.getElements().getDiagonalAdd(1));		
		return adition;
	}
	
	public static int getLCCellErrorDifference(Adder adder, int x, int y, int contribution) {
		int lineAdd = adder.getElements().getLineAdd(x);
		int difference = getErrorDifference(lineAdd , contribution, adder.n());
		int columnAdd = adder.getElements().getColumnAdd(y);
		difference += getErrorDifference(columnAdd, contribution, adder.n());
		return difference;
	}
	
	public static int getErrorDifference(int actualAdd, int contribution, int n) {
		int actualError = error(n, actualAdd);
		int newError = error(n, actualAdd + contribution);
		return actualError- newError;
	}
	
	public static int getDiagCellErrorDifference(Adder adder, int x, int y, int contribution) {
		int difference = 0;
		if(x == y) 
			difference += getDiagDiff(adder, 0, contribution);
		if(x + y == adder.n() - 1)
			difference += getDiagDiff(adder, 1, contribution);
		return difference;		
	}
	
	private static int getDiagDiff(Adder adder,int diagonal, int contribution) {
		int diagAdd = adder.getElements().getDiagonalAdd(diagonal);
		return getErrorDifference(diagAdd, contribution, adder.n());
	}
	
	private static int getCellErrorDifference(Adder adder, int x, int y, int contribution) {
		int difference = getLCCellErrorDifference(adder, x, y, contribution);
		difference += getDiagCellErrorDifference(adder, x, y, contribution);
		return difference;
	}
	
	public static int swapErrorDifference(Adder adder, int pos1, int pos2) {
		int value1 = adder.getSquare().elementAt(pos1);
		int value2 = adder.getSquare().elementAt(pos2);
		Square s =  adder.getSquare();
		int difference = getCellErrorDifference(adder, s.getX(pos1), s.getY(pos1), value2 - value1);
		difference += getCellErrorDifference(adder, s.getX(pos2), s.getY(pos2), value1 - value2);
		return difference;	
	}
	
	public static int swapLCErrorDiff(Adder adder, int pos1, int pos2) {
		int value1 = adder.getSquare().elementAt(pos1);
		int value2 = adder.getSquare().elementAt(pos2);
		Square s =  adder.getSquare();
		int difference = getLCCellErrorDifference(adder, s.getX(pos1), s.getY(pos1), value2 - value1);
		difference += getLCCellErrorDifference(adder, s.getX(pos2), s.getY(pos2), value1 - value2);
		return difference;
	}
	
	/*
	 * int value1 = square.elementAt(pos1);
		int value2 = square.elementAt(pos2);
		elements.changeCell(value2 - value1, square.getX(pos1), square.getY(pos1));
		elements.changeCell(value1 - value2, square.getX(pos2), square.getY(pos2));
		public void changeCell(int contribution, int x, int y){
		linesAdd[x] += contribution;
		columnsAdd[y] += contribution;
		if(x == y)
			diagonalsAdd[0] += contribution;
		if(x + y == n() - 1)
			diagonalsAdd[1] += contribution;
		
	}*/
	
}
