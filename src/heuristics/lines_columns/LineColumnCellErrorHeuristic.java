package heuristics.lines_columns;

import magicsquare.Adder;
import magicsquare.Evaluator;
import heuristics.CellErrorHeuristic;

public class LineColumnCellErrorHeuristic extends CellErrorHeuristic {

	public LineColumnCellErrorHeuristic(double heuristicProbability) {
		super(heuristicProbability);
	}
	
	@Override
	public int getPos1(Adder adder) {
		ArrayDiferenceExtremes lineExt = new ArrayDiferenceExtremes(adder.getElements().getLineAdds());
		ArrayDiferenceExtremes columnExt = new ArrayDiferenceExtremes(adder.getElements().getColumnAdds());
		return getWorstCell(lineExt, columnExt, adder.n());
	}

	@Override
	protected boolean validPos2(int pos1, int pos2, int n) {
		return pos2/n == pos1/n || pos2%n == pos1%n;
	}

	private int getWorstCell(ArrayDiferenceExtremes lineExt,
			ArrayDiferenceExtremes columnExt, int n) {
		int magicNumber = Evaluator.magicNumber(n);
		int minAddError = 2*magicNumber - lineExt.minValue - columnExt.minValue;
		int maxAddError = lineExt.maxValue + columnExt.maxValue - 2*magicNumber;
		if(minAddError < maxAddError)
			return lineExt.maxIndex * n + columnExt.maxIndex;
		return lineExt.minIndex * n + columnExt.minIndex;
	}
	
	//TODO hacer evaluación solo para las filas y las columnas
	@Override
	protected double swapDifference(Adder adder, int pos1, int pos2) {
		return Evaluator.swapLCErrorDiff(adder, pos1, pos2);
	}
	
	@Override
	protected double getFirstPos2ErrorValue(Adder adder) {
		return 0;
	}
}
