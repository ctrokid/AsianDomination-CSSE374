package integrationTests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.junit.Before;
import org.junit.Test;

import impl.ClassMethod;
import visitor.IVisitor;
import visitor.UMLOutputStream;

public class TestClassMethod {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;

	@Before
	public final void setUp() {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new UMLOutputStream(bytesOut);
	}

	@Test
	public final void testAcceptStringReturnType() {
		ClassMethod cm = new ClassMethod("Animal", "", "", "String");
		cm.accept(outStreamVisitor);
		String expected =  " Animal() : String\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptVoidReturnType() {
		ClassMethod cm = new ClassMethod("People", "", "+", "void");
		cm.accept(outStreamVisitor);
		String expected =  "+ People() : void\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptBooleanReturnType() {
		ClassMethod cm = new ClassMethod("isStudent", "", "#", "boolean");
		cm.accept(outStreamVisitor);
		String expected =  "# isStudent() : boolean\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptCollectionReturnType() {
		ClassMethod cm = new ClassMethod("getStudents", "", "+", "Collection");
		cm.accept(outStreamVisitor);
		String expected =  "+ getStudents() : Collection\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public final void classAcceptParametersInMethod() {
		ClassMethod cm = new ClassMethod("getStudents", "String,int", "+", "Collection");
		cm.accept(outStreamVisitor);
		String expected =  "+ getStudents(String,int) : Collection\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
}
