package heuristics;

import heuristics.lines_columns.DoubleRectificationHeuristic;
import heuristics.lines_columns.LineColumnCellErrorHeuristic;
import heuristics.lines_columns.LineColumnHeuristic;
import magicsquare.Adder;
import magicsquare.Evaluator;
import magicsquare.SquareFactory;
import apcs.Action;
import junit.framework.TestCase;

public class LineColumnCellErrorHeuristicTests extends TestCase {

	public void testGetAction() {
		Heuristic[] heuristic = {new LineColumnCellErrorHeuristic(1)};
		Adder adder = new Adder(SquareFactory.generateSquare(3));
		HeuristicSolver solver = new HeuristicSolver(adder, heuristic);
		Action nextAction = null;
		for(int i = 0; i < 20; i++) {
			nextAction = solver.getValidAction();
			solver.moveNext(nextAction);
		}
		assertTrue(true);
	}
	
	public void testGetActionCombo() {
		Heuristic[] heuristic = {new LineColumnCellErrorHeuristic(1), new LineColumnHeuristic(1), new DoubleRectificationHeuristic(1)};
		Adder adder = new Adder(SquareFactory.generateSquare(50));
		Adder BestAdder = new Adder(adder.getSquare().clone());
		double bestEval = Double.MAX_VALUE;
		HeuristicSolver solver = new HeuristicSolver(adder, heuristic);
		Action nextAction = null;
		for(int j = 0; j < 1000; j++){
			adder = new Adder(BestAdder.getSquare().clone());
			solver.restart(adder);
			for(int i = 0; i < 50; i++) {
				nextAction = solver.getValidAction();
				solver.moveNext(nextAction);
				double evaluation = Evaluator.evaluateLineColumn(adder);
				
				if(evaluation < bestEval) {
					bestEval = evaluation;
					BestAdder =  new Adder(adder.getSquare().clone());
				}
				if(evaluation == 0){
					System.out.println("iteration " + i);
					break;
				}
			}
			if(bestEval == 0){
				System.out.println("cycle " + j);
				break;
			}
		}
		System.out.print(BestAdder.toString());
		assertTrue(true);
	}
}
