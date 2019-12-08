package heuristics;

import heuristics.lines_columns.LineHeuristic;
import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.Values;
import junit.framework.TestCase;

public class LineHeuristicsTests extends TestCase {

	public void testSetLines() {
		int[] sqr = {1,2,3,4,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		LineHeuristic heuristic = new LineHeuristic(new Adder(square));
		assertEquals(heuristic.getMinLine(), 0);
		assertEquals(heuristic.getMaxLine(), 2);
	}
	
	public void testSetLinesFalse() {
		int[] sqr = {1,2,3,4,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		LineHeuristic heuristic = new LineHeuristic(new Adder(square));
		assertNotSame(heuristic.getMinLine(), 1);
	}

	public void testSetColumn() {
		int[] sqr = {3,2,1,4,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		LineHeuristic heuristic = new LineHeuristic(new Adder(square));
		assertEquals(heuristic.getColumn(), 2);
	}
}
