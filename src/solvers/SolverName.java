package solvers;

import magicsquare.Adder;
import heuristics.ActionPairValidator;
import heuristics.BiEntrySolver;
import heuristics.CellErrorHeuristic;
import heuristics.Heuristic;
import heuristics.diagonals.DiagonalHeuristicSolver;
import heuristics.diagonals.DiagonalsHeuristic;
import heuristics.diagonals.DoubleDiagonalRectificationSolver;
import heuristics.lines_columns.DoubleRectificationHeuristic;
import heuristics.lines_columns.LineColumnCellErrorHeuristic;
import heuristics.lines_columns.LineColumnHeuristic;
import heuristics.lines_columns.LineColumnRectification;

public enum SolverName {
	Solver, CellsHeuristicSolver, DiagonalSolver, DiagonalRectificationSolver, 
	LinesColumnsHeuristicSolver, DiagonalHeuristicSolver, SolverFirstFase, CellsHeuristicSolverAlone,
	LinesColumnsHeuristicSolverFirstFase, OneFaseHeuristics, BiEntrySolver, BiEntryDiagonalSolver,
	BiEntrySolverWithActionValidator, BiEntryDiagonalSolverWithActionValidator, 
	LinesColumnsHeuristicWithDoubleRectification, LinesColumnsCellHeuristicWithDoubleRectification,
	BiEntryCellSolverWithActionValidator, LinesColumnsCellHeuristicWithDoubleRectification1, 
	LinesColumnsCellHeuristicWithDoubleRectification2, DiagonalHeuristicSolver1, DiagonalHeuristicSolver2,
	BiEntryDiagonalSolverWithActionValidator1;
	
	public static Solver getSolver(SolverName solverName, Adder adder) {
		switch(solverName) {
		case Solver:
			return new Solver(adder);
		case CellsHeuristicSolver:
			Heuristic[] heuristics = {new LineColumnRectification(), new CellErrorHeuristic(0.5)};
			return new heuristics.HeuristicSolver(adder, heuristics);
		case DiagonalSolver:
			return new DiagonalSolver(adder);
		case DiagonalRectificationSolver:
			return new DoubleDiagonalRectificationSolver(adder);
		case LinesColumnsHeuristicSolver:
			Heuristic[] heuristics1 = {new LineColumnHeuristic(0.4), new CellErrorHeuristic(0.8)};
			return new heuristics.HeuristicSolver(adder, heuristics1, new LineColumnSolverEvaluation(adder));
		case DiagonalHeuristicSolver:
			Heuristic[] heuristics2 = {new DiagonalsHeuristic(0.8)};
			return new DiagonalHeuristicSolver(adder, heuristics2);
		case SolverFirstFase:
			return new Solver(adder, new LineColumnSolverEvaluation(adder));
		case CellsHeuristicSolverAlone:
			Heuristic[] heuristics3 = {new CellErrorHeuristic(1)};
			return new heuristics.HeuristicSolver(adder, heuristics3);
		case LinesColumnsHeuristicSolverFirstFase:
			Heuristic[] heuristics4 = {new LineColumnHeuristic(0.8)};
			return new heuristics.HeuristicSolver(adder, heuristics4, new LineColumnSolverEvaluation(adder));
		case OneFaseHeuristics:
			Heuristic[] heuristics5 = {new LineColumnHeuristic(0.4), new CellErrorHeuristic(0.8)};
			return new heuristics.HeuristicSolver(adder, heuristics5);
		case BiEntrySolver:
			Heuristic[] heuristics6 = {new CellErrorHeuristic(0.4), new LineColumnHeuristic(0.8)};
			return new BiEntrySolver(adder, heuristics6, new LineColumnSolverEvaluation(adder));
		case BiEntryDiagonalSolver:
			Heuristic[] heuristics7 = {new DiagonalsHeuristic(0.8)};
			return new heuristics.diagonals.BiEntryDiagonalSolver(adder, heuristics7);
		case BiEntrySolverWithActionValidator:
			Heuristic[] heuristics8 = {new LineColumnHeuristic(0.49), new CellErrorHeuristic(0.98)};
			return new BiEntrySolver(adder, heuristics8, new LineColumnSolverEvaluation(adder, new ActionPairValidator()));
		case BiEntryDiagonalSolverWithActionValidator:
			Heuristic[] heuristics9 = {new DiagonalsHeuristic(0.8)};
			return new heuristics.diagonals.BiEntryDiagonalSolver(adder, heuristics9, new DiagonalEvaluation(adder, new ActionPairValidator()));
		case LinesColumnsHeuristicWithDoubleRectification:
			Heuristic[] heuristics10 = {new LineColumnHeuristic(0.4), new DoubleRectificationHeuristic(0.4), new CellErrorHeuristic(0.8)};
			return new heuristics.HeuristicSolver(adder, heuristics10, new LineColumnSolverEvaluation(adder));
		case LinesColumnsCellHeuristicWithDoubleRectification:
			Heuristic[] heuristics1111 = {new LineColumnCellErrorHeuristic(0.6), new LineColumnHeuristic(0.95), new DoubleRectificationHeuristic(0.95)};
			return new heuristics.HeuristicSolver(adder, heuristics1111, new LineColumnSolverEvaluation(adder));
		case BiEntryCellSolverWithActionValidator:
			Heuristic[] heuristics12 = {new LineColumnCellErrorHeuristic(0.6), new LineColumnHeuristic(0.95), new DoubleRectificationHeuristic(0.95)};
			return new BiEntrySolver(adder, heuristics12, new LineColumnSolverEvaluation(adder, new ActionPairValidator()));
		case LinesColumnsCellHeuristicWithDoubleRectification1:
			Heuristic[] heuristics11 = {new LineColumnCellErrorHeuristic(0.4), new LineColumnHeuristic(0.8), new DoubleRectificationHeuristic(0.8)};
			return new heuristics.HeuristicSolver(adder, heuristics11, new LineColumnSolverEvaluation(adder));
		case LinesColumnsCellHeuristicWithDoubleRectification2:
			Heuristic[] heuristics111 = {new LineColumnCellErrorHeuristic(0.5), new LineColumnHeuristic(0.9), new DoubleRectificationHeuristic(0.9)};
			return new heuristics.HeuristicSolver(adder, heuristics111, new LineColumnSolverEvaluation(adder));
		case DiagonalHeuristicSolver1:
			Heuristic[] heuristics21 = {new DiagonalsHeuristic(0.9)};
			return new DiagonalHeuristicSolver(adder, heuristics21);
		case DiagonalHeuristicSolver2:
			Heuristic[] heuristics22 = {new DiagonalsHeuristic(0.95)};
			return new DiagonalHeuristicSolver(adder, heuristics22);
		case BiEntryDiagonalSolverWithActionValidator1:
			Heuristic[] heuristics13 = {new DiagonalsHeuristic(0.9)};
			return new heuristics.diagonals.BiEntryDiagonalSolver(adder, heuristics13, new DiagonalEvaluation(adder, new ActionPairValidator()));
		}
		return null;
	}
}