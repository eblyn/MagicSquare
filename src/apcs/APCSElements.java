package apcs;

public class APCSElements {
	protected GeneticAlgorithm genetic;
	protected Environment environment;
	protected Covering covering;
	protected long matchingTimes;
	protected long invalidActions;
	protected int coveringTimes;
	
	public APCSElements(GeneticAlgorithm genetic, Environment environment) {
		this.genetic = genetic;
		this.environment = environment;
		this.covering = new Covering(genetic.getIndividual(0).wildProbability);
	}
	
	public double evaluateIndividual(int individualIndex, int trials) {
		for(int i = 0; i < trials; i++)
			runTrial(individualIndex);
		updateLastStrength(individualIndex, trials);
		environment.reset();
		return genetic.getIndividual(individualIndex).getStrength();
	}
	
	private void runTrial(int individualIndex){
		byte[] signal = environment.getSignal();
		Rule rule = genetic.getIndividual(individualIndex).getRule(signal);
		if(rule == null){
			rule = covering.getCoveringRule(genetic.getIndividual(individualIndex), signal, environment.getValidAction());
			coveringTimes++;
		}
		else
			matchingTimes++;
		Action action = rule.getAction();
		environment.moveNext(action);
		updateStrength(individualIndex, rule);
	}
	
	protected void updateStrength(int indiIndex, Rule rule) {
		double evaluation = environment.getEvaluation();
		if(evaluation < 0){	
			invalidActions++;
			evaluation = createNewAction(rule);
		}
		double newStrength = genetic.getIndividual(indiIndex).getStrength() + evaluation;
		genetic.getIndividual(indiIndex).setStrength(newStrength);
	}

	private double createNewAction(Rule rule){
		Action action = environment.getValidAction();
		return setAction(rule, action);
	}
   
   private double setAction(Rule rule, Action action) {
       rule.setAction(action);
       environment.moveNext(action);
       return environment.getEvaluation();
    }

	
	protected void updateLastStrength(int indiIndex, int trials) {
		double evaluation = environment.getLastEvaluation(trials);
		double newStrength = genetic.getIndividual(indiIndex).getStrength() + evaluation;
		genetic.getIndividual(indiIndex).setStrength(newStrength / 2);
	}
	
	public GeneticAlgorithm getGA() {
		return genetic;
	}
	
	public Environment getEnvironment(){
		return environment;
	}
	
	public void setEnvironment(Environment value) {
		this.environment = value;
	}
	
	public void nextGeneration(double[] evaluations){
		covering.newGeneration(genetic);
		genetic.nextGeneration(evaluations);
	}
	
	public Covering getCovering(){
		return covering;
	}
}
