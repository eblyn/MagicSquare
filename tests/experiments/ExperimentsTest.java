package experiments;

import junit.framework.TestCase;

public class ExperimentsTest extends TestCase {
	
	public void solverTest(){
		Experimentation.runExperiment("/home/eblyn/workspace/Msc Workspace/APCS/param.simu", null, null);
		assertTrue(true);
	}	
}