package heuristics;

import solvers.SquareActionFactory;
import apcs.Action;
import magicsquare.Adder;
import magicsquare.CellEvaluator;
import magicsquare.Evaluator;

public class CellErrorHeuristic extends Heuristic {
	
	public CellErrorHeuristic(double heuristicProbability) {
		super(heuristicProbability);
	}

	/*TODO esta segunda parte también se le puede mejorar en tiempo analizando 
	los pares de mayor suma de errores en relación con el valor. Se podrá calcular a partir de qué valor 
	el intercambio es positivo o negativo para una casilla
	*/
	public int getPos2(int pos1, Adder adder) {
		int bestIndex = -1;
		double bestValue = getFirstPos2ErrorValue(adder);
		for(int i = 0; i < adder.n()*adder.n(); i++){
			if(validPos2(pos1, i, adder.n()))
				continue;
			double errorDiff = swapDifference(adder, pos1, i);
			if(bestValue < errorDiff){
				bestIndex = i;
				bestValue = errorDiff;
			}
		}
		return bestIndex;
	}
	
	protected double getFirstPos2ErrorValue(Adder adder) {
		return Double.MIN_VALUE;
	}
	
	protected boolean validPos2(int pos1, int pos2, int n) {
		return pos2 == pos1;
	}
	
	//TODO No usar tanto evaluate, solo cuando se cambia el adder y pa eso, mantener la evaluación en el adder.
	protected double swapDifference(Adder adder, int pos1, int pos2) {
		return Evaluator.swapErrorDifference(adder, pos1, pos2);
	}
	
	public int getPos1(Adder adder){
		int bigestError = -1;
		int bigestErrorIndex = -1;
		for(int i = 0; i < adder.n() * adder.n(); i++){
			double cellError = CellEvaluator.cellError(i, adder);
			if(cellError > bigestError){
				 bigestError = (int)cellError;
				 bigestErrorIndex = i;
			}
		}
		return bigestErrorIndex;
	}
	
	public Action getAction(Adder adder) {
		int pos1 = getPos1(adder);
		int pos2 = getPos2(pos1, adder);
		if(pos2 != -1)
			return SquareActionFactory.generate(pos1, pos2, adder.n() * adder.n() - 1);
		return null;
	}

	@Override
	public Action getNextProbableAction(Adder adder) {
		// TODO Auto-generated method stub
		return null;
	}
}
