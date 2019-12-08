package heuristics;

import heuristics.lines_columns.OrderColumnOrLinePairs;
import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.Values;
import junit.framework.TestCase;

public class BestPairTests extends TestCase {

	public void testSetNextIfCeroDiferenceExistBetweenLinesReturnFirstPair() {
		int[] sqr = {1,2,3,4,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		OrderColumnOrLinePairs bestPair = new OrderColumnOrLinePairs(new Adder(square));
		assertEquals(bestPair.getBestPair().getMinPos(), 0);
		assertEquals(bestPair.getBestPair().getMaxPos(), 2);
	}

	public void testSetNextIfCeroDiferenceExistBetweenColumnsReturnFirstPair() {
		int[] sqr = {1,5,3,4,2,6,7,8,9};
		Square square = new Square(new Values(sqr));
		OrderColumnOrLinePairs bestPair = new OrderColumnOrLinePairs(new Adder(square));
		assertEquals(bestPair.getBestPair().getMinPos(), 0);
		assertEquals(bestPair.getBestPair().getMaxPos(), 2);
	}
	
	public void testSetNextIfCeroDiferenceExistBetweenContinuousLinesReturnFirstPair() {
		int[] sqr = {1,2,3,7,8,9,4,5,6};
		Square square = new Square(new Values(sqr));
		OrderColumnOrLinePairs bestPair = new OrderColumnOrLinePairs(new Adder(square));
		assertEquals(bestPair.getBestPair().getMinPos(), 0);
		assertEquals(bestPair.getBestPair().getMaxPos(), 1);
	}

	
	/*
	 *            37
[1, 2, 3, 4] 10
[10, 11, 12, 13] 46
[14, 15, 16, 5] 50
[6, 7, 8, 9] 30
[31, 35, 39, 31] 37
	 * */
	
	public void testSetNextIfBestDiferenceExistBetweenLinesReturnFirstPair() {
		int[] sqr = {1,2,3,4,10,11,12,13,14,15,16,5,6,7,8,9};
		Square square = new Square(new Values(sqr));
		OrderColumnOrLinePairs bestPair = new OrderColumnOrLinePairs(new Adder(square));
		assertEquals(bestPair.getBestPair().getMinPos(), 0);
		assertEquals(bestPair.getBestPair().getMaxPos(), 2);
		assertFalse(bestPair.getBestPair().getFirst().isLine());
	}
}
