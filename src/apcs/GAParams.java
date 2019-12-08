package apcs;

public class GAParams {
	protected double mutationProbability;
	protected double crossoverProbability;
	protected int eliteSize;
	protected int populationSize;
	
	public GAParams(double mutationProbability, double crossoverProbability, 
			int eliteSize, int populationSize) {
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.eliteSize = eliteSize;
		this.populationSize = populationSize;
	}
	
	public int getEliteSize() {
		return eliteSize;
	}

	public double getMutationProbability() {
		return mutationProbability;
	}
	
	public double getCrossoverProbability() {
		return crossoverProbability;
	}	
	
	public int getPopulationSize() {
		return populationSize;
	}
}
