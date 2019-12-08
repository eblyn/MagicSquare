package apcs;

public class Rule {
	protected Condition condition;
	protected Action action;
	protected RuleCovering covering;
	
	public Rule(Condition condition, Action action, int coveringTime) {
		this.condition = condition;
		this.action = action;
		covering = new RuleCovering(coveringTime);
	}
	
	public Condition getCondition() { return condition; }
	
	public void setCondition(Condition condition) {this.condition = condition; }

	public Action getAction() { return action; }

	public void setAction(Action action) { this.action = action; }
	
	@Override
	protected Rule clone() { return new Rule(condition.clone(), action.clone(), covering.getCoveringTime()); }
	
	public String toString(){
		return condition.toString() + ' ' + action.toString();
	}
	
	public int size() { return condition.size() + action.size();}
	
	public Bit getBit(int index) {
		if (index >= size())
			throw new IllegalArgumentException();
		if(index < condition.size())
			return condition.getCondition()[index];
		return action.getAction()[index - condition.size()];
	}
	
	public void crossover(Rule crossRule, int position) {
		for(int i = position; i < size(); i++) {
			byte value = getBit(i).getValue();
			byte crossValue = crossRule.getBit(i).getValue();
			getBit(i).setValue(crossValue);
			crossRule.getBit(i).setValue(value);
		}
	}
	
	public boolean match(byte[] signal) {
		boolean match = getCondition().match(signal);
		if (match)
			covering.incrementMatch();
		return match;
	}
	
	public RuleCovering getCovering() { return covering; }
	
	public void restart(double wildcardProbablity){
		this.condition = new Condition(condition.size(), wildcardProbablity);
		this.action = new Action(action.size());
		this.covering = new RuleCovering(covering.getCoveringTime());
	}
}
