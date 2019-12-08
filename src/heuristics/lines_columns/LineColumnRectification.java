package heuristics.lines_columns;

import heuristics.Heuristic;
import magicsquare.Adder;
import apcs.Action;

public class LineColumnRectification extends Heuristic{

	public LineColumnRectification(double heuristicProbability) {
		super(heuristicProbability);
	}

	public LineColumnRectification() {
		super(1);
	}
	
	private Action simpleRectification(Adder adder) {
		LineRectificationHeuristic lineRectification = new LineRectificationHeuristic(adder);
		if(lineRectification.generateRectification())
			return lineRectification.getAction(adder);
		ColumnRectificationHeuristic columnRectification = new ColumnRectificationHeuristic(adder);
		if(columnRectification.generateRectification())
			return columnRectification.getAction(adder);
		return null;
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
		Action action = simpleRectification(adder);
		if(action == null)
			return doubleRectification(adder);
		return action;
	}

	@Override
	public Action getNextProbableAction(Adder adder) {
		// TODO Auto-generated method stub
		return null;
	}
}
