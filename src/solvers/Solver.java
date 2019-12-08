package solvers;

import magicsquare.Adder;
import magicsquare.Square;
import magicsquare.Wrapper;
import apcs.Action;
import apcs.ActionFactory;
import apcs.Environment;

public class Solver implements Environment{
	protected SolverEvaluation evaluator;
	protected Adder bestGlobalSolution;
	protected ActionFactory actionFactory;
	protected Adder adder;
	
	public Solver(Adder adder, SolverEvaluation evaluation) {
		this.adder = adder;
		this.evaluator = evaluation;
		bestGlobalSolution = new Adder(adder.getSquare().clone());
		this.actionFactory = new SquareActionFactory(adder.n());
	}

	public Solver(Adder adder) {
		this(adder, new SolverEvaluation(adder));
	}
	
	public ActionFactory getActionFactory() {
		return actionFactory;
	}
	
	public Solver(Square square) {
		this(new Adder(square.clone()));
	}
	
	@Override
	public double getEvaluation() { return evaluator.getEvaluation();}
	
	@Override
	public byte[] getSignal() { return Wrapper.wrapp(adder.getSquare()); }
	public Square getBestSolution() { return evaluator.getSolutions().getBest(); }
	
	@Override
	public void reset() {
		//TODO pasar bestGlobalSolution pa solutions
		double globalEvaluation  = evaluator.evaluate(bestGlobalSolution);
		if( globalEvaluation > evaluator.getSolutions().getBestEvaluation())
			bestGlobalSolution = new Adder(evaluator.getSolutions().getBest());
		evaluator.reset();
		adder = new Adder(evaluator.solutions.getFirst().clone());
	}
	
	public double getBestEvaluation() { return evaluator.getSolutions().getBestEvaluation(); 	}

	@Override
	public double getLastEvaluation(int trials) { 
		return evaluator.getSolutions().getFirstEvaluation() - evaluator.getSolutions().getBestEvaluation();
	}
	
	public int getConditionSize() {
		int n = adder.n();
		return Wrapper.byteSizePerNumber(n * n) * n * n;
	}
	
	@Override
	public void moveNext(Action action) {
		int posSize = getPosSize();
		int pos1 = Wrapper.unwrappInt(action.getAction(), 0, posSize);
		int pos2 = Wrapper.unwrappInt(action.getAction(), posSize, posSize);
		moveNext(pos1, pos2);
	}
	
	public boolean validAction(Action action) {
		int posSize = getPosSize();
		int pos1 = Wrapper.unwrappInt(action.getAction(), 0, posSize);
		int pos2 = Wrapper.unwrappInt(action.getAction(), posSize, posSize);
		return evaluator.getValidator().validAction(pos1, pos2, evaluator.getNewSquare(adder, pos1, pos2));
	}
	
	public boolean moveNext(int pos1, int pos2){
		return evaluator.moveNext(adder, pos1, pos2);
	}
	
	public Adder getBestGlobalSolution() {
		return bestGlobalSolution;
	}
	
	public SolverEvaluation getEvaluator() {
		return evaluator;
	}
	
	public int getPosSize() {
		return Wrapper.byteSizePerNumber(adder.n() * adder.n() - 1);
	}

	@Override
	public Action getValidAction() {
		return actionFactory.generate();
	}
	
	public void restart(Adder adder) {
		this.adder = adder;
		bestGlobalSolution = new Adder(adder.getSquare().clone());
		evaluator.reset(adder);
	}
}