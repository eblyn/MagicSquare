package heuristics;

import heuristics.diagonals.DiagonalsBestPairError;
import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.Values;
import junit.framework.TestCase;

public class DiagonalBestErrorTest extends TestCase {

	public void testDiagonalsBestError() {
		int[] sqr = {6,5,4,1,2,3,7,8,9};
		Square square = new Square(new Values(sqr));
		DiagonalsBestPairError diagonalError = new DiagonalsBestPairError(new Adder(square));
		assertTrue(diagonalError.isLine());
		assertEquals(diagonalError.getI(), 0);
		assertEquals(diagonalError.getJ(), 1);
	}

}
