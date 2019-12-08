package heuristics.lines_columns;

import magicsquare.Adder;
import apcs.Action;

public class DoubleRectificationHeuristic extends LineColumnHeuristic{

	public DoubleRectificationHeuristic(
			double heuristicProbability) {
		super(heuristicProbability);
	}
	
	private Action doubleRectification(Adder adder) {
		DoubleLineRectHeuristic lineRectification = new DoubleLineRectHeuristic(adder);
		if(lineRectification.generateRectification())
			return lineRectification.getAction(adder);
		DoubleColumnRectHeuristic columnRectification = new DoubleColumnRectHeuristic(adder);
		if(columnRectification.generateRectification())
			return columnRectification.getAction(adder);
		return null;
	}
	
	@Override
	public Action getAction(Adder adder) {
		return doubleRectification(adder);
	}
	
	@Override
	public Action getNextProbableAction(Adder adder) {
		return null;
	}
}
