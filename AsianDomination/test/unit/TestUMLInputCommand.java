package unit;

import static org.junit.Assert.*;

import java.util.Arrays;

import input.UMLInputCommand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import output.IDiagramOutputStream;
import output.UMLDiagramOutputStream;
import visitor.Visitor;
import construction.IAddStrategy;
import construction.UMLAddStrategy;
import fake.FakeProjectModel;

public class TestUMLInputCommand {
	private UMLInputCommand _cmd;
	private IDiagramOutputStream _diagramOutputStream;
	
	@Before
	public void setup() {
		_cmd = null;
		_diagramOutputStream = new UMLDiagramOutputStream("input_output_test/UML", "", new Visitor());
	}
	
	@Test
	public void TestUMLInputCommandAddStrategy() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputStream, classes, null, null);
		
		IAddStrategy addStrat = _cmd.getAddStrategy(new FakeProjectModel(_cmd));
		assertTrue(addStrat instanceof UMLAddStrategy);
	}
	
	@Test
	public void TestUMLInputCommandOutputStrategy() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputStream, classes, null, null);
		
		IDiagramOutputStream outStrat = _cmd.getDiagramOutputStream();
		assertTrue(outStrat instanceof UMLDiagramOutputStream);
	}
	
	@Test
	public void TestUMLInputCommandGetInputParams() {
		String[] classes = new String[] {
				"test/examples/Animal"
		};
		_cmd = new UMLInputCommand(_diagramOutputStream, classes, null, null);
		
		assertEquals(Arrays.asList(classes), Arrays.asList(_cmd.getInputParameters()));
	}
	
	@After
	public void tearDown() {
		_cmd = null;
		_diagramOutputStream = null;
	}
}
