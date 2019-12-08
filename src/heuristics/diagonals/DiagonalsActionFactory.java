package heuristics.diagonals;

import solvers.SquareActionFactory;
import apcs.Action;

public class DiagonalsActionFactory extends SquareActionFactory{

	public DiagonalsActionFactory(int n) {
		super(n);
		this.maxValue = n*n - 1;
	}
	
	public static Action generate(int i, int j, boolean line, int n) {
		int pos1 = (line) ? i*n : i;
		int pos2 = (line) ? j*n : j;
		return generate(pos1, pos2, n*n - 1); 
	}
}
