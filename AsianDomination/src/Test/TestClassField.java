package Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import api.IClassField;
import impl.ClassField;
import visitor.IVisitor;
import visitor.TargetClassOutputStream;

public class TestClassField {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;
	
	@Before
	public void setUp() {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new TargetClassOutputStream(bytesOut);
	}
	
	@Test
	public void testFieldOutputStreamOne() {
		IClassField field = new ClassField("firstField", "-", "not sure", "string");
		field.accept(outStreamVisitor);
		
		String expected = "- firstField : string\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testFieldOutputStreamTwo() {
		IClassField field = new ClassField("age", "#", "not sure", "int");
		field.accept(outStreamVisitor);
		
		String expected = "# age : int\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testFieldOutputStreamThree() {
		IClassField field = new ClassField("GPA", "+", "not sure", "double");
		field.accept(outStreamVisitor);
		
		String expected = "+ GPA : double\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
}
