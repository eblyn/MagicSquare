package heuristics;

import solvers.SolverEvaluation;
import magicsquare.Adder;
import magicsquare.Wrapper;

public class BiEntrySolver extends HeuristicSolver {
	protected int pos1;
	protected int pos2;
	
	public BiEntrySolver(Adder adder, Heuristic[] heuristics) {
		super(adder, heuristics);
	}
	
	public BiEntrySolver(Adder adder, Heuristic[] heuristics, SolverEvaluation evaluation){
		super(adder, heuristics, evaluation);
	}
	
	@Override
	public byte[] getSignal() {
		int numberSize = Wrapper.byteSizePerNumber(adder.n()*adder.n() - 1);
		byte[] action = new byte[2 * numberSize];
		Wrapper.addWrappNumber(action, pos1, numberSize, 0);
		Wrapper.addWrappNumber(action, pos2, numberSize, 1);
		return action;
	}
	
	public int getConditionSize() {
		int n = adder.n();
		return 2 * Wrapper.byteSizePerNumber(n * n - 1) ;
	}
	
	@Override
	public boolean moveNext(int pos1, int pos2){
		this.pos1 = pos1;
		this.pos2 = pos2;
		return super.moveNext(pos1, pos2);
	}
	
	@Override
	public void reset() {
		pos1 = pos2 = 0;
		super.reset();
	}
	
	@Override
	public void restart(Adder adder) {
		pos1 = pos2 = 0;
		super.restart(adder);
	}
}
