package solvers;
import heuristics.diagonals.DiagonalsActionFactory;
import magicsquare.Adder;

public class DiagonalSolver extends Solver {
	
	public DiagonalSolver(Adder adder) {
		super(adder, new DiagonalEvaluation(adder));
		this.actionFactory = new DiagonalsActionFactory(adder.n());
	}
	
	public DiagonalSolver(Adder adder, SolverEvaluation evaluator){
		super(adder, evaluator);
		this.actionFactory = new DiagonalsActionFactory(adder.n());
	}
}
