package integration;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import impl.ClassMethod;
import output.AbstractDiagramOutputStream;
import output.UMLDiagramOutputStream;
import visitor.IVisitor;

public class TestClassMethod {
	private AbstractDiagramOutputStream outStreamVisitor;
	private OutputStream bytesOut;
	private IVisitor _visitor;

	@Before
	public final void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new UMLDiagramOutputStream(new Properties());
		
		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
		f.setAccessible(true);
		
		f.set(outStreamVisitor, bytesOut);
		
		Field vis = AbstractDiagramOutputStream.class.getDeclaredField("_visitor");
		vis.setAccessible(true);
		
		_visitor = (IVisitor) vis.get(outStreamVisitor);
	}

	@Test
	public final void testAcceptStringReturnType() {
		ClassMethod cm = new ClassMethod("Animal", "", Opcodes.ACC_PRIVATE, "String");
		cm.accept(_visitor);
		String expected =  "- Animal() : String\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptVoidReturnType() {
		ClassMethod cm = new ClassMethod("People", "", Opcodes.ACC_PUBLIC, "void");
		cm.accept(_visitor);
		String expected =  "+ People() : void\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptBooleanReturnType() {
		ClassMethod cm = new ClassMethod("isStudent", "", Opcodes.ACC_PROTECTED, "boolean");
		cm.accept(_visitor);
		String expected =  "# isStudent() : boolean\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptCollectionReturnType() {
		ClassMethod cm = new ClassMethod("getStudents", "", Opcodes.ACC_PUBLIC, "Collection");
		cm.accept(_visitor);
		String expected =  "+ getStudents() : Collection\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptParametersInMethod() {
		ClassMethod cm = new ClassMethod("getStudents", "(Ljava/util/Collection;I)Ljava/lang/String;", Opcodes.ACC_PUBLIC, "Collection");
		cm.accept(_visitor);
		String expected =  "+ getStudents(Collection, int) : Collection\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
}
