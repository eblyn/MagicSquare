package experiments;

import solvers.SolverName;
import apcs.ActionFactory;
import apcs.GAParams;
import apcs.IndiParams;
import apcs.RuleParams;

public class ParamsCreator {
	protected ParamsReader params;
	
	public ParamsCreator(ParamsReader params) {
		this.params = params;
	}
	
	public IndiParams createIndiParams(ActionFactory actionFactory, int conditionSize) {
		double wildProbability = Double.parseDouble(params.getParam("P_Wildcard"));
		RuleParams ruleParams = new RuleParams(wildProbability, actionFactory, conditionSize);
		int classifierNumber = Integer.parseInt(params.getParam("NB_Classifiers"));
		int coveringTime = Integer.parseInt(params.getParam("CoveringTime"));
		return new IndiParams(classifierNumber, coveringTime, ruleParams);
	}
	
	public GAParams createGAParams() {
		double mutationProbability = Double.parseDouble(params.getParam("P_Mutation"));
		double crossoverProbability = Double.parseDouble(params.getParam("P_Crossover"));
		int eliteSize = Integer.parseInt(params.getParam("EliteSize"));
		int populationSize = Integer.parseInt(params.getParam("PopulationSize"));
		return new GAParams(mutationProbability, crossoverProbability, eliteSize, populationSize);
	}
	
	public SquareParams createSquareParams() {
		int n = Integer.parseInt(params.getParam("N"));
		int cycles = Integer.parseInt(params.getParam("NB_Cycles"));
		int trials = Integer.parseInt(params.getParam("NB_Trials"));
		return new SquareParams(n, cycles, trials);
	}
	
	public ExperimentsParams createExperimentParams() {
		boolean twoStade = Boolean.parseBoolean(params.getParam("TwoStade"));
		SolverName approach = SolverName.values()[Integer.parseInt(params.getParam("Approach"))];
		String secondParamsFile = params.getParam("SecondParams").trim();
		return new ExperimentsParams(twoStade, approach, secondParamsFile);
	}
}
