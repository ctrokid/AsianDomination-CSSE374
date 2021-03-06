package integration;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

import api.IClassField;
import impl.ClassField;
import output.AbstractDiagramOutputStream;
import output.UMLDiagramOutputStream;
import utils.AsmClassUtils;
import visitor.IVisitor;

public class TestClassField {
	private AbstractDiagramOutputStream outStreamVisitor;
	private OutputStream bytesOut;
	private IVisitor _visitor;
	
	@Before
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
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
	public void testAcceptPrivateField() {
		IClassField field = new ClassField("firstField", Opcodes.ACC_PRIVATE, "", "string");
		field.accept(_visitor);
		
		String expected = "- firstField : string\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptProtectedField() {
		IClassField field = new ClassField("age", Opcodes.ACC_PROTECTED, "", "int");
		field.accept(_visitor);
		
		String expected = "# age : int\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptPublicField() {
		IClassField field = new ClassField("GPA", Opcodes.ACC_PUBLIC, "", "double");
		field.accept(_visitor);
		
		String expected = "+ GPA : double\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptClassFieldType() {
		IClassField field = new ClassField("GPA", Opcodes.ACC_PUBLIC, "", "Object");
		field.accept(_visitor);
		
		String expected = "+ GPA : Object\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptCollectionFieldType() {
		String signature = AsmClassUtils.parseSignature("Ljava/util/Collection<Ljava/lang/String;>;", true);
		IClassField field = new ClassField("classList", Opcodes.ACC_PUBLIC, "\\<" + signature + "\\>", "Collection");
		field.accept(_visitor);
		
		String expected = "+ classList : Collection\\<String\\>\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
	
	@Test
	public void testAcceptHashMapFieldType() {
		String signature = AsmClassUtils.parseSignature("Ljava/util/HashMap<Ljava/lang/String;Ljava/util/lang/Integer;>;", true);
		IClassField field = new ClassField("map", Opcodes.ACC_PUBLIC, "\\<" + signature + "\\>", "HashMap");
		field.accept(_visitor);
		
		String expected = "+ map : HashMap\\<String,Integer\\>\\l";
		String written = bytesOut.toString();
		assertEquals(expected, written);
	}
}
