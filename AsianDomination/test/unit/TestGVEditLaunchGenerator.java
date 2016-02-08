package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;

public class TestGVEditLaunchGenerator {
	private String outputPath;
	private LaunchDiagramGenerator ld;
	
	@Before
	public void setup() {
		String gvExecutable = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
		ld = new LaunchDiagramGenerator(gvExecutable);
	}
	
	@Test
	public void testGVEditLaunchSuccess() {
		String outputPath = "input_output/dotSuccessTest.gv";
		
		int actual = ld.RunGVEdit(outputPath, DiagramFileExtension.PDF);
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGVEditLaunchFailureBadPath() {
		outputPath = "input_output/dotIntentionalbadpathSuccessTest.gv";
		
		int actual = ld.RunGVEdit(outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
	
	@Test
	public void testGVEditLaunchFailureBadFile() {
		outputPath = "input_output/dotFailureTest";
		
		int actual = ld.RunGVEdit(outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
	
	@After
	public void tearDown() {
		ld = null;
	}
}
