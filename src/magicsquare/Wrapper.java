package magicsquare;
import apcs.Bit;

public class Wrapper {
	public static int byteSizePerNumber(int maxNumber){		
		return (int) (Math.log(maxNumber)/Math.log(2)) + 1;
	}
	
	public static byte[] wrappArray(int[] values, int maxValue) {
		int numberSize = byteSizePerNumber(maxValue);
		byte[] wrapp = new byte[values.length * numberSize];
		for(int i = 0; i < values.length; i ++)
			addWrappNumber(wrapp, values[i], numberSize, i);
		return wrapp;
	}
	
	public static byte[] wrapp(Square square) {
		int size = square.getValues().n() * square.getValues().n();
		int numberSize = byteSizePerNumber(size);
		byte[] wrapp = new byte[size * numberSize];
		for(int i = 0; i < size; i ++)
			addWrappNumber(wrapp, square.elementAt(i), numberSize, i);
		return wrapp;
	}
	
	public static void addWrappNumber(byte[] wrapp, int number, int size, int index) {
		int rest = number;
		for(int i = 1; i <= size; i++) {
			wrapp[size - i + index*size] = (byte)(rest % 2);
			rest /= 2;
		}
	}
	
	public static int unwrappInt(Bit[] values, int init, int size){
		int number = 0;
		for(int i = init; i < init + size; i++)
			number = number * 2 + values[i].getValue();
		return number;
	}
	
	public static byte[] wrappNumber(int n, int size) {
		byte[] wrapp = new byte[size];
		int rest = n;
		for(int i = 1; i <= wrapp.length; i++) {
			wrapp[wrapp.length - i] = (byte)(rest % 2);
			rest /= 2;
		}
		return wrapp;
	}
}
