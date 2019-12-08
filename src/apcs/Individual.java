package apcs;

import java.util.Random;
import java.util.Vector;

public class Individual {
	protected Vector<Rule> ruleSet;
	protected Random random;
	protected double wildProbability;
	protected double strength;
	
	public Individual(Vector<Rule> ruleSet, double wildProbability, double strength){
		this.ruleSet = ruleSet;
		random = new Random();
		this.wildProbability = wildProbability;
		this.strength = strength;
	}
	
	public Individual(Vector<Rule> ruleset, double wildProbability){
		this(ruleset, wildProbability, 0);
	}
	
	public Individual(IndiParams indiParams) {
		this(indiParams, true);
	}
	
	public Individual(IndiParams individualParams, boolean createRules) {
		this(generateRules(individualParams, createRules), individualParams.getRuleParams().getWildcardProbability());
	}
	
	private static Vector<Rule> generateRules(IndiParams individualParams, boolean createRules){
		Vector<Rule> ruleSet = new Vector<Rule>();
		if(createRules)
			fillRules(ruleSet, individualParams);
		return ruleSet;
	} 
	
	private static void fillRules(Vector<Rule> ruleSet2, IndiParams individualParams) {
		RuleParams ruleParams = individualParams.getRuleParams();
		for(int i = 0; i < individualParams.getClassifierNumber(); i++){ 
			Condition condition = new Condition(ruleParams.getConditionSize(), ruleParams.getWildcardProbability());
			Action action = ruleParams.getActionFactory().generate();
			ruleSet2.add(new Rule(condition, action, individualParams.getCoveringTime()));
		}		
	}

	public Rule getRule(byte[] signal) {
		Vector<Rule> matchingRules = matchingRules(signal);
		if(matchingRules.size() == 0) 
			return null;
		int index =	random.nextInt(matchingRules.size());
		return matchingRules.elementAt(index);
	}
	
	public Vector<Rule> matchingRules(byte[] signal){
		Vector<Rule> matchingRules = new Vector<Rule>();
		for(int i = 0; i < ruleSet.size(); i ++)
			if(ruleSet.elementAt(i).match(signal))
				matchingRules.add(ruleSet.elementAt(i));
		return matchingRules;
	}
	
	public int size() {
		return ruleSet.size();
	}

	public Rule getRule(int index) {
		return ruleSet.elementAt(index);
	}
	
	@Override
	protected Individual clone(){
		Vector<Rule> rulesClon = new Vector<Rule>();
		for(int i = 0; i < ruleSet.size(); i++)
			rulesClon.add(ruleSet.elementAt(i).clone());
		return new Individual(rulesClon, wildProbability, strength);
	}
	
	public void crossover(Individual individual, int ruleIndex, int position){
		ruleCrossover(individual, ruleIndex);
		Rule crossRule = individual.getRule(ruleIndex);
		ruleSet.elementAt(ruleIndex).crossover(crossRule, position);
		updateCrossoverStrength(individual);
	}

	private void ruleCrossover(Individual individual, int ruleIndex) {
		for(int i = ruleIndex + 1; i < size(); i++) {
			Rule aux = ruleSet.elementAt(i);
			ruleSet.set(i, individual.ruleSet.elementAt(i));
			individual.ruleSet.set(i, aux);
		}
	}
	
	private void updateCrossoverStrength(Individual other) {
		double newStrength = (other.getStrength() + getStrength())/2;
		setStrength(newStrength);
		other.setStrength(newStrength);
	}
	
	public void mute(double probability) {
		for(int i = 0; i < ruleSet.size(); i++){
			ruleSet.elementAt(i).getCondition().mute(probability, wildProbability);
			ruleSet.elementAt(i).getAction().mute(probability);
		}
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i < ruleSet.size(); i ++)
			str += ruleSet.elementAt(i).toString() + '\n';
		return str;
	}
	
	public void setStrength(double value) {
		strength = value;
	}
	
	public double getStrength() {
		return strength;
	}
	
	public int getRuleSize() {
		return ruleSet.elementAt(0).size();
	}
	
	public void restart(){
		for(int i = 0; i < ruleSet.size(); i++)
			ruleSet.elementAt(i).restart(wildProbability);
		this.strength = 0;
	}
}
