package heuristics.lines_columns;

import solvers.SquareActionFactory;
import apcs.Action;
import magicsquare.Adder;
import magicsquare.CellEvaluator;

public class ColumnRectificationHeuristic {
	protected int line;
	protected int column1;
	protected int column2;
	protected Adder adder;
	
	public ColumnRectificationHeuristic(Adder adder) {
		this.adder = adder;
		line = column1 = column2 = -1;
	}
	
	public boolean generateRectification(){
		while(setNextColumns())
			if(setLines())
				return true;
		return false;
	}
	
	public boolean setNextColumns() {
		for(int i = column1 + 1; i < adder.n(); i++) 
			if(setColumn(i))
				return true;
		return false;
	}
	
	private boolean setColumn(int column) {
		int newcolumn = getColumn(column);
		if(newcolumn < adder.n()) {
			column1 = column;
			column2 = newcolumn; 
			return true;
		}
		return false;
	}
	
	private int getColumn(int column1) {
		int column = column1;
		int diference1 = CellEvaluator.getDiference(adder.getElements().getColumnAdd(column1), adder.n());
		if(diference1 == 0)
			return adder.n();
		while(++column < adder.n()){
			int diference2 = CellEvaluator.getDiference(adder.getElements().getColumnAdd(column), adder.n());
			if(diference1 == -diference2)
				return column;
		}
		return column;
	}
	
	public boolean setLines() {
		if(column1 == -1 || column2 == -1)
			return false;
		for(int i = 0; i < adder.n(); i++)
			if(setLine(i))
				return true;
		return false;
	}
	
	protected boolean setLine(int line) {
		int diference1 = CellEvaluator.getDiference(adder.getElements().getColumnAdd(column1), adder.n());
		int value1 = adder.getSquare().getValues().elementAt(line, column1);
		int value2 = adder.getSquare().getValues().elementAt(line, column2);
		if(value1 - value2 == diference1){
			this.line = line;
			return true;
		}
		return false;
	}
	
	public Action getAction(Adder adder) {
		return SquareActionFactory.generate(getPos1(), getPos2(), adder.n() * adder.n() - 1);
	}
	
	public int getPos1() {
		return line * adder.n() + column1;
	}
	
	public int getPos2() {
		return line * adder.n() + column2;
	}
}
