package Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.junit.Before;
import org.junit.Test;

import impl.ClassMethod;
import visitor.IVisitor;
import visitor.TargetClassOutputStream;

public class test {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;

	@Before
	public final void setUp() {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new TargetClassOutputStream(bytesOut);
	}

	@Test
	public final void classMethodsTest() {
		ClassMethod cm = new ClassMethod("Animal", "", "", "String");
		cm.accept(outStreamVisitor);
		String expected =  " Animal() : String\\l";
		assertEquals(expected, bytesOut.toString());
		
	}
}
