package magicsquare;

public class Operators {
	
	public static boolean isSquare(int n){
		int sqrt = (int) Math.sqrt(n);
		return sqrt * sqrt == n;
	}
	
	public static int[][] convertSquare(int[] square){		
		int n = (int) Math.sqrt(square.length);
		int[][] array = new int[n][n];
		for(int i = 0; i < n*n; i++)
			array[(int)i/n][i%n] = square[i];
		return array;
	}
	
	public static boolean isSquare(int[][] array) {
		for(int i = 0; i < array.length; i++)
			if(array[i].length != array.length)
				return false;
		return true;
	}
}
