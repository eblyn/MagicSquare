package heuristics.diagonals;

import heuristics.Heuristic;
import magicsquare.Adder;
import apcs.Action;

public class DiagonalsHeuristic extends Heuristic {

	public DiagonalsHeuristic(double heuristicProbability) {
		super(heuristicProbability);
	}
	
	@Override
	protected Action getAction(Adder adder) {
		DiagonalsBestPairError totalSwap = new DiagonalsBestPairError(adder);
		DiagonalBestDoublePair doublePairSwap = new DiagonalBestDoublePair(adder);
		if(isBetterTotalSwap(totalSwap, doublePairSwap))
			return DiagonalsActionFactory.generate(totalSwap.getI(), totalSwap.getJ(), totalSwap.isLine(), adder.n());
		if(doublePairSwap.getDoublePair().validSwap())
			return DiagonalsActionFactory.generate(doublePairSwap.getDoublePair().getPos1(adder.n()), doublePairSwap.getDoublePair().getPos2(adder.n()), adder.n() * adder.n() - 1);
		return null;
	}

	private boolean isBetterTotalSwap(DiagonalsBestPairError totalSwap,	DiagonalBestDoublePair doublePairSwap) {
		if(!totalSwap.validSwap())
			return false;
		if (!doublePairSwap.getDoublePair().validSwap())
			return true;
		if(totalSwap.getError() <= doublePairSwap.getDoublePair().getError()) 
			return true;
		return false;
	}
	@Override
	public Action getNextProbableAction(Adder adder) {
		// TODO Auto-generated method stub
		return null;
	}

}
