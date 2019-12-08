package heuristics.diagonals;

import solvers.SquareActionFactory;
import apcs.Action;
import magicsquare.Adder;
import magicsquare.CellEvaluator;
import magicsquare.Evaluator;
import magicsquare.Values;

public class Diagonal0Rectification {
	protected int i, j;
	protected Adder adder;
	protected boolean line;
	
	public Diagonal0Rectification(Adder adder){
		this.adder = adder;
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

	public boolean generateRectification() {
		if(Evaluator.error(adder.n(), adder.getElements().getDiagonalAdd(0)) == 0)
			return false;
		for(i = 0; i < adder.n() - 1; i++)
			if(setPositions())
				return true;
		return false;
	}

	private boolean setPositions() {
		for(j = i + 1; j < adder.n(); j++) {
			if(validLineSwap(i,j)) {
				line = true;
				return true;
			}
			else if(validColumnSwap(i, j)){
				line = false;
				return true;
			}
		}
		return false;
	}

	private boolean validLineSwap(int i, int j) {
		Values square = adder.getSquare().getValues();
		int line1Add = square.elementAt(i,i) + square.elementAt(i,j);
		int line2Add = square.elementAt(j,i) + square.elementAt(j,j);
		if(line1Add == line2Add)
			return validSwap(square, i, j);			
		return false;
	}
	
	private boolean validSwap(Values square, int i, int j){
		int diagonalDiference = CellEvaluator.getDiference(adder.getElements().getDiagonalAdd(0), adder.n());
		int diagonalAdd = square.elementAt(i,i) + square.elementAt(j,j);
		int swapAdd = square.elementAt(i,j) + square.elementAt(j,i);
		if(diagonalAdd - swapAdd == diagonalDiference)
			return true;
		return false;
	}
	
	public Action getAction(Adder adder) {
		int pos1 = (line ? i : j) * adder.n() + (line ? j : i);
		int pos2 = j * adder.n() + j;
		return SquareActionFactory.generate(pos1, pos2, adder.n() * adder.n() - 1);
	}
	
	private boolean validColumnSwap(int i, int j) {
		Values square = adder.getSquare().getValues();
		int column1Add = square.elementAt(i,i) + square.elementAt(j,i);
		int column2Add = square.elementAt(i,j) + square.elementAt(j,j);
		if(column1Add == column2Add)
			return validSwap(square, i, j);			
		return false;
	}
	
	public void swapValues() {
		int x = line ? i : j;
		int y = line ? j : i;
		adder.swap(x * adder.n() + y, j * adder.n() + j);
		adder.swap(i * adder.n() + i, y * adder.n() + x);
	}
}
