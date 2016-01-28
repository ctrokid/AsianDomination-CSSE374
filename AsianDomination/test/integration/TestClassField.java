package integration;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

import api.IClassField;
import impl.ClassField;
import output.AbstractDiagramOutputStream;
import output.UMLDiagramOutputStream;
import utils.AsmClassUtils;
import visitor.IVisitor;
import visitor.Visitor;

public class TestClassField {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;
	
	@Before
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new UMLDiagramOutputStream("", new Visitor());
		
		Field f = AbstractDiagramOutputStream.class.getDeclaredField("_outputStream");
		f.setAccessible(true);
		
		f.set(outStreamVisitor, bytesOut);
	}
	
	@Test
	public void testAcceptPrivateField() {
		IClassField field = new ClassField("firstField", Opcodes.ACC_PRIVATE, "", "string");
		field.accept(outStreamVisitor);
		
		String expected = "- firstField : string\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptProtectedField() {
		IClassField field = new ClassField("age", Opcodes.ACC_PROTECTED, "", "int");
		field.accept(outStreamVisitor);
		
		String expected = "# age : int\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptPublicField() {
		IClassField field = new ClassField("GPA", Opcodes.ACC_PUBLIC, "", "double");
		field.accept(outStreamVisitor);
		
		String expected = "+ GPA : double\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptClassFieldType() {
		IClassField field = new ClassField("GPA", Opcodes.ACC_PUBLIC, "", "Object");
		field.accept(outStreamVisitor);
		
		String expected = "+ GPA : Object\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptCollectionFieldType() {
		String signature = AsmClassUtils.parseSignature("Ljava/util/Collection<Ljava/lang/String;>;", true);
		IClassField field = new ClassField("classList", Opcodes.ACC_PUBLIC, "\\<" + signature + "\\>", "Collection");
		field.accept(outStreamVisitor);
		
		String expected = "+ classList : Collection\\<String\\>\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptHashMapFieldType() {
		String signature = AsmClassUtils.parseSignature("Ljava/util/HashMap<Ljava/lang/String;Ljava/util/lang/Integer;>;", true);
		IClassField field = new ClassField("map", Opcodes.ACC_PUBLIC, "\\<" + signature + "\\>", "HashMap");
		field.accept(outStreamVisitor);
		
		String expected = "+ map : HashMap\\<String, Integer\\>\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
}
