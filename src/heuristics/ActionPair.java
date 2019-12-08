package heuristics;

public class ActionPair {
	protected int pos1, pos2;

	public ActionPair(int pos1, int pos2) {
		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	public int getPos1() {
		return pos1;
	}

	public int getPos2() {
		return pos2;
	}
	
	@Override
	public boolean equals(Object pair) {
		try{
			if(pos1 == ((ActionPair)pair).pos1 && pos2 == ((ActionPair)pair).pos2)
				return true;
			if(pos2 == ((ActionPair)pair).pos1 && pos1 == ((ActionPair)pair).pos2)
				return true;
		}catch(Exception e){
			return super.equals(pair);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "[" + pos1 + "," + pos2 + "]";
	}
}
