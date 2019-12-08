package heuristics.lines_columns;

import solvers.SquareActionFactory;
import apcs.Action;
import magicsquare.Adder;
import magicsquare.CellEvaluator;

public class LineRectificationHeuristic {
	protected int line1;
	protected int line2;
	protected int column;
	protected Adder adder;
	
	public LineRectificationHeuristic(Adder adder) {
		this.adder = adder;
	}
	
	public boolean generateRectification(){
		while(setNextLines())
			if(setColumns())
				return true;
		return false;
	}
	
	public boolean setNextLines() {
		for(; line1 < adder.n(); line1++) 
			if(setLine2())
				return true;
		return false;
	}
	
	private boolean setLine2() {
		if(line2 >= adder.n())
			line2 = line1;
		int diference1 = CellEvaluator.getDiference(adder.getElements().getLineAdd(line1), adder.n());
		if(diference1 == 0)
			return false; 
		while(++line2 < adder.n()){
			int diference2 = CellEvaluator.getDiference(adder.getElements().getLineAdd(line2), adder.n());
			if(diference1 == -diference2)
				return true;
		}
		return false;
	}
	
	public boolean setColumns() {
		if(line1 == -1 || line2 == -1)
			return false;
		for(int i = 0; i < adder.n()-1; i++)
			if(setColumn(i))
				return true;
		return false;
	}
	
	protected boolean setColumn(int column) {
		int diference1 = CellEvaluator.getDiference(adder.getElements().getLineAdd(line1), adder.n());
		int value1 = adder.getSquare().getValues().elementAt(line1, column);
		int value2 = adder.getSquare().getValues().elementAt(line2, column);
		if(value1 - value2 == diference1){
			this.column = column;
			return true;
		}
		return false;
	}
	
	public Action getAction(Adder adder) {
		return SquareActionFactory.generate(getPos1(), getPos2(), adder.n() * adder.n() - 1);
	}
	
	public int getPos1() {
		return line1 * adder.n() + column;
	}
	
	public int getPos2() {
		return line2 * adder.n() + column;
	}
}
