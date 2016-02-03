package unit;

import static org.junit.Assert.*;

import java.util.Arrays;

import input.UMLInputCommand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import output.IDiagramOutputStream;
import output.UMLDiagramOutputStream;
import construction.IAddStrategy;
import construction.UMLAddStrategy;
import fake.FakeProjectModel;

public class TestUMLInputCommand {
	private UMLInputCommand _cmd;
	private String _diagramOutputPath;
	private String _asmOutputPath;
	
	@Before
	public void setup() {
		_cmd = null;
		_diagramOutputPath = "input_output_test/UML";
		_asmOutputPath = "input_output_test/UML";
	}
	
	@Test
	public void TestUMLInputCommandGetDiagramOutputPath() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes, null, null);
		assertEquals(_diagramOutputPath, _cmd.getDiagramOutputPath());
	}
	
	@Test
	public void TestUMLInputCommandGetAsmOutputPath() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes, null, null);
		assertEquals(_asmOutputPath, _cmd.getAsmOutputPath());
	}
	
	@Test
	public void TestUMLInputCommandAddStrategy() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes, null, null);
		
		IAddStrategy addStrat = _cmd.getAddStrategy(new FakeProjectModel(_cmd));
		assertTrue(addStrat instanceof UMLAddStrategy);
	}
	
	@Test
	public void TestUMLInputCommandOutputStrategy() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes, null, null);
		
		IDiagramOutputStream outStrat = _cmd.getOutputStream();
		assertTrue(outStrat instanceof UMLDiagramOutputStream);
	}
	
	@Test
	public void TestUMLInputCommandGetInputParams() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes, null, null);
		
		assertEquals(Arrays.asList(classes), Arrays.asList(_cmd.getInputParameters()));
	}
	
	@After
	public void tearDown() {
		_cmd = null;
		_diagramOutputPath = null;
		_asmOutputPath = null;
	}
}
