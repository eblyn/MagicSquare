package solvers;

import java.util.Random;

import magicsquare.Wrapper;
import apcs.Action;
import apcs.ActionFactory;

public class SquareActionFactory implements ActionFactory{
	protected int maxValue;
	protected Random random;
	
	public SquareActionFactory(int n) {
		this.maxValue = n*n;
		this.random = new Random();
	}
	
	public Action generate() {
		return generate(maxValue, random);
	}
	
	public static Action generate(int maxValue, Random random) {
		int pos1 =  getNumber(random, maxValue, -1);
		int pos2 = getNumber(random, maxValue, pos1);
		return generate(pos1, pos2, maxValue);
	}
	
	public static Action generate(int pos1, int pos2, int maxValue) {
		int numberSize = Wrapper.byteSizePerNumber(maxValue);
		byte[] action = new byte[2 * numberSize];
		Wrapper.addWrappNumber(action, pos1, numberSize, 0);
		Wrapper.addWrappNumber(action, pos2, numberSize, 1);
		return new Action(action);
	}
	
	private static int getNumber(Random random, int maxValue, int lastNumber){
		int number = 0;
		do{
			number = random.nextInt(maxValue);
		}while(number > maxValue || number == lastNumber);
		return number;
	}
}
