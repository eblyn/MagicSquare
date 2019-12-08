package apcs;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;
import java.util.Vector;

public class Elite {
	
	public static Vector<Individual> bestIndividuals(int eliteSize, Vector<Individual> population){
		TreeSet<Individual> bestIndividuals = getBestIndividuals(eliteSize, population);
		Vector<Individual> bestIndex = new Vector<Individual>();
		Iterator<Individual> it = bestIndividuals.iterator();
		while(it.hasNext())
			bestIndex.add(it.next());
		return bestIndex;
	}
	
	private static TreeSet<Individual> getBestIndividuals(int eliteSize, Vector<Individual> population) {
		TreeSet<Individual> bestIndividuals = new TreeSet<Individual>(new IndividualComparator());
		for(int i = 0; i < population.size(); i++) {
			bestIndividuals.add(population.elementAt(i));
			if(bestIndividuals.size() > eliteSize)
				bestIndividuals.remove(bestIndividuals.last());
		}
		return bestIndividuals;
	}
	
	public static void run(Vector<Individual> bestParents, Vector<Individual> offspring) {
		deleteValues(bestParents.size(), offspring);
		fillValues(bestParents, offspring);
	}
	
	private static void fillValues(Vector<Individual> values, Vector<Individual> offspring){
		Random random = new Random();
		for(int i = 0; i < offspring.size(); i++) {
			if(offspring.elementAt(i) != null) 
				continue;
			int index = random.nextInt(values.size());
			offspring.set(i, values.elementAt(index));
			values.remove(values.elementAt(index));
		}
	}
	
	protected static void deleteValues(int amount, Vector<Individual> offspring) {
		Random random = new Random();
		for(int i = 0; i < amount; i++){
			int index = 0;
			do {
				index = random.nextInt(offspring.size());
			} while(offspring.elementAt(index) == null);
			offspring.set(index, null);
		}
	}
}
