package experiments;

import magicsquare.Adder;
import magicsquare.Evaluator;

public class FirstStadeExperiment extends Experiment {

	public FirstStadeExperiment(ParamsCreator params) {
		super(params);
	}

	public FirstStadeExperiment(ParamsCreator params, Adder adder) {
		super(params, adder);
	}
	
	@Override
	public boolean meetCondition() {
		return Evaluator.evaluateLineColumn(results.getBestSolution()) == 0;
	}
}
