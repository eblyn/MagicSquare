package apcs;
import java.util.Vector;

public class GeneticAlgorithm {
	protected Vector<Individual> population;
	protected GAParams parameters; 
	
	public GeneticAlgorithm(Vector<Individual> population, GAParams parameters){
		this.population = population;
		this.parameters = parameters;
	}
	
	public GeneticAlgorithm(IndiParams individualParams, GAParams agParams, boolean createRules) {
		int populationSize = agParams.getPopulationSize();
		population = new Vector<Individual>();
		for(int i = 0; i < populationSize; i++) 
			population.add(new Individual(individualParams, createRules));
		parameters = agParams;
	}
	
	public void nextGeneration(double[] evaluations){
		Vector<Individual> bestParents = Elite.bestIndividuals(parameters.getEliteSize(), population);
		Vector<Individual> offspring = Selection.run(population, evaluations);
		Crossover.run(offspring, parameters.crossoverProbability);
		mute(offspring, parameters.mutationProbability);
		Elite.run(bestParents, offspring);
		population = offspring;
	}
	
	public static void mute(Vector<Individual> offspring, double probability){
		for(int i = 0; i < offspring.size(); i++)
			offspring.elementAt(i).mute(probability);
	}
	
	public Individual getIndividual(int index) {
		return population.elementAt(index);
	}
	
	public int size() {
		return population.size();
	}
	
	public GAParams getParameters() {
		return parameters;
	}
	
	public void restart(){
		for(int i = 0; i < population.size(); i++)
			population.elementAt(i).restart();
	}
}
