package unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;

public class TestSDEditLaunchGenerator {
	private String outputPath;
	private LaunchDiagramGenerator ld;
	
	@Before
	public void setup() {
		String sdExecutable = "lib/sdedit-4.01.jar";
		ld = new LaunchDiagramGenerator(sdExecutable);
	}
	
	@Test
	public void testSDEditLaunchSuccess() {
		outputPath = "input_output/sdEditSuccessTest.sd";
		
		int actual = ld.RunSDEdit(outputPath, DiagramFileExtension.PDF);
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSDEditLaunchFailureBadInputPath() {
		outputPath = "input_output/sdThisISIntentionalFailureTest";
		
		int actual = ld.RunSDEdit(outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
	
	@Test
	public void testSDEditLaunchFailureBadSDFile() {
		outputPath = "input_output/sdEditFailureTest";
		
		int actual = ld.RunSDEdit(outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
	
	@Test
	public void tearDown() {
		ld = null;
	}
}
