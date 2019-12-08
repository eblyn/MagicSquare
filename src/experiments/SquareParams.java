package experiments;

public class SquareParams {
	protected int n;
	protected int cycles;
	protected int trials;
	
	public SquareParams(int n, int cycles, int trials) {
		this.n = n;
		this.cycles = cycles;
		this.trials = trials;
	}

	public int getN() {
		return n;
	}

	public int getCycles() {
		return cycles;
	}

	public int getTrials() {
		return trials;
	}	
}
