package solvers;

import magicsquare.Square;

public abstract class SolverValidator {
	
	public boolean validPositions(int pos1, int pos2, int n) {
		int maxValue = n*n - 1;
		if(pos1 > maxValue ||  pos2 > maxValue)
			return false;
		if(pos1 == pos2)
			return false;
		 return true;
	}
	
	public boolean validAction(int pos1, int pos2, Square square){
		if(!validPositions(pos1, pos2, square.getValues().n()))
			return false;
		if(!validSwap(pos1, pos2, square))
			return false;
		return true;
	}
	
	public abstract boolean validSwap(int pos1, int pos2, Square square);
	public abstract void reset();
	public abstract void reset(Square square);
	public abstract void addVisited(int pos1, int pos2, Square square);
}
