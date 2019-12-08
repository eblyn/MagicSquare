package apcs;

public class IndiParams {
	protected int classifierNumber;
	protected int coveringTime;
	protected RuleParams ruleParams;
	
	public IndiParams(int classifierNumber, int coveringTime, RuleParams ruleParams) {
		this.classifierNumber = classifierNumber;
		this.coveringTime = coveringTime;
		this.ruleParams = ruleParams;
	}
	
	public RuleParams getRuleParams() {
		return ruleParams;
	}

	public int getCoveringTime() {
		return coveringTime;
	}

	public int getClassifierNumber() {
		return classifierNumber;
	}
}
