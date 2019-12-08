package experiments;

import solvers.SolverName;

public class ExperimentsParams {
	protected boolean twoStade;
	protected SolverName approach;
	protected String secondParamsFile;
	
	public ExperimentsParams(boolean twoStade, SolverName approach,
			String secondParamsFile) {
		this.twoStade = twoStade;
		this.approach = approach;
		this.secondParamsFile = secondParamsFile;
	}

	public boolean isTwoStade() {
		return twoStade;
	}

	public SolverName getApproach() {
		return approach;
	}

	public String getSecondParamsFile() {
		return secondParamsFile;
	}
}
