package apcs;

import java.util.Comparator;

public class IndividualComparator implements Comparator<Individual> {

	@Override
	public int compare(Individual indi0, Individual indi1) {
		if (indi0.equals(indi1))
			return 0;
		return (indi0.getStrength() < indi1.getStrength()) ? 1 : -1;
	}
}
