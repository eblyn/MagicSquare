package experiments;

import solvers.Solver;
import solvers.SolverName;
import magicsquare.Adder;
import magicsquare.SquareFactory;
import apcs.APCS;
import apcs.GAParams;
import apcs.GeneticAlgorithm;
import apcs.IndiParams;

public class ExperimentsElements {
	protected APCS apcs;
	protected SquareParams squareParams;
	protected Solver solver;
	
	public ExperimentsElements(ParamsCreator paramsCreator, Adder adder){
		squareParams = paramsCreator.createSquareParams();
		ExperimentsParams expParams = paramsCreator.createExperimentParams();
		solver = SolverName.getSolver(expParams.getApproach(), adder);
		init(paramsCreator);
	}
	
	public ExperimentsElements(ParamsCreator paramsCreator){
		squareParams = paramsCreator.createSquareParams();
		int n = squareParams.getN();
		Adder adder = new Adder(SquareFactory.generateSquare(n));
		ExperimentsParams expParams = paramsCreator.createExperimentParams();
		solver = SolverName.getSolver(expParams.getApproach(), adder);
		init(paramsCreator);
		
	}

	private void init(ParamsCreator params) {
		int n = squareParams.getN();
		int conditionSize = solver.getConditionSize();
		init(params, n, conditionSize);
	}
	
	private void init(ParamsCreator params, int n, int conditionSize) {
		GAParams gaParams = params.createGAParams();
		IndiParams indiParams = params.createIndiParams(solver.getActionFactory(), conditionSize);		
		GeneticAlgorithm genetic = new GeneticAlgorithm(indiParams, gaParams, true);
		apcs = new APCS(genetic, getEnvironment());
	}

	public APCS getApcs() {
		return apcs;
	}

	public SquareParams getSquareParams() {
		return squareParams;
	}
	
	public void updateBest(double evaluation, Adder bestLocalSolution) {
		Adder adder = new Adder(bestLocalSolution.getSquare().clone());
		solver.restart(adder);
		//apcs.restart(getEnvironment());
	}
	
	public Adder runCycle() {
		apcs.run(squareParams.getTrials());
		return getEnvironment().getBestGlobalSolution();
	}

	public Solver getEnvironment() {
		return solver;
	}
}
