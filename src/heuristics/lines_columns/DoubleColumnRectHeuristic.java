package heuristics.lines_columns;

import magicsquare.Adder;
import magicsquare.CellEvaluator;

public class DoubleColumnRectHeuristic extends ColumnRectificationHeuristic {

	public DoubleColumnRectHeuristic(Adder adder) {
		super(adder);
	}

	@Override
	protected boolean setLine(int line) {
		int diference = CellEvaluator.getDiference(adder.getElements().getColumnAdd(column1), adder.n());
		for(int i = line + 1; i < adder.n(); i++) 
			if(validColumnsSwap(line, i, diference)) {
				this.line = i;
				return true;
			}
		return false;
	}
	
	protected boolean validColumnsSwap(int line1, int line2, int diference) {
		int value11 = adder.getSquare().getValues().elementAt(line1, column1);
		int value12 = adder.getSquare().getValues().elementAt(line1, column2);
		int value21 = adder.getSquare().getValues().elementAt(line2, column1);
		int value22 = adder.getSquare().getValues().elementAt(line2, column2);
		if(value11 + value21 - (value12 + value22) == diference)
			return true;
		return false;
	}
}
