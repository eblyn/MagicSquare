package apcs;

import java.util.Random;
import java.util.Vector;

public class Crossover {

	public static void run(Vector<Individual> offspring, double prob) {
		Random random = new Random();
		Vector<Individual> indis = new Vector<Individual>(offspring);
		for(int i = 0; i < offspring.size()/2; i++) {
			if(random.nextDouble() < prob)
				doCrossover(random, indis);
			else
				indis.remove(0);
		}
	}
	
	private static void doCrossover(Random random, Vector<Individual> indis) {
		Individual individual1 = indis.elementAt(0);
		indis.remove(0);
		int index2 = random.nextInt(indis.size());
		Individual individual2 = indis.elementAt(index2);
		indis.remove(index2);
		int ruleIndex = random.nextInt(individual1.size());
		int crossPosition = random.nextInt(individual1.getRuleSize());
		individual1.crossover(individual2, ruleIndex, crossPosition);
	}
}
