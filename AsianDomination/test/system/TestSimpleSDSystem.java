package system;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import api.IProjectModel;
import construction.SDAddStrategy;
import fake.FakeInputCommand;
import fake.FakeSDDiagramOutputStream;
import impl.ProjectModel;
import input.InputCommand;
import output.AbstractDiagramOutputStream;
import output.IDiagramOutputStream;
import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;

@SuppressWarnings("unused")
public class TestSimpleSDSystem {
private InputCommand _cmd;
	
	@Before
	public void setup() {
		_cmd = null;
	}
	
//	@Test
//	public void TestSDSimpleExample() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		String initialClass = "SequenceDiagramPack/driver";
//		String initialMethod = "main";
//		String initialParams = "String[]";
//		String maxDepth = "3";
//		
//		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
//		IDiagramOutputStream out = new FakeSDDiagramOutputStream("", initialClass, initialMethod, initialParams, Integer.parseInt(maxDepth));
//		
//		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
//		f.setAccessible(true);
//		f.set(out, bytesOut);
//		
//		FakeInputCommand cmd = new FakeInputCommand("", "", new String[] {
//				initialClass, initialMethod, initialParams, maxDepth
//		});
//		
//		cmd.setOutputStream(out);
//		cmd.setAddStrategy(new SDAddStrategy(Integer.parseInt(maxDepth)));
//		
//		IProjectModel model = new ProjectModel(cmd);
//		model.parseModel();
//		
//		String actual = bytesOut.toString();
//		
//		System.out.println(actual);
//	}
	
//	@Test
//	public void TestGenerationSD() {
//		assertEquals(0, LaunchDiagramGenerator.RunSDEdit("input_output/firstSDTest", "input_output/firstSDTest", DiagramFileExtension.PDF));	
//	}
}
