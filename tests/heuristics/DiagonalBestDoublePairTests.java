package heuristics;

import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.Values;
import heuristics.diagonals.DiagonalBestDoublePair;
import junit.framework.TestCase;

public class DiagonalBestDoublePairTests extends TestCase {

	/*
	 *  		
		15	14	4	1
		6	7	9	12
		10	11	5	8
		3	2	16	13

	 */
	
	public void testDiagonalBestDoublePair() {
		int[] sqrArr = {15, 14, 4, 1, 6, 7, 9, 12, 10, 11, 5, 8, 3, 2, 16, 13};
		DiagonalBestDoublePair heuristic = new DiagonalBestDoublePair(new Adder(new Square(new Values(sqrArr))));
		assertEquals(heuristic.getDoublePair().getLine1(), 0);
		assertEquals(heuristic.getDoublePair().getLine2(), 1);
		assertEquals(heuristic.getDoublePair().getColumn1(), 2);
		assertEquals(heuristic.getDoublePair().getColumn2(), 3);
		assertFalse(heuristic.getDoublePair().isLine());
	}
	
	/*
	 *  		
		15	14	1	4
		6	7	12	9
		10	11	5	8
		3	2	16	13

	 */
	
	public void testDiagonalBestDoublePair1() {
		int[] sqrArr = {15, 14, 1, 4, 6, 7, 12, 9, 10, 11, 5, 8, 3, 2, 16, 13};
		DiagonalBestDoublePair heuristic = new DiagonalBestDoublePair(new Adder(new Square(new Values(sqrArr))));
		assertEquals(heuristic.getDoublePair().getLine1(), 0);
		assertEquals(heuristic.getDoublePair().getLine2(), 1);
		assertEquals(heuristic.getDoublePair().getColumn1(), 0);
		assertEquals(heuristic.getDoublePair().getColumn2(), 1);
		assertFalse(heuristic.getDoublePair().isLine());
	}

	/*
	 *  		
		14	15	1	4
		7	6	12	9
		10	11	5	8
		3	2	16	13

	 */
	
	public void testDiagonalBestDoublePair2() {
		int[] sqrArr = {14, 15, 1, 4, 7, 6, 12, 9, 10, 11, 5, 8, 3, 2, 16, 13};
		DiagonalBestDoublePair heuristic = new DiagonalBestDoublePair(new Adder(new Square(new Values(sqrArr))));
		assertEquals(heuristic.getDoublePair().getLine1(), 0);
		assertEquals(heuristic.getDoublePair().getLine2(), 1);
		assertEquals(heuristic.getDoublePair().getColumn1(), 0);
		assertEquals(heuristic.getDoublePair().getColumn2(), 1);
		assertFalse(heuristic.getDoublePair().isLine());
	}
}
