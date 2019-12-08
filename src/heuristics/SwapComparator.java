package heuristics;

import java.util.Comparator;

public class SwapComparator implements Comparator<SwapValues> {

	@Override
	public int compare(SwapValues arg0, SwapValues arg1) {
		if(arg0.equals(arg1))
			return 0;
		if(arg0.getReward() == arg1.getReward())
			return compareIfEqualReward(arg0, arg1);
		return arg0.getReward() > arg1.getReward() ? -1 : 1;
	}

	private int compareIfEqualReward(SwapValues arg0, SwapValues arg1) {
		if(arg0.getPos1() == arg1.getPos1())
			return arg0.getPos2() < arg1.getPos2() ? -1 : 1;
		return arg0.getPos1() < arg1.getPos1() ? -1 : 1;
	}

}
