package apcs;

import java.util.Random;

public class Condition {

	protected Bit[] condition;
	
	public Condition(byte[] condition){
		this.condition = new Bit[condition.length];
		for(int i = 0; i < condition.length; i++)
			this.condition[i] = new Bit(condition[i]);
	}
	
	public Condition(int size, double wildProbability) {
		condition = new Bit[size];
		Bit.fillRandom(condition, wildProbability);
	}
	
	public Condition(Bit[] condition) {
		this.condition = condition;
	}
	
	public boolean match(byte[] bytes) {
		for(int i = 0; i < bytes.length; i++)
			if(!condition[i].match(bytes[i]))
				return false;
		return true;
	}
	
	@Override
	public Condition clone() {
		return new Condition(Bit.getCopy(condition));
	}
	
	public Bit[] getCondition() {
		return condition;
	}
	
	public void mute(double probability, double wildCardProb) {
		Random random = new Random();
		for(int i = 0; i < condition.length; i++) 
			condition[i].mute(probability, wildCardProb, random);	
	}
	
	public String toString(){
		return Bit.toString(condition);
	}
	
	public int size() {
		return condition.length;
	}
	
	public void covering(byte[] signal, double wildProbability) {
		Random random = new Random();
		for(int i = 0; i < condition.length; i++)
			if(!condition[i].match(signal[i]))
				condition[i].mute(1, wildProbability, random);
	}
}
