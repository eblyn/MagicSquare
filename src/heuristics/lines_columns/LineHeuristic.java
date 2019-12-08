package heuristics.lines_columns;

import magicsquare.Adder;
import magicsquare.CellEvaluator;

public class LineHeuristic {
	protected int minLine;
	protected int maxLine;
	protected int column;
	protected double reward;
	protected Adder adder;
	
	public LineHeuristic(Adder adder) {
		this.adder = adder;
		reward = minLine = maxLine = column =  -1;
		setLines();
		if(minLine != -1 && maxLine != -1)
			setColumn();
	}
	
	private void setLines() {
		double minError = 0;
		double maxError = 0;
		for(int i = 0; i < adder.n(); i++) {
			double nextLineError = CellEvaluator.getDiference(adder.getElements().getLineAdd(i), adder.n());
			minError = updateMinError(nextLineError, minError, i);
			maxError = updateMaxError(nextLineError, maxError, i);
		}
	}
	
	private double updateMinError(double nextLineError, double minError, int nextLine) {
		if(nextLineError < 0 && nextLineError < minError) {
			minLine = nextLine;
			return nextLineError;
		}
		return minError;
	}
	
	private double updateMaxError(double nextLineError, double maxError, int nextLine) {
		if(nextLineError > 0 && nextLineError > maxError) {
			maxLine = nextLine;
			return nextLineError;
		}
		return maxError;
	}
	
	private void setColumn() {
		double minError = Double.MAX_VALUE;
		double maxLineError = CellEvaluator.getDiference(adder.getElements().getLineAdd(maxLine), adder.n());
		double minLineError = CellEvaluator.getDiference(adder.getElements().getLineAdd(minLine), adder.n());
		for(int i = 0; i < adder.n(); i++) 
			minError = updateColumn(i, maxLineError, minLineError, minError);
	}
	
	private double updateColumn(int index, double maxLineError, double minLineError, double minError) {
		double diference = adder.getSquare().getValues().elementAt(maxLine, index) - adder.getSquare().getValues().elementAt(minLine, index);
		double error = Math.abs(maxLineError - diference) + Math.abs(minLineError + diference);
		if (error < minError) {
			column = index;
			reward = maxLineError - minLineError - error;
			return error;
		}
		return minError;
	}
	
	public int getMinLine() { return minLine; }
	public int getMaxLine() { return maxLine; }
	public int getColumn() { return column;	}
	public double getReward() { return reward; }
}
