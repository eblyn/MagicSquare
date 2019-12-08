package experiments;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import magicsquare.Adder;
import magicsquare.Evaluator;

public class Experimentation {
	
	public static void runExperiment(String file, Adder adder, String output) {
		try{
			ParamsReader params = new ParamsReader(file);
			print(output, params.toString());
			runExperiment(params, adder, output);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Experiment runExperiment(ParamsReader params, Adder adder, String output) {
		ParamsCreator paramsCreator = new ParamsCreator(params);
		ExperimentsParams expParams = paramsCreator.createExperimentParams();
		Experiment experiment = createExperiment(paramsCreator, adder, expParams);
		runExperiment(experiment, expParams, output);
		return experiment;
	}
	
	public static Experiment createExperiment(ParamsCreator params, Adder adder, ExperimentsParams expParams){
		if(expParams.isTwoStade())
			return new FirstStadeExperiment(params);
		if(adder != null)
			return new Experiment(params, adder);
		return new Experiment(params);
	}
	
	public static void runExperiment(Experiment experiment, ExperimentsParams expParams, String output) {
		experiment.run();
		printResults(experiment, output);
		if(expParams.isTwoStade() && experiment.meetCondition() && Evaluator.evaluate(experiment.getResults().getBestSolution()) != 0){
			print(output, ",");
			Adder adder = experiment.getResults().getBestSolution();
			runExperiment(expParams.getSecondParamsFile(), adder, output);
		}
		else
			print(output, "\n");
	}
	
	public static void printResults(Experiment experiment, String output) {
		print(output,experiment.getResults().lastResult());
		print(output, "," + experiment.meetCondition());
		print(output, "," + experiment.getExtraOutput());
		System.out.println(experiment.getExtraOutput());
		System.out.println(experiment.getResults().toString());
		System.out.println(experiment.getResults().getBestSolution());
	}
	
	public static void printLast(ParamsReader params, Experiment experiment, String output)  throws IOException{
		OutputStream out = new FileOutputStream(new File(output), true);
		PrintStream printer = new PrintStream(out);
		printer.print(params.toString());
		printer.println(experiment.getResults().lastResult());
		printer.close();
		out.close();
	}
	
	public static void print(String output, String value) {
		if(output == null || output == "")
			return;
		try{
		OutputStream out = new FileOutputStream(new File(output), true);
		PrintStream printer = new PrintStream(out);
		printer.print(value);
		printer.close();
		out.close();
		} catch(IOException e) {}
	}
}
