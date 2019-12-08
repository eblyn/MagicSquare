package apcs;

public class RuleParams {

	protected double wildcardProbability;
	protected ActionFactory actionFactory;
	protected int conditionSize;
	
	public RuleParams(double wildcardProbability, ActionFactory actionFactory,
			int conditionSize) {
		this.wildcardProbability = wildcardProbability;
		this.actionFactory = actionFactory;
		this.conditionSize = conditionSize;
	}
	
	public double getWildcardProbability() {
		return wildcardProbability;
	}
	public ActionFactory getActionFactory() {
		return actionFactory;
	}
	public int getConditionSize() {
		return conditionSize;
	}
}
