package solvers;

import magicsquare.Adder;
import magicsquare.Evaluator;

public class LineColumnSolverEvaluation extends SolverEvaluation {
	public LineColumnSolverEvaluation(Adder adder, SolverValidator validator){
		super(adder, validator);
	}
	
	public LineColumnSolverEvaluation(Adder adder) {
		super(adder);
	}
	
	@Override
	public double evaluate(Adder adder) {
		return Evaluator.evaluateLineColumn(adder);
	}
}
