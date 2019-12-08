package heuristics.lines_columns;

public class ArrayDiferenceExtremes {
	protected int maxIndex;
	protected int minIndex;
	protected int maxValue;
	protected int minValue;
	
	public ArrayDiferenceExtremes(int[] values) {
		setValues(values);
	}

	private void setValues(int[] values) {
		if(values.length > 0)
			minValue = maxValue = values[0];	
		for(int i = 1; i < values.length; i++) {
			updateMaxIndex(i, values[i]);
			updateMinValue(i, values[i]);
		}
	}

	private void updateMinValue(int index, int newValue) {
		if(newValue < minValue){
			minIndex = index;
			minValue = newValue;
		}
	}

	private void updateMaxIndex(int index, int newValue) {
		if(newValue > maxValue) {
			maxIndex = index;
			maxValue = newValue;
		}
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public int getMinIndex() {
		return minIndex;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public int getMinValue() {
		return minValue;
	}
}
