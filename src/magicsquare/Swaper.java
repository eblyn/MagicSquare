package magicsquare;

public class Swaper {
	protected int[][] square;
	
	public Swaper(int[][] square) {
		this.square = square;
	}
	
	public void swap(int x1, int y1, int x2, int y2) {
		int firstValue = square[x1][y1];
		square[x1][y1] = square[x2][y2];
		square[x2][y2] = firstValue;
	}
	
	public void swapLines(int line1, int line2) {
		int[] aux = square[line1];
		square[line1] = square[line2];
		square[line2] = aux;
	}
	
	public void swapColumns(int column1, int column2) {
		for(int i = 0; i < square.length; i++){
			int aux = square[i][column1];
			square[i][column1] = square[i][column2];
			square[i][column2] = aux;
		}
	}

	public boolean equalAdd(int line1, int line2, int column1, int column2, boolean line) {
		int firstAdd = (line) ? square[line1][column1] + square[line1][column2] 
		                      : square[line1][column1] + square[line2][column1];
		int secondAdd = (line) ? square[line2][column1] + square[line2][column2] 
		                      : square[line1][column2] + square[line2][column2];
		if(firstAdd == secondAdd)
			return true;
		return false;
	}
	
	public void swapValues(int line1, int line2, int column1, int column2, boolean line) {
		if(line){
			swap(line1, column1, line2, column1);
			swap(line1, column2, line2, column2);
		}
		else {
			swap(line1, column1, line1, column2);
			swap(line2, column1, line2, column2);
			
		}
	}
}
