package heuristics.diagonals;

import java.util.Random;
import solvers.DiagonalSolver;
import solvers.SolverEvaluation;
import heuristics.Heuristic;
import magicsquare.Adder;
import apcs.Action;

public class DiagonalHeuristicSolver extends DiagonalSolver {
	protected Heuristic[] heuristics;
	protected Random random;
	
	public DiagonalHeuristicSolver(Adder adder, Heuristic[] heuristics) {
		super(adder);
		this.heuristics = heuristics;
		random = new Random();
	}
	
	public DiagonalHeuristicSolver(Adder adder, Heuristic[] heuristics, SolverEvaluation evaluator) {
		super(adder, evaluator);
		this.heuristics = heuristics;
		random = new Random();
	}
	
	@Override
	public Action getValidAction() {
		double randomValue = random.nextDouble();
		for(int i = 0; i < heuristics.length; i++){
			Action action = heuristics[i].getProbableAction(adder, randomValue);
			while(action != null && !validAction(action))
				action = heuristics[i].getNextProbableAction(adder);
			if(action != null)
				return action;
		}
		return super.getValidAction();
	}
}
