package apcs;

public class APCS {
	protected APCSElements elements;
	protected Individual bestIndividual;
	
	public APCS(GeneticAlgorithm genetic, Environment environment) {
		this.elements = new APCSElements(genetic, environment);
	}
	
	public void run(int trials){
		elements.coveringTimes = 0;
		double[] evaluations = new double[elements.getGA().size()];
		for(int i = 0; i < elements.getGA().size(); i++) 
			evaluations[i] = elements.evaluateIndividual(i, trials);
		updateBest(evaluations);
		setPositive(evaluations);
		elements.nextGeneration(evaluations);
	}
	
	private void updateBest(double[] evaluations) {
		int bestIndividualIndex = 0;
		bestIndividual = elements.getGA().getIndividual(0);
		for(int i = 0; i < evaluations.length; i++)
			if(evaluations[i] > evaluations[bestIndividualIndex]) {
				bestIndividualIndex = i;
				bestIndividual = elements.getGA().getIndividual(i);
			}
	}
	
	private int getMinIndex(double[] evaluations) {
		int minIndex = 0;
		for(int i = 0; i < evaluations.length; i++)
			minIndex = (evaluations[i] < evaluations[minIndex]) ? i : minIndex;
		return minIndex;
	}
	
	private void setPositive(double[] evaluations) {
		int minIndex = getMinIndex(evaluations);
		if(evaluations[minIndex] > 0)
			return;
		double value = - evaluations[minIndex];
		for(int i = 0; i < evaluations.length; i++)
			evaluations[i] += value + 1 ;
	}
	
	public Individual bestIndividual() {
		return bestIndividual;
	}
	
	public long getMatchingTimes(){
		return elements.matchingTimes;
	}
	
	public long getInvalidActionCount(){
		return elements.invalidActions;
	}
	
	public void restart(Environment environment) {
		elements.setEnvironment(environment);
		elements.getGA().restart();
	}
	
	public int getCoveringTimes(){
		return elements.coveringTimes;
	}
}