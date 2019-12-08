package solvers;

import magicsquare.Adder;
import magicsquare.Evaluator;
import magicsquare.Square;

public class SolverEvaluation {
	protected double evaluation;
	protected SolverValidator validator;
	protected SolverSolutions solutions;
	
	public SolverEvaluation(Adder adder){
		this(adder, new SquareValidator(adder.getSquare().clone()));
	}
	
	public SolverEvaluation(Adder adder, SolverValidator validator) {
		double firstEvaluation = evaluate(adder);
		solutions = new SolverSolutions(adder.getSquare(), firstEvaluation);
		this.validator = validator;
	}
	
	public double getEvaluation() { return evaluation; }
	public SolverSolutions getSolutions(){ return solutions; }
	public SolverValidator getValidator() { return validator;}
	public double evaluate(Adder adder) { return Evaluator.evaluate(adder); }
	
	public void updateEvaluation(Adder adder) {
		double newEvaluation = evaluate(adder);
		evaluation = solutions.updateBest(newEvaluation, adder.getSquare());
	}
	
	public void reset() {
		solutions.reset();
		validator.reset(solutions.getFirst().clone());
	}
	
	protected Square getNewSquare(Adder adder, int pos1, int pos2) {
		Square newSquare = adder.getSquare().clone();
		newSquare.swap(pos1, pos2);
		return newSquare;
	}
	
	public boolean moveNext(Adder adder, int pos1, int pos2){
		if(checkAction(adder, pos1, pos2)){
			swap(adder, pos1, pos2);
			updateEvaluation(adder);
			return true;
		}
		else {
			evaluation = -1;
			return false;
		}
	}
	
	public boolean checkAction(Adder adder, int pos1, int pos2){
		if(!validator.validPositions(pos1, pos2, adder.n()))
			return false;
		Square newSquare = getNewSquare(adder, pos1, pos2);
		if(!validator.validSwap(pos1, pos2, newSquare))
			return false;
		//TODO hacer addVisited(ActionPair,square) y validPosition tb.
		validator.addVisited(pos1, pos2, newSquare);
		return true;
	}
	
	public void reset(Adder adder) {
		double firstEvaluation = evaluate(adder);
		solutions.reset(adder.getSquare().clone(), firstEvaluation);
		validator.reset(adder.getSquare().clone());
	}
	
	protected void swap(Adder adder, int pos1, int pos2) {
		adder.swap(pos1, pos2);
	}
}
