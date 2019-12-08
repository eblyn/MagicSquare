package apcs;

import solvers.Solver;
import solvers.SquareActionFactory;
import magicsquare.Adder;
import magicsquare.Evaluator;
import magicsquare.SquareFactory;
import magicsquare.Wrapper;
import junit.framework.TestCase;

public class APCSTest extends TestCase {
	public void testRun3() {
		int n = 3;
		double wildcardProbability = 0.3;
		int conditionSize = Wrapper.byteSizePerNumber(n*n) * n * n;
		SquareActionFactory actionFactory = new SquareActionFactory(n);
		RuleParams ruleParams = new RuleParams(wildcardProbability, actionFactory, conditionSize);
		
		int classifierNumber = 20;
		int coveringTime = n;
		IndiParams individualParams = new IndiParams(classifierNumber, coveringTime, ruleParams);
		
		double mutationProbability = 0.1;
		double crossoverProbability = 0.7;
		int populationSize = 10;
		int eliteSize = populationSize / 2;
		GAParams agParams = new GAParams(mutationProbability, crossoverProbability, eliteSize, populationSize);

		Solver environment = new Solver(SquareFactory.generateSquare(n));
		int trials = 30;
		
		GeneticAlgorithm genetic = new GeneticAlgorithm(individualParams, agParams, true);
		APCS apcs = new APCS(genetic, environment);
		Adder bestSolution = null;
		double bestEvaluation = Double.MAX_VALUE;
		for(int i = 0; i < 100000; i++) {
			apcs.run(trials);
			Adder bestLocalSolution = environment.getBestGlobalSolution();
			double evaluation = Evaluator.evaluate(bestLocalSolution);
			//System.out.println(evaluation);
			if(evaluation < bestEvaluation) {
				bestEvaluation = evaluation;
				bestSolution = bestLocalSolution;
				environment = new Solver(bestSolution);
				apcs.restart(environment);
				System.out.println(bestEvaluation);
						//+ " ==>\n" + bestSolution.toString());
				if(evaluation == 0)
					break;
			}		
		}
	}
	
	public void testRun5(){
		int n = 5;
		double wildcardProbability = 0.1;
		int conditionSize = Wrapper.byteSizePerNumber(n*n) * n * n;
		SquareActionFactory actionFactory = new SquareActionFactory(n);
		RuleParams ruleParams = new RuleParams(wildcardProbability, actionFactory, conditionSize);
		
		int classifierNumber = 30;
		int coveringTime = n;
		IndiParams individualParams = new IndiParams(classifierNumber, coveringTime, ruleParams);
		
		double mutationProbability = 0.01;
		double crossoverProbability = 0.5;
		int populationSize = 20;
		int eliteSize = 5;
		GAParams agParams = new GAParams(mutationProbability, crossoverProbability, eliteSize, populationSize);
		
		Solver environment = new Solver(SquareFactory.generateSquare(n));
		int trials = 20;
		
		GeneticAlgorithm genetic = new GeneticAlgorithm(individualParams, agParams, true);
		APCS apcs = new APCS(genetic, environment);
		Adder bestSolution = null;
		double bestEvaluation = Double.MAX_VALUE;
		for(int i = 0; i < 100000; i++) {
			apcs.run(trials);
			//apcs.runIndividual(apcs.bestIndividual(), trials);
			
			Adder bestLocalSolution = environment.getBestGlobalSolution();
			double evaluation = Evaluator.evaluate(bestLocalSolution);
			//System.out.println(evaluation);
			if(evaluation < bestEvaluation) {
				bestEvaluation = evaluation;
				bestSolution = bestLocalSolution;
				environment = new Solver(bestSolution);
				apcs.restart(environment);
				System.out.println(i + " " +bestEvaluation);
						//+ " ==>\n" + bestSolution.toString());
				if(evaluation == 0)
					break;
			}		
		}
	}
}
