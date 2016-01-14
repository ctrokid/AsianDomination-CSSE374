package unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;

public class TestSDEditLaunchGenerator {
	private String inputPath;
	private String outputPath;
	
	@Test
	public void testSDEditLaunchSuccess() {
		inputPath = "input_output/sdEditSuccessTest";
		outputPath = "input_output/firstSequenceTest";
		
		int actual = LaunchDiagramGenerator.RunSDEdit(inputPath, outputPath, DiagramFileExtension.PDF);
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSDEditLaunchFailureBadInputPath() {
		inputPath = "input_output/sdThisISIntentionalFailureTest";
		outputPath = "input_output/badPathFailureSequenceTest";
		
		int actual = LaunchDiagramGenerator.RunSDEdit(inputPath, outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
	
	@Test
	public void testSDEditLaunchFailureBadSDFile() {
		inputPath = "input_output/sdEditFailureTest";
		outputPath = "input_output/badFileFailureSequenceTest";
		
		int actual = LaunchDiagramGenerator.RunSDEdit(inputPath, outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
}
