package magicsquare;
import java.util.Arrays;

public class Adder {
	protected Square square;
	protected AdderElements elements;
	protected int evaluation;
	
	public Adder(Square square) {
		this.square = square;  
		elements = new AdderElements(square);
		evaluation = Evaluator.evaluate(this);
	}
	
	public int n(){
		return square.values.n();
	}
	
	public int add(int[] values) {
		int add = 0;
		for(int i = 0; i < values.length; i++)
			add += values[i];
		return add;
	}
	
	public void swap(int pos1, int pos2) {
		int value1 = square.elementAt(pos1);
		int value2 = square.elementAt(pos2);
		elements.changeCell(value2 - value1, square.getX(pos1), square.getY(pos1));
		elements.changeCell(value1 - value2, square.getX(pos2), square.getY(pos2));
		square.swap(pos1, pos2);
		evaluation += Evaluator.swapErrorDifference(this, pos1, pos2);
	}
	
	public Square getSquare(){
		return square;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < square.values.n() * (Math.log10(square.values.n()) + 2); i++) 
			str += " ";
		str += elements.getDiagonalAdd(1) + "\n";
		for(int i = 0; i < square.values.n(); i++)
			str += Arrays.toString(square.values.square[i]) + " " + elements.getLineAdd(i) + '\n';
		str += elements.lastLine();
		return str;
	}
	
	public AdderElements getElements() {
		return elements;
	}
	
	public int getEvaluation() {
		return evaluation;
	}
}
