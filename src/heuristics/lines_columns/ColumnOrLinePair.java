package heuristics.lines_columns;

public class ColumnOrLinePair {
	protected ColumnOrLineValue first;
	protected ColumnOrLineValue second;
	
	public ColumnOrLinePair(ColumnOrLineValue first, ColumnOrLineValue second) {
		this.first = first;
		this.second = second;
	}

	public ColumnOrLineValue getFirst() {
		return first;
	}


	public ColumnOrLineValue getSecond() {
		return second;
	}

	public int getDiference(){
		return Math.abs(first.getError() + second.getError());
	}
	
	public int getError() {
		return Math.abs(first.getError()) + Math.abs(second.getError());
	}
	
	public int getMaxPos() {
		return Math.max(first.getPosition(), second.getPosition());
	}
	
	public int getMinPos() {
		return Math.min(first.getPosition(), second.getPosition());
	}
	
	@Override
	public boolean equals(Object obj) {
		try{
			ColumnOrLinePair other = (ColumnOrLinePair) obj;
			if(first.equals(other.first) && second.equals(other.second))
				return true;
			if(first.equals(other.second) && second.equals(other.first))
				return true;
			return false;
		}catch(Exception e) {
			return super.equals(obj);
		}	
	}
	
	@Override
	public String toString() {
		return "[" + first.toString() + "," + second.toString() + "]";
	}
}
