package apcs;

import java.util.Random;

public class Bit {
	protected byte value;
	
	public Bit(byte value) {
		if(value != (byte) 1 && value != (byte) 0 && value != '*' )
			throw new IllegalArgumentException("" + value);
		this.value = value;
	}

	public Bit(double random, double wildProbability){
		if(random < wildProbability)
			value = '*';
		else 
			value = (random < 1 - (1 - wildProbability)/2) ? (byte) 1 : (byte) 0;
	}
	
	public void setValue(byte value) {
		this.value = value;
	}
	
	public byte getValue(){
		return value;
	}
	
	public void mute(double probability, double wildCardProb, Random random){
		double randomNumber = random.nextDouble();
		if((probability < randomNumber))
			return;
		randomNumber = random.nextDouble();
		if (randomNumber <= wildCardProb && value != '*') 
			value = '*';
		else {
			randomNumber = random.nextDouble();
			value = (value == (byte) 0 || ((value == '*') && randomNumber < 0.5)) ? 
					(byte)1 : (byte)0;
		}
	}
	
	public static Bit[] getCopy(Bit[] bits){
		Bit[] copy = new Bit[bits.length];
		for(int i = 0; i < bits.length; i++)
			copy[i] = new Bit(bits[i].getValue());
		return copy;
	}
	
	public String toString() {
		return (value == (byte)'*') ? "*" : "" + value;
	}
	
	public static String toString(Bit[] values) {
		String str = "";
		for(int i = 0; i < values.length; i++)
			str += values[i].toString();
		return str;
	}
	
	public static void fillRandom(Bit[] values, double wildProbability) {
		Random random = new Random();
		for(int i = 0; i < values.length; i++) 
			values[i] = new Bit(random.nextDouble(), wildProbability);
	}
	
	public boolean match(byte bit) {
		if (value == '*')
			return true;
		return value == bit;
	}
	
	public static Bit getWildCard() {
		return new Bit((byte)'*');
	}
}
