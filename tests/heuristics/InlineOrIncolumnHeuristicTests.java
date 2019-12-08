package heuristics;

import heuristics.lines_columns.InlineOrIncolumnHeuristic;
import heuristics.lines_columns.OrderColumnOrLinePairs;
import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.Values;
import junit.framework.TestCase;

public class InlineOrIncolumnHeuristicTests extends TestCase {

	public void testGetValue() {
		int[] sqr = {3,2,1,4,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		Adder adder = new Adder(square);
		OrderColumnOrLinePairs bestPair = new OrderColumnOrLinePairs(adder);
		InlineOrIncolumnHeuristic heuristic = new InlineOrIncolumnHeuristic(adder);
		heuristic.addValues(bestPair.getBestPair());
		assertEquals(heuristic.next().getPos1(), 2);
	}

	/*
	 *            37
[1, 2, 3, 4] 10
[10, 11, 12, 13] 46
[14, 15, 5, 16] 50
[6, 7, 8, 9] 30
[31, 35, 28, 42] 37
	 * */
	
	public void testGetValueIfExist() {
		int[] sqr = {1,2,3,4,10,11,12,13,14,15,5,16,6,7,8,9};
		Square square = new Square(new Values(sqr));
		Adder adder = new Adder(square);
		OrderColumnOrLinePairs bestPair = new OrderColumnOrLinePairs(adder);
		InlineOrIncolumnHeuristic heuristic = new InlineOrIncolumnHeuristic(adder);
		heuristic.addValues(bestPair.getBestPair());
		assertEquals(heuristic.next().getPos1(), 10);
	}

}
