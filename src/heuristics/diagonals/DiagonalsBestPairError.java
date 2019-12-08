package heuristics.diagonals;

import magicsquare.Adder;
import magicsquare.Evaluator;

public class DiagonalsBestPairError {
	protected boolean line;
	protected int i, j;
	protected Adder adder;
	protected double error;
	
	public DiagonalsBestPairError(Adder adder) {
		this.adder = adder;
		i = j = -1;
		error = Evaluator.evaluateDiagonals(adder);
		setValues();
	}
	
	private void setValues(){
		for(int index = 0; index < adder.n() - 1; index++) 
			setBestJ(index);
	}

	private void setBestJ(int index) {
		for(int nextIndex = index + 1; nextIndex < adder.n(); nextIndex++) {
			double errorLine = SwapEvaluator.lineErrorDiag0(index, nextIndex, adder) 
							+ SwapEvaluator.LineErrorDiag1(index, nextIndex, adder);
			double errorColumn = SwapEvaluator.lineErrorDiag0(index, nextIndex, adder) 
							+ SwapEvaluator.LineErrorDiag1(adder.n()-index-1, adder.n()-nextIndex-1, adder);
			setBestError(errorLine, errorColumn, index, nextIndex);
		}
	}
	
	private void setBestError (double errorLine, double errorColumn, int index0, int index1) {
		if(errorLine < error || errorColumn < error) {
			i = index0;
			j = index1;
			line = errorLine <= errorColumn;
			error = line ? errorLine : errorColumn;
		}
	}

	public boolean isLine() { return line; 	}
	public int getI() {	return i;	}
	public int getJ() { return j;	}
	
	public double getError() {
		return error;
	}
	
	public boolean validSwap() {
		return i > 0 && j > 0;
	}
}
