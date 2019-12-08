package heuristics;

public class SwapValues {
	protected int pos1;
	protected int pos2;
	protected int reward;
	
	public SwapValues(int pos1, int pos2, int reward) {
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.reward = reward;
	}
	
	public int getPos1() {
		return pos1;
	}
	
	public int getPos2() {
		return pos2;
	}
	
	public int getReward() {
		return reward;
	}
	
	@Override
	public boolean equals(Object obj) {
		try{
			SwapValues other = (SwapValues) obj;
			return other.pos1 == pos1 && other.pos2 == pos2;
		}catch(Exception e){
			return super.equals(obj);
		}
	}
	
	@Override
	public String toString() {
		return "[" + pos1 + "," + pos2 + "," + reward + "]";
	}
}
