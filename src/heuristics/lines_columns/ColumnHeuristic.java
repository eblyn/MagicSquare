package heuristics.lines_columns;

import magicsquare.Adder;
import magicsquare.CellEvaluator;

public class ColumnHeuristic {
	protected int minColumn;
	protected int maxColumn;
	protected int line;
	protected double reward;
	protected Adder adder;
	
	public ColumnHeuristic(Adder adder) {
		this.adder = adder;
		reward = minColumn = maxColumn = line = -1;
		setColumns();
		if(minColumn != -1 && maxColumn != -1)
			setLine();
	}
	
	private void setColumns() {
		double minError = 0;
		double maxError = 0;
		for(int i = 0; i < adder.n(); i++) {
			double nextColumnError = CellEvaluator.getDiference(adder.getElements().getColumnAdd(i), adder.n());
			minError = updateMinError(nextColumnError, minError, i);
			maxError = updateMaxError(nextColumnError, maxError, i);
		}
	}
	
	private double updateMinError(double nextColumnError, double minError, int nextColumn) {
		if(nextColumnError < 0 && nextColumnError < minError) {
			minColumn = nextColumn;
			return nextColumnError;
		}
		return minError;
	}
	
	private double updateMaxError(double nextColumnError, double maxError, int nextColumn) {
		if(nextColumnError > 0 && nextColumnError > maxError) {
			maxColumn = nextColumn;
			return nextColumnError;
		}
		return maxError;
	}
	
	private void setLine() {
		double minError = Double.MAX_VALUE;
		double maxColumnError = CellEvaluator.getDiference(adder.getElements().getColumnAdd(maxColumn), adder.n());
		double minColumnError = CellEvaluator.getDiference(adder.getElements().getColumnAdd(minColumn), adder.n());
		for(int i = 0; i < adder.n(); i++) 
			minError = updateLine(i, maxColumnError, minColumnError, minError);
	}
	
	private double updateLine(int index, double maxColumnError, double minColumnError, double minError) {
		double diference = adder.getSquare().getValues().elementAt(index, maxColumn) 
						 - adder.getSquare().getValues().elementAt(index, minColumn);
		double error = Math.abs(maxColumnError - diference) + Math.abs(minColumnError + diference);
		if (error < minError) {
			line = index;
			reward = maxColumnError - minColumnError - error;
			return error;
		}
		return minError;
	}
	
	public double getReward() { return reward; }
	public int getMinColumn() { return minColumn; }
	public int getMaxColumn() { return maxColumn; }
	public int getLine() { return line;	}
}
