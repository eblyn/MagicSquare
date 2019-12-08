package heuristics;

import apcs.Action;
import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.SquareFactory;
import magicsquare.Values;
import heuristics.lines_columns.DoubleColumnRectHeuristic;
import heuristics.lines_columns.DoubleLineRectHeuristic;
import heuristics.lines_columns.DoubleRectificationHeuristic;
import junit.framework.TestCase;

public class DoubleRectHeuristicTest extends TestCase {

	/**
	 * 	2	3	8	-2
		9	5	1	0
		4	7	6	2
		0	0	0

	 */
	
	public void testLineGenerateRectification() {
		int[] sqrArr = {2,3,8,9,5,1,4,7,6};
		Adder adder = new Adder(new Square(new Values(sqrArr))); 
		DoubleLineRectHeuristic heuristic = new DoubleLineRectHeuristic(adder);
		assertTrue(heuristic.generateRectification());
	}
	
	/**
	 * 	2	3	8	-2
		9	5	1	0
		4	7	6	2
		0	0	0

	 */
	
	public void testLineGenerateRectificationGetPositions() {
		int[] sqrArr = {2,3,8,9,5,1,4,7,6};
		Adder adder = new Adder(new Square(new Values(sqrArr))); 
		DoubleLineRectHeuristic heuristic = new DoubleLineRectHeuristic(adder);
		heuristic.generateRectification();
		assertEquals(heuristic.getPos1(), 2);
		assertEquals(heuristic.getPos2(), 8);
	}

	/**
	 * 	2	7	6	0
		5	9	1	0
		3	4	8	0
		-5	5	0
	 */
	
	public void testColumnGenerateRectification() {
		int[] sqrArr = {2,7,6,5,9,1,3,4,8};
		Adder adder = new Adder(new Square(new Values(sqrArr))); 
		DoubleColumnRectHeuristic heuristic = new DoubleColumnRectHeuristic(adder);
		assertTrue(heuristic.generateRectification());
	}
	
	/**
	 * 	2	7	6	0
		5	9	1	0
		3	4	8	0
		-5	5	0

	 */
	
	public void testColumnGenerateRectificationGetPositions() {
		int[] sqrArr = {2,7,6,5,9,1,3,4,8};
		Adder adder = new Adder(new Square(new Values(sqrArr))); 
		DoubleColumnRectHeuristic heuristic = new DoubleColumnRectHeuristic(adder);
		heuristic.generateRectification();
		assertEquals(heuristic.getPos1(), 6);
		assertEquals(heuristic.getPos2(), 7);
	}
	
	public void testGetAction() {
		Heuristic[] heuristic = {new DoubleRectificationHeuristic(1)};
		Adder adder = new Adder(SquareFactory.generateSquare(5));
		HeuristicSolver solver = new HeuristicSolver(adder, heuristic);
		Action nextAction = null;
		for(int i = 0; i < 20; i++) {
			nextAction = solver.getValidAction();
			solver.moveNext(nextAction);
		}
		assertTrue(true);
	}
}
