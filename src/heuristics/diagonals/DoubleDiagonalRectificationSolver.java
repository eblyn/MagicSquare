package heuristics.diagonals;

import solvers.DiagonalSolver;
import magicsquare.Adder;
import apcs.Action;

public class DoubleDiagonalRectificationSolver extends DiagonalSolver {
	
	public DoubleDiagonalRectificationSolver(Adder adder) {
		super(adder);
	}

	@Override
	public Action getValidAction() {
		Action action = rectificationAction();
		if(action != null)
			return action;
		return super.getValidAction();
	}

	private Action rectificationAction() {
		Diagonal0Rectification diagonal0 = new Diagonal0Rectification(adder);
		if(!diagonal0.generateRectification())
			rectifieDiagonal1();
		else if(validDiagonal1Swap(diagonal0.getI(), diagonal0.getJ(), diagonal0.isLine()))
			return getAction(diagonal0.getI(), diagonal0.getJ(), diagonal0.isLine());
		else diagonal0.swapValues();
		return null;
	}
	
	private boolean validDiagonal1Swap(int i, int j, boolean line) {
		if(line)
			return Diagonal1Rectification.validLineSwap(i, j, adder);
		return Diagonal1Rectification.validColumnSwap(i, j, adder);
	}
	
	private void rectifieDiagonal1(){
		Diagonal1Rectification diagonal1 = new Diagonal1Rectification(adder);
		if(diagonal1.generateRectification())
			diagonal1.swapValues();
	}

	private Action getAction(int i, int j, boolean line) {
		return DiagonalsActionFactory.generate(i, j, line, adder.n());
	}
}
