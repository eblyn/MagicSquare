package magicsquare;
import java.util.Arrays;

public class Values {
	protected int[][] square;
	protected Swaper swaper;
	
	public Values(int[][] square) {
		if( square.length <= 0 || !Operators.isSquare(square))  
			throw new IllegalArgumentException();
		this.square = square;
		this.swaper = new Swaper(square);
	}
	
	public Values(int[] square) {		
		if( square.length <= 0 || !Operators.isSquare(square.length))
			throw new IllegalArgumentException();
		this.square = Operators.convertSquare(square);
		this.swaper = new Swaper(this.square);
	}
	
	public int n(){ 
		return square.length; 
	}
	
	public int elementAt(int x, int y) { 
		return square[x][y]; 
	}
	
	public Swaper getSwaper() {
		return swaper;
	}
	
	@Override public Values clone() {
		int[][] squareClone = square.clone();
		for(int i = 0; i < n(); i ++)
			squareClone[i] = square[i].clone();
		return new Values(squareClone);
	}

	public boolean equals(Values other) {
		for(int i = 0; i < square.length; i++)
			if(!Arrays.equals(square[i], other.square[i]))
				return false;
		return true;
	}
}
