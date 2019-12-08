package heuristics.lines_columns;

import heuristics.Heuristic;
import heuristics.SwapValues;

import java.util.Iterator;

import solvers.SquareActionFactory;

import magicsquare.Adder;
import apcs.Action;

public class LineColumnHeuristic extends Heuristic {
	protected Iterator<ColumnOrLinePair> pairsIt;
	protected InlineOrIncolumnHeuristic lineOrColumn;
	
	public LineColumnHeuristic(double heuristicProbability) {
		super(heuristicProbability);
	}
	
	private Action getActionFromIt(Adder adder) {
		ColumnOrLinePair pair = null;
		while(!lineOrColumn.hasNext() && pairsIt.hasNext()){
			pair = pairsIt.next();
			lineOrColumn.addValues(pair);
		}
		if(lineOrColumn.hasNext() && pair != null)
			return getAction(lineOrColumn.next(), adder.n());
		return null;
	}
	
	private Action getAction(SwapValues next, int n) {
		return SquareActionFactory.generate(next.getPos1(), next.getPos2(), n*n-1);
	}

	@Override
	protected Action getAction(Adder adder) {
		OrderColumnOrLinePairs pairs = new OrderColumnOrLinePairs(adder);
		pairsIt = pairs.getIterator();
		lineOrColumn = new InlineOrIncolumnHeuristic(adder);
		return getActionFromIt(adder);
	}

	@Override
	public Action getNextProbableAction(Adder adder) {
		return getActionFromIt(adder);
	}
}
