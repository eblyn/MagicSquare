package apcs;

import java.util.Random;
import java.util.Vector;

public class Covering {
	protected double wildProbability;
	protected Random random;
	
	public Covering(double wildProbability) {
		this.wildProbability = wildProbability;
		this.random = new Random();
	}
	
	public void doCovering(byte[] signal, Rule rule, Action action) {
		rule.getCondition().covering(signal, wildProbability);
		//rule.setCondition(createCondition(signal));
		rule.setAction(action);
		rule.getCovering().restart(signal.length);
	}
	
	public Condition createCondition(byte[] signal){
		Bit[] condition = new Bit[signal.length];
		for(int i = 0; i < signal.length; i++){
			if(random.nextDouble() < wildProbability)
				condition[i] = Bit.getWildCard();
			else
				condition[i] = new Bit(signal[i]);
		}
		return new Condition(condition);
	}
	
	public Rule getCoveringRule(Individual individual, byte[] signal, Action action) {
		Vector<Integer> minMatchIndex = new Vector<Integer>();
		for(int i = 0; i < individual.size(); i++)
			analizeRule(minMatchIndex, individual, i);
		int pos = random.nextInt(minMatchIndex.size());
		int ruleIndex = minMatchIndex.elementAt(pos);
		doCovering(signal, individual.getRule(ruleIndex), action);
		return individual.getRule(ruleIndex);
	}
	
	private void analizeRule(Vector<Integer> minMatchIndex, Individual individual, int index) {
		int ruleValue = individual.getRule(index).covering.getTotalMatch();
		int vectorValue = Integer.MAX_VALUE;
		if(minMatchIndex.size() > 0) 
			vectorValue = individual.getRule(minMatchIndex.firstElement()).getCovering().getTotalMatch();
		if(vectorValue < ruleValue)
			return;
		if(ruleValue < vectorValue)
			minMatchIndex.removeAllElements();
		minMatchIndex.add(index);
	}
	
	public void newGeneration(GeneticAlgorithm genetic) {
		for(int i = 0; i < genetic.size(); i++)
			newGeneration(genetic.getIndividual(i));
	}
	
	public void newGeneration(Individual individual) {
		for(int i = 0; i < individual.size(); i++)
			individual.getRule(i).getCovering().nextGeneration();
	}
}
