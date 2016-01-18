package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import construction.IAddStrategy;
import construction.SDAddStrategy;
import input.SequenceInputCommand;
import output.IDiagramOutputStream;
import output.SDDiagramOutputStream;

public class TestSequenceInputCommand {
	private SequenceInputCommand _cmd;
	private String _diagramOutputPath;
	private String _asmOutputPath;
	private String clazz;
	private String method;
	private String parameters;
	
	@Before
	public void setup() {
		_diagramOutputPath = "input_output_test/UML";
		_asmOutputPath = "input_output_test/UML";
		clazz = "test/examples/Animal";
		method = "helloWorld";
		parameters = "(String, int)";
		
		_cmd = new SequenceInputCommand(_diagramOutputPath, _asmOutputPath, clazz, method, parameters, 5);
	}
	
	@Test
	public void TestSequenceInputCommandGetOutputPath() {
		assertEquals(_diagramOutputPath, _cmd.getDiagramOutputPath());
	}
	
	@Test
	public void TestSequenceInputCommandGetInputParams() {
		String[] params = _cmd.getInputParameters();
		assertEquals(clazz, params[0]);
		assertEquals(method, params[1]);
		assertEquals(parameters, params[2]);
		assertEquals("5", params[3]);
	}
	
	@Test
	public void TestSequenceInputCommandAddStrategy() {
		IAddStrategy addStrat = _cmd.getAddStrategy();
		assertTrue(addStrat instanceof SDAddStrategy);
	}
	
	@Test
	public void TestSeuqenceInputCommandOutputStrategy() {
		IDiagramOutputStream outStrat = _cmd.getOutputStream();
		assertTrue(outStrat instanceof SDDiagramOutputStream);
	}
	
	@After
	public void tearDown() {
		_cmd = null;
	}
}
