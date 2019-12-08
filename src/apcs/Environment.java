package apcs;

public interface Environment {
	public void reset();
	public byte[] getSignal();
	public double getEvaluation();
	public void moveNext(Action action);
	public double getLastEvaluation(int trials);
	public Action getValidAction();
	public ActionFactory getActionFactory();
}
