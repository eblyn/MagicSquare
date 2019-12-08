package magicsquare;

import java.util.Arrays;

public class AdderElements {
	protected int[] linesAdd;
	protected int[] columnsAdd;
	protected int[] diagonalsAdd;
	
	public AdderElements(Square square) {
		linesAdd = new int[square.values.n()];
		columnsAdd = new int[square.values.n()];
		diagonalsAdd = new int[2];
		fillLinesColumns(square);
	}
	
	private void fillLinesColumns(Square square){
		for(int line = 0; line < square.values.n(); line++)
			for(int col = 0; col < square.values.n(); col++)
				addCell(line, col, square.values.elementAt(line, col));
		addDiagonals(square);
	}
	
	private void addCell(int line, int col, int value) {
		linesAdd[line] += value;
		columnsAdd[col] += value;
	}
	
	public void addDiagonals(Square square){
		diagonalsAdd[0] = diagonalsAdd[1] = 0;
		for(int i = 0; i < square.values.n(); i++){
			diagonalsAdd[0] += square.getValues().elementAt(i, i);
			diagonalsAdd[1] += square.getValues().elementAt(i, square.getValues().n() - i - 1);;
		}
	}
	
	public void changeCell(int contribution, int x, int y){
		linesAdd[x] += contribution;
		columnsAdd[y] += contribution;
		if(x == y)
			diagonalsAdd[0] += contribution;
		if(x + y == n() - 1)
			diagonalsAdd[1] += contribution;
		
	}
	
	public String lastLine() {
		return Arrays.toString(columnsAdd) + " " + diagonalsAdd[0];
	}
	
	public int getLineAdd(int index) { 	return linesAdd[index];	}
	public int getColumnAdd(int index) { return columnsAdd[index]; 	}
	public int getDiagonalAdd(int index) { return diagonalsAdd[index]; 	}
	public int n() { return linesAdd.length; }
	public int[] getLineAdds() { 	return linesAdd;	}
	public int[] getColumnAdds() { return columnsAdd; 	}
	public int[] getDiagonalAdds() { return diagonalsAdd; 	}
}
