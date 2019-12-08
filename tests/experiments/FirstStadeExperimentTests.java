package experiments;

import java.io.IOException;

import magicsquare.Adder;
import junit.framework.TestCase;

public class FirstStadeExperimentTests extends TestCase {

	public void solverTest(){
		Experiment experiment = runFirstExperiment(4);
		if(experiment != null)
			runSecondExperiment(3, experiment.getResults().getBestSolution());
		assertTrue(true);
	}
	
	public void solverTest1(){
		Experiment experiment = runFirstExperiment(4);
		if(experiment != null)
			runSecondExperiment(5, experiment.getResults().getBestSolution());
		assertTrue(true);
	}
	
	public Experiment runFirstExperiment(int approach) {
		try{
			ParamsReader params = new ParamsReader("/home/eblyn/workspace/Msc Workspace/APCS/param.simu");
			return runExperiment(approach, params);
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Experiment runExperiment(int approach, ParamsReader params) {
		Experiment experiment = new FirstStadeExperiment(new ParamsCreator(params));
		experiment.run();
		System.out.println(experiment.getResults().toString());
		System.out.println(experiment.getResults().getBestSolution());
		return experiment;
	}
	
	public Experiment runSecondExperiment(int approach, Adder adder) {
		try{
			ParamsReader params = new ParamsReader("/home/eblyn/workspace/Msc Workspace/APCS/param1.simu");
			return runExperiment(approach, params, adder);
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Experiment runExperiment(int approach, ParamsReader params, Adder adder) {
		Experiment experiment = new Experiment(new ParamsCreator(params), adder);
		experiment.run();
		System.out.println(experiment.getResults().toString());
		System.out.print(experiment.getResults().getBestSolution());
		return experiment;
	}
}
