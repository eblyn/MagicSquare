package apcs;

import java.util.Random;

public class Action {
	protected Bit[] action;
	
	public Action(Bit[] action){
		this.action = action;
	}
	
	public Action(byte[] action) {
		this.action = new Bit[action.length];
		for(int i = 0; i < action.length; i++)
			this.action[i] = new Bit(action[i]);
	}
	
	public Action(int size) {
		action = new Bit[size];
		Bit.fillRandom(action, 0);
	}
	
	@Override
	public Action clone() {
		return new Action(Bit.getCopy(action));
	}
	
	public Bit[] getAction() {
		return action;
	}
	
	public void mute(double probability) {
		Random random = new Random();
		for(int i = 0; i < action.length; i++) 
			action[i].mute(probability, 0, random);
	}
	
	public String toString(){
		return Bit.toString(action);
	}

	public int size() {
		return action.length;
	}
}
