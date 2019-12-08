package experiments;

import magicsquare.Adder;

public class Experiment {
	protected ExperimentsResults results;
	protected ExperimentsElements elements;
	protected double seconds;
	protected int cycle;
	protected int coveringRestart;
	
	public Experiment(ParamsCreator params) {
		elements = new ExperimentsElements(params);
		results = new ExperimentsResults();
	}
	
	public Experiment(ParamsCreator params, Adder adder) {
		elements = new ExperimentsElements(params, adder);
		results = new ExperimentsResults();
	}
	
	public void run() {
		long time = System.currentTimeMillis();
		for(cycle = 0; cycle < elements.getSquareParams().getCycles(); cycle++) {
			Adder bestLocalSolution = elements.runCycle();
			update(bestLocalSolution, cycle);
			if(meetCondition())
				break;
		}
		seconds = ((double)(System.currentTimeMillis() - time))/1000;
	}
	
	public boolean meetCondition() {
		return results.getBestEvaluation() == 0;
	}
	
	private void update(Adder bestLocalSolution, int cycle){
		double evaluation = elements.getEnvironment().getEvaluator().evaluate(bestLocalSolution); 
		if(evaluation < results.getBestEvaluation()) {
			results.updateBest(evaluation, bestLocalSolution, cycle);
			elements.updateBest(evaluation, bestLocalSolution);
		}
		/*else if (elements.getApcs().getCoveringTimes() == 0){
			elements.getApcs().restart(elements.getEnvironment());
			coveringRestart++;
		}*/
	}
	
	public ExperimentsResults getResults() {
		return results;
	}
	
	public String getExtraOutput(){
		String extra = seconds + ",";
		int matchingTimesAvg = (int)elements.getApcs().getMatchingTimes()/(cycle + 1);
		int invalidActionAvg = (int)elements.getApcs().getInvalidActionCount()/(cycle + 1);
		extra += matchingTimesAvg + "," + invalidActionAvg + "," + coveringRestart;
		return extra;
	}
}
