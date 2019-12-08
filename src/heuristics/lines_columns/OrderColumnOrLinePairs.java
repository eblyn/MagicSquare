package heuristics.lines_columns;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;
import magicsquare.Adder;
import magicsquare.CellEvaluator;

public class OrderColumnOrLinePairs {
	protected TreeSet<ColumnOrLinePair> values; 
	
	public OrderColumnOrLinePairs(Adder adder) {
		fillValues(adder);
	}
	
	private void fillValues(Adder adder){
		values = new TreeSet<ColumnOrLinePair>(new ColumnOrLinePairComparator());
		Vector<ColumnOrLineValue> linePositions = getLinePositions(adder);
		Vector<ColumnOrLineValue> columnPositions = getColumnPositions(adder);
		for(int i = 0 ; i < linePositions.size() - 1; i++) 
			insertValues(i, linePositions);
		for(int i = 0 ; i < columnPositions.size() - 1; i++) 
			insertValues(i, columnPositions);
	}
	
	private Vector<ColumnOrLineValue> getLinePositions(Adder adder) {
		Vector<ColumnOrLineValue> linePositions = new Vector<ColumnOrLineValue>();
		for(int i = 0; i < adder.n(); i++) {
			int error = CellEvaluator.getDiference(adder.getElements().getLineAdd(i), adder.n());
			if(error != 0)
				linePositions.add(new ColumnOrLineValue(i, error, true));
		}
		return linePositions;
	}
	
	private Vector<ColumnOrLineValue> getColumnPositions(Adder adder) {
		Vector<ColumnOrLineValue> columnPositions = new Vector<ColumnOrLineValue>();
		for(int i = 0; i < adder.n(); i++) {
			int error = CellEvaluator.getDiference(adder.getElements().getColumnAdd(i), adder.n());
			if(error != 0)
				columnPositions.add(new ColumnOrLineValue(i, error, false));
		}
		return columnPositions;
	}
	
	private void insertValues(int i, Vector<ColumnOrLineValue> positions) {
		ColumnOrLineValue valueI = positions.elementAt(i);
		for(int j = i+1; j < positions.size(); j++){
			ColumnOrLineValue valueJ = positions.elementAt(j);
			if(Integer.signum(valueI.getError()) != Integer.signum(valueJ.getError()))
				values.add(new ColumnOrLinePair(valueI, valueJ));
		}
	}
	
	public ColumnOrLinePair getBestPair() {
		return values.first();
	}
	
	public Iterator<ColumnOrLinePair> getIterator() {
		return values.iterator();
	}
}
