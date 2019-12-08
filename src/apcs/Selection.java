package apcs;

import java.util.Random;
import java.util.Vector;

public class Selection {
	
	public static Vector<Individual> run(Vector<Individual> population, double[] evaluations) {
		Random random = new Random();
		double[] intervals = normalize(evaluations);
		createIntervals(intervals);
		return rouletteWheel(intervals, random, population);
	}
	
	protected static void createIntervals(double[] evaluations){
		double actualAdd = 0;
		for(int i = 0; i < evaluations.length; i++) {
			evaluations[i] += actualAdd;
			actualAdd = evaluations[i];
		}
	}
	
	protected static double[] normalize(double[] evaluations) {
		double[] result = new double[evaluations.length];
		int add = 0;
		for(int i = 0; i < evaluations.length; i++)
			add += evaluations[i];
		for(int i = 0; i < evaluations.length; i++)
			result[i] = ((double)evaluations[i])/add;
		return result;
	}
	
	protected static Vector<Individual> rouletteWheel(double[] intervals, Random random, Vector<Individual> population) {
		Vector<Individual> offspring = new Vector<Individual>();
		for(int i = 0; i < intervals.length; i++){
			double point = random.nextDouble();
			int index = rouletteWheel(intervals, point, 0, intervals.length - 1);
			offspring.add(population.elementAt(index).clone());
		}
		return offspring;
	}
	
	private static int rouletteWheel(double[] intervals, double point, int initIndex, int lastIndex){
		if(point <= intervals[initIndex])
			return initIndex;
		if(lastIndex == 0 || lastIndex == initIndex || point > intervals[lastIndex]) 
			return lastIndex;
		if(point <= intervals[lastIndex] && point > intervals[lastIndex - 1])
			return lastIndex;
		if(point > intervals[(initIndex + lastIndex)/2])
			return rouletteWheel(intervals, point, (initIndex + lastIndex)/2, lastIndex);
		return rouletteWheel(intervals, point, initIndex, (initIndex + lastIndex)/2);
	}
}
