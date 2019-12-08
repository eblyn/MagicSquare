package heuristics.lines_columns;

import magicsquare.Adder;
import magicsquare.CellEvaluator;

public class DoubleLineRectHeuristic extends LineRectificationHeuristic{
	
	public DoubleLineRectHeuristic(Adder adder) {
		super(adder);
	}

	@Override
	protected boolean setColumn(int column) {
		int diference = CellEvaluator.getDiference(adder.getElements().getLineAdd(line1), adder.n());
		for(int i = column + 1; i < adder.n(); i++) 
			if(validColumnsSwap(column, i, diference)) {
				this.column = i;
				return true;
			}
		return false;
	}
	
	protected boolean validColumnsSwap(int column1, int column2, int diference) {
		int value11 = adder.getSquare().getValues().elementAt(line1, column1);
		int value12 = adder.getSquare().getValues().elementAt(line1, column2);
		int value21 = adder.getSquare().getValues().elementAt(line2, column1);
		int value22 = adder.getSquare().getValues().elementAt(line2, column2);
		if(value11 + value12 - (value21 + value22) == diference)
			return true;
		return false;
	}
}
