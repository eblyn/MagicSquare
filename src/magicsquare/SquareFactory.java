package magicsquare;
import java.util.Random;
import java.util.Vector;

public class SquareFactory{	
	protected Square square;
	
	public static Vector<Integer> getNumbers(int n){
		Vector<Integer> numbers = new Vector<Integer>();
		for(int i = 0; i < n; i++)
			numbers.add(new Integer(i+1));
		return numbers;
	}
	
	private static int[] fillRandomArray(Vector<Integer> numbers){
		int[] square = new int[numbers.size()];
		Random r = new Random();
		for(int i = 0; i < square.length; i++){
			int position = r.nextInt(square.length - i);
			square[i] = numbers.elementAt(position);
			numbers.remove(position);
		}		
		return square;
	}

	public static Square generateSquare(int n) {
		return new Square(new Values(fillRandomArray(getNumbers(n*n))));
	}
}
