package unitTests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.junit.Before;
import org.junit.Test;

import impl.ClassMethod;
import visitor.IVisitor;
import visitor.TargetClassOutputStream;

public class TestClassMethod {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;

	@Before
	public final void setUp() {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new TargetClassOutputStream(bytesOut);
	}

	@Test
	public final void classMethodsTest1() {
		ClassMethod cm = new ClassMethod("Animal", "", "", "String");
		cm.accept(outStreamVisitor);
		String expected =  " Animal() : String\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	@Test
	public final void classMethodsTest2() {
		ClassMethod cm = new ClassMethod("People", "", "+", "void");
		cm.accept(outStreamVisitor);
		String expected =  "+ People() : void\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
	@Test
	public final void classMethodsTest3() {
		ClassMethod cm = new ClassMethod("isStudent", "", "-", "boolean");
		cm.accept(outStreamVisitor);
		String expected =  "- isStudent() : boolean\\l";
		String actual = bytesOut.toString();
		assertEquals(expected, actual);
	}
}
