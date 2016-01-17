package unit;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;

public class TestGVEditLaunchGenerator {
	private String inputPath;
	private String outputPath;
	
	@Test
	public void testGVEditLaunchSuccess() {
		inputPath = "input_output/dotSuccessTest";
		outputPath = "firstDotTest";
		
		int actual = LaunchDiagramGenerator.RunGVEdit(inputPath, outputPath, DiagramFileExtension.PDF);
		int expected = 0;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGVEditLaunchFailureBadPath() {
		inputPath = "input_output/dotIntentionalbadpathSuccessTest";
		outputPath = "badPathFailureUMLTest";
		
		int actual = LaunchDiagramGenerator.RunGVEdit(inputPath, outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
	
	@Test
	public void testGVEditLaunchFailureBadFile() {
		inputPath = "input_output/dotFailureTest";
		outputPath = "badFileFailureUMLTest";
		
		int actual = LaunchDiagramGenerator.RunGVEdit(inputPath, outputPath, DiagramFileExtension.PDF);
		int notExpected = 0;
		assertNotEquals(notExpected, actual);
	}
}
