package heuristics;

import heuristics.lines_columns.ColumnHeuristic;
import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.Values;
import junit.framework.TestCase;

public class ColumnHeuristicsTests extends TestCase {

	public void testColumnHeuristic() {
		int[] sqr = {1,2,3,4,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		ColumnHeuristic heuristic = new ColumnHeuristic(new Adder(square));
		assertEquals(heuristic.getMinColumn(), 0);
		assertEquals(heuristic.getMaxColumn(), 2);
	}
	
	public void testColumnHeuristicFalse() {
		int[] sqr = {1,2,3,4,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		ColumnHeuristic heuristic = new ColumnHeuristic(new Adder(square));
		assertNotSame(heuristic.getMinColumn(), 1);
	}
	
	public void testGetLine() {
		int[] sqr = {7,2,3,4,5,6,1,8,9};
		Square square = new Square(new Values(sqr));
		ColumnHeuristic heuristic = new ColumnHeuristic(new Adder(square));
		assertEquals(heuristic.getLine(), 1);
	}
}
