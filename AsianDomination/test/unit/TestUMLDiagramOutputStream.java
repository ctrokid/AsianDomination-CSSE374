package unit;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import impl.ClassField;
import impl.ClassMethod;
import impl.TargetClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import output.AbstractDiagramOutputStream;
import output.UMLDiagramOutputStream;
import visitor.IVisitor;
import visitor.Visitor;

public class TestUMLDiagramOutputStream {
	private OutputStream bytesOut;
	private IVisitor _visitor;
	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		bytesOut = new ByteArrayOutputStream();
		UMLDiagramOutputStream _outStreamVisitor = new UMLDiagramOutputStream("", new Visitor());
		
		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
		f.setAccessible(true);
		
		f.set(_outStreamVisitor, bytesOut);
		
		Field vis = AbstractDiagramOutputStream.class.getDeclaredField("_visitor");
		vis.setAccessible(true);
		
		_visitor = (IVisitor) vis.get(_outStreamVisitor);
	}
	
	@Test
	public void TestUMLVisitClass() {
		ITargetClass clazz = new TargetClass("problem/test");
		_visitor.visit(clazz);
		
		String expected = "test[\n\tstyle = solid, bgcolor = black, label = \"{test|";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLPostVisitClass() {
		ITargetClass clazz = new TargetClass("problem/test");
		_visitor.postVisit(clazz);
		
		String expected = "}\"\n]\n\n";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLVisitField() {
		IClassField field = new ClassField("_name", Opcodes.ACC_PROTECTED, "\\<String\\>", "Collection");
		_visitor.visit(field);
		
		String expected = "# _name : Collection\\<String\\>\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLVisitMethod() {
		IClassMethod method = new ClassMethod("helloWorld", "(Ljava/util/Collection;I)Ljava/lang/String;", Opcodes.ACC_PRIVATE, "double");
		_visitor.visit(method);
		
		String expected = "- helloWorld(Collection, int) : double\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestUMLPostVisitField() {
		IClassField field = new ClassField(null, 0, null, null);
		_visitor.postVisit(field);
		
		String expected = "|";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@After
	public void tearDown() {
		_visitor = null;
	}
}
