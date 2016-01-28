package integration;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import impl.ClassMethod;
import output.AbstractDiagramOutputStream;
import output.UMLDiagramOutputStream;
import visitor.IVisitor;
import visitor.Visitor;

public class TestClassMethod {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;

	@Before
	public final void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new UMLDiagramOutputStream("", new Visitor());
		
		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
		f.setAccessible(true);
		
		f.set(outStreamVisitor, bytesOut);
	}

	@Test
	public final void testAcceptStringReturnType() {
		ClassMethod cm = new ClassMethod("Animal", "", Opcodes.ACC_PRIVATE, "String");
		cm.accept(outStreamVisitor);
		String expected =  "- Animal() : String\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptVoidReturnType() {
		ClassMethod cm = new ClassMethod("People", "", Opcodes.ACC_PUBLIC, "void");
		cm.accept(outStreamVisitor);
		String expected =  "+ People() : void\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptBooleanReturnType() {
		ClassMethod cm = new ClassMethod("isStudent", "", Opcodes.ACC_PROTECTED, "boolean");
		cm.accept(outStreamVisitor);
		String expected =  "# isStudent() : boolean\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptCollectionReturnType() {
		ClassMethod cm = new ClassMethod("getStudents", "", Opcodes.ACC_PUBLIC, "Collection");
		cm.accept(outStreamVisitor);
		String expected =  "+ getStudents() : Collection\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptParametersInMethod() {
		ClassMethod cm = new ClassMethod("getStudents", "(Ljava/util/Collection;I)Ljava/lang/String;", Opcodes.ACC_PUBLIC, "Collection");
		cm.accept(outStreamVisitor);
		String expected =  "+ getStudents(Collection, int) : Collection\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
}
