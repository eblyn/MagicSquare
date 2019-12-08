package heuristics;

public class DoublePair {
	protected int line1, line2, column1, column2;
	protected boolean line;
	protected double error;
	
	public DoublePair(double error) {
		line1 = line2 = column1 = column2 = -1;
		this.error = error;
	}
	
	public void setValues(double error, int line1, int line2, int column1, int column2, boolean line) {
		this.line1 = line1;
		this.line2 = line2;
		this.column1 = column1;
		this.column2 = column2;
		this.line = line;
		this.error = error; 
	}
	
	public int getPos1(int n) {
		return line ? column1 * n + line1 : column2 * n + line1;
	}
	
	public int getPos2(int n) {
		return line ? column2 * n + line2 : column1 * n + line2;
	}
	
	public boolean validSwap() {
		return column1 != -1 && column2 != -1 && line1 != -1 && line2 != -1;
	}
	
	public int getLine1() { return line1; }

	public int getLine2() { return line2; }

	public int getColumn1() { return column1; }

	public int getColumn2() { return column2; }

	public boolean isLine() { return line; }

	public double getError() { return error; }
}
