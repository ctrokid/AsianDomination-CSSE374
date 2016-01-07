package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

import Utils.DotClassUtils.RelationshipType;
import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.IRelationshipManager;
import api.ITargetClass;
import impl.ClassDeclaration;
import impl.ClassField;
import impl.ClassMethod;
import impl.RelationshipManager;
import impl.TargetClass;
import visitor.IVisitor;
import visitor.TargetClassOutputStream;

public class TestRelationshipManager {
	private IVisitor outStreamVisitor;
	private OutputStream bytesOut;
	private IRelationshipManager relationshipManager;
	
	@Before
	public void setUp() {
		bytesOut = new ByteArrayOutputStream();
		outStreamVisitor = new TargetClassOutputStream(bytesOut);
	}
	
	@Test
	public void testTwoClassesWithInheritance() {
		
		relationshipManager = new RelationshipManager(new String[] {"SubAge", "Age"});
		relationshipManager.addRelationshipEdge("SubAge", "Age", RelationshipType.INHERITANCE);
		
		relationshipManager.accept(outStreamVisitor);
		
		String written = bytesOut.toString();
		assertTrue(written.contains("SubAge -> Age"));
		assertTrue(written.contains("arrowhead = \"empty\""));
		assertTrue(written.contains("style = \"solid\""));
	}
	
	@Test
	public void testTwoClassesWithAssociation() {
		
		relationshipManager = new RelationshipManager(new String[] {"SubAge", "Age"});
		relationshipManager.addRelationshipEdge("SubAge", "Age", RelationshipType.ASSOCIATION);
		
		relationshipManager.accept(outStreamVisitor);
		
		String written = bytesOut.toString();
		assertTrue(written.contains("SubAge -> Age"));
		assertTrue(written.contains("arrowhead = \"vee\""));
		assertTrue(written.contains("style = \"solid\""));
	}
	
}
