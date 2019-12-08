package heuristics.lines_columns;

import java.util.Comparator;

public class ColumnOrLinePairComparator implements Comparator<ColumnOrLinePair> {

	@Override
	public int compare(ColumnOrLinePair arg0, ColumnOrLinePair arg1) {
		if(arg0.equals(arg1))
			return 0;
		if(arg0.getDiference() == arg1.getDiference())
			return compareIfEqualDiference(arg0, arg1);
		return (arg0.getDiference() < arg1.getDiference()) ? -1 : 1;
	}
	
	private int compareIfEqualDiference(ColumnOrLinePair arg0, ColumnOrLinePair arg1) {
		if(Math.abs(arg0.getError()) == Math.abs(arg1.getError()))
			return compareIfEqualError(arg0, arg1);
		return (Math.abs(arg0.getError()) > Math.abs(arg1.getError())) ? -1 : 1;
	}
	
	private int compareIfEqualError(ColumnOrLinePair arg0, ColumnOrLinePair arg1) {
		if(arg0.getMaxPos() == arg1.getMaxPos())
			return compareIfEqualMaxPos(arg0, arg1);
		return (arg0.getMaxPos() < arg1.getMaxPos()) ? -1 : 1;
	}
	
	private int compareIfEqualMaxPos(ColumnOrLinePair arg0, ColumnOrLinePair arg1) {
		if(arg0.getMinPos() == arg1.getMinPos())
			return compareIfEqualMinPos(arg0, arg1);
		return (arg0.getMinPos() < arg1.getMinPos()) ? -1 : 1;
	}
	
	private int compareIfEqualMinPos(ColumnOrLinePair arg0, ColumnOrLinePair arg1) {
		if(arg0.getFirst().isLine() == arg1.getFirst().isLine())
			return 0;
		return arg0.getFirst().isLine() ? -1 : 1;
	}
}
