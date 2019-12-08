package heuristics.diagonals;

import solvers.SquareActionFactory;
import magicsquare.Adder;
import magicsquare.CellEvaluator;
import magicsquare.Evaluator;
import magicsquare.Values;
import apcs.Action;

public class Diagonal1Rectification {
	protected int i, j;
	protected Adder adder;
	protected boolean line;
	
	public Diagonal1Rectification(Adder adder){
		this.adder = adder;
	}
	
	public boolean generateRectification() {
		if(Evaluator.error(adder.n(), adder.getElements().getDiagonalAdd(1)) == 0)
			return false;
		for(i = 0; i < adder.n() - 1; i++)
			if(setPositions())
				return true;
		return false;
	}

	private boolean setPositions() {
		for(j = i + 1; j < adder.n(); j++) {
			if(validLineSwap(i,j, adder)) {
				line = true;
				return true;
			}
			else if(validColumnSwap(i, j, adder)){
				line = false;
				return true;
			}
		}
		return false;
	}

	public static boolean validLineSwap(int i, int j, Adder adder) {
		Values square = adder.getSquare().getValues();
		int line1Add = square.elementAt(i,adder.n() - i - 1) + square.elementAt(i,adder.n() - j - 1);
		int line2Add = square.elementAt(j,adder.n() - i - 1) + square.elementAt(j,adder.n() - j - 1);
		if(line1Add == line2Add){
			int diagonalDiference = CellEvaluator.getDiference(adder.getElements().getDiagonalAdd(0), adder.n());
			return validSwap(square, i, j, diagonalDiference);	
		}
		return false;
	}
	
	private static boolean validSwap(Values square, int i, int j, int diagonalDiference){
		int diagonalAdd = square.elementAt(i,square.n() - i - 1) + square.elementAt(j,square.n() - j - 1);
		int swapAdd = square.elementAt(i,square.n() - j - 1) + square.elementAt(j,square.n() - i - 1);
		if(diagonalAdd - swapAdd == diagonalDiference)
			return true;
		return false;
	}
	
	public Action getAction(Adder adder) {
		int x = line ? i : j;
		int pos1 = x * adder.n() + adder.n() - x - 1;
		int pos2 = j * adder.n() + adder.n() - i - 1;
		return SquareActionFactory.generate(pos1, pos2, adder.n() * adder.n() - 1);
	}
	
	public static boolean validColumnSwap(int i, int j, Adder adder) {
		Values square = adder.getSquare().getValues();
		int column1Add = square.elementAt(i,adder.n() - i - 1) + square.elementAt(j,adder.n() - i - 1);
		int column2Add = square.elementAt(i,adder.n() - j - 1) + square.elementAt(j,adder.n() - j - 1);
		if(column1Add == column2Add){
			int diagonalDiference = CellEvaluator.getDiference(adder.getElements().getDiagonalAdd(0), adder.n());
			return validSwap(square, i, j, diagonalDiference);	
		}
		return false;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public boolean isLine() {
		return line;
	}
	
	public void swapValues() {
		int x = line ? i : j;
		adder.swap(x * adder.n() + adder.n() - x - 1, j * adder.n() + adder.n() - i - 1);
		x = i + j - x;
		adder.swap(x * adder.n() + adder.n() - x - 1, i * adder.n() + adder.n() - j - 1);
	}
}
