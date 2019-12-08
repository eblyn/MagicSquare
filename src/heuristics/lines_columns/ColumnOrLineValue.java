package heuristics.lines_columns;

public class ColumnOrLineValue {
	protected int position;
	protected int error;
	protected boolean line;
	
	public ColumnOrLineValue(int position, int error, boolean line) {
		this.position = position;
		this.error = error;
		this.line = line;
	}

	public int getPosition() {
		return position;
	}

	public int getError() {
		return error;
	}

	public boolean isLine() {
		return line;
	}
	
	@Override
	public boolean equals(Object obj) {
		try{
			ColumnOrLineValue other = (ColumnOrLineValue) obj;
			return (position == other.position) && (line == other.line);
		}catch(Exception e){
			return super.equals(obj);
		}
	}
	
	@Override
	public String toString() {
		String str = "["; 
		str += (line) ? "line " : "column ";
		return str + position + "," + error + "]";
	}
}
