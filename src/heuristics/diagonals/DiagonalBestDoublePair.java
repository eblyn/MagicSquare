package heuristics.diagonals;

import heuristics.DoublePair;
import magicsquare.Adder;
import magicsquare.Evaluator;

//TODO Ordenar las acciones en dependencia del error para si no se puede aplicar una acción seleccionar la próxima vez.
public class DiagonalBestDoublePair {
	protected Adder adder;
	protected DoublePair doublePair;
	
	public DiagonalBestDoublePair(Adder adder) {
		this.adder = adder;
		doublePair = new DoublePair(Evaluator.evaluateDiagonals(adder));
		setValues();
	}
	
	private void setValues(){
		for(int index = 0; index < adder.n() - 1; index++) 
			setBestJ(index);
	}

	private void setBestJ(int index) {
		for(int nextIndex = index + 1; nextIndex < adder.n(); nextIndex++) {
			setBestError(index, nextIndex, index, nextIndex);
			setBestError(index, nextIndex, adder.n() - nextIndex - 1, adder.n() - index - 1);
		}
	}
	
	private void setBestError(int line1, int line2, int column1, int column2){
		if(SwapEvaluator.validSwap(adder, line1, line2, column1, column2, true))
			updateBest(line1, line2, column1, column2, true);
		if(SwapEvaluator.validSwap(adder, line1, line2, column1, column2, false))
			updateBest(line1, line2, column1, column2, false);
	}
	
	private void updateBest(int line1, int line2, int column1, int column2, boolean line) {
		double actualError = SwapEvaluator.swapError(adder, line1, line2, column1, column2, line);
		if(actualError < doublePair.getError() )
			doublePair.setValues(actualError, line1, line2, column1, column2, line);
	}
	
	public DoublePair getDoublePair(){
		return doublePair;
	}
}
