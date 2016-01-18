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
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes);
		assertEquals(_diagramOutputPath, _cmd.getDiagramOutputPath());
	}
	
	@Test
	public void TestUMLInputCommandGetAsmOutputPath() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes);
		assertEquals(_asmOutputPath, _cmd.getAsmOutputPath());
	}
	
	@Test
	public void TestUMLInputCommandAddStrategy() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes);
		
		IAddStrategy addStrat = _cmd.getAddStrategy();
		assertTrue(addStrat instanceof UMLAddStrategy);
	}
	
	@Test
	public void TestUMLInputCommandOutputStrategy() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes);
		
		IDiagramOutputStream outStrat = _cmd.getOutputStream();
		assertTrue(outStrat instanceof UMLDiagramOutputStream);
	}
	
	@Test
	public void TestUMLInputCommandGetInputParams() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, classes);
		
		assertEquals(Arrays.asList(classes), Arrays.asList(_cmd.getInputParameters()));
	}
	
	@After
	public void tearDown() {
		_cmd = null;
		_diagramOutputPath = null;
		_asmOutputPath = null;
	}
}
