package heuristics.lines_columns;

import heuristics.SwapComparator;
import heuristics.SwapValues;

import java.util.TreeSet;

import magicsquare.Adder;

public class InlineOrIncolumnHeuristic {
	protected Adder adder;
	protected TreeSet<SwapValues> values;
	
	public InlineOrIncolumnHeuristic(Adder adder) {
		values = new TreeSet<SwapValues>(new SwapComparator());
		this.adder = adder;
	}
	
	public SwapValues next() {
		SwapValues swap = values.first();
		values.remove(swap);
		return swap;
	}
	
	public void addValues(ColumnOrLinePair pair) {
		int minError = pair.getError();
		for(int i = 0; i < adder.n(); i++) 
			updateColumn(i, minError, pair);
	}
	
	public boolean hasNext() {
		return values.size() > 0;
	}
	
	private void updateColumn(int index, int minError, ColumnOrLinePair pair) {
		int diference = getDiference(index, pair);
		int error = Math.abs(pair.getFirst().getError() - diference) + Math.abs(pair.getSecond().getError() + diference);
		if (error < minError) 
			insertSwap(index, pair, adder.n(), minError - error);
	}
	private void insertSwap(int lineOrColumn, ColumnOrLinePair pair, int n, int reward) {
		int pos1, pos2;
		if(pair.getFirst().isLine()) {
			pos1 = lineOrColumn + pair.getFirst().getPosition() * n;
			pos2 = lineOrColumn + pair.getSecond().getPosition() * n;
		}
		else{
			pos1 = pair.getFirst().getPosition() + lineOrColumn * n;
			pos2 = pair.getSecond().getPosition() + lineOrColumn * n;
		}
		values.add(new SwapValues(pos1, pos2, reward));
	}	
	
	private int getDiference(int index, ColumnOrLinePair pair){
		int first = getPositionValue(pair.getFirst(), index);
		int second = getPositionValue(pair.getSecond(), index);
		return first - second;
	}
	
	private int getPositionValue(ColumnOrLineValue position, int i) {
		if(position.isLine())
			return adder.getSquare().getValues().elementAt(position.getPosition(), i);
		return adder.getSquare().getValues().elementAt(i, position.getPosition());
	}
}