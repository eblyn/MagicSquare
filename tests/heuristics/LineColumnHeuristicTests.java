package heuristics;

import heuristics.lines_columns.LineColumnHeuristic;
import magicsquare.Adder;
import magicsquare.SquareFactory;
import apcs.Action;
import junit.framework.TestCase;

public class LineColumnHeuristicTests extends TestCase {

	public void testGetAction() {
		Heuristic[] heuristic = {new LineColumnHeuristic(1)};
		Adder adder = new Adder(SquareFactory.generateSquare(3));
		HeuristicSolver solver = new HeuristicSolver(adder, heuristic);
		Action nextAction = null;
		for(int i = 0; i < 20; i++) {
			nextAction = solver.getValidAction();
			solver.moveNext(nextAction);
		}
		assertTrue(true);
	}

}
