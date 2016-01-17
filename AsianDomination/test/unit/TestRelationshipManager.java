package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import impl.RelationshipManager;
import impl.RelationshipManager.RelationshipEdge;
import utils.DotClassUtils.RelationshipType;

public class TestRelationshipManager {
	private RelationshipManager _manager;
	
	@Before
	public void setup() {
		_manager = new RelationshipManager();
	}
	
	@Test
	public void TestAddInterfaceRelationship() {
		String subClass = "Cat";
		String superClass = "Animal";
		
		RelationshipEdge edge = _manager.new RelationshipEdge(subClass, superClass);
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.IMPLEMENTATION);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).size());
		assertTrue(_manager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).contains(edge));
	}
	
	@Test
	public void TestAddInheritanceRelationship() {
		String subClass = "Cat";
		String superClass = "Animal";
		
		RelationshipEdge edge = _manager.new RelationshipEdge(subClass, superClass);
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.INHERITANCE);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.INHERITANCE).size());
		assertTrue(_manager.getRelationshipEdges(RelationshipType.INHERITANCE).contains(edge));
	}
	
	@Test
	// good test for singleton pattern
	public void TestAddAssociationRelationship() {
		String subClass = "Cat";
		String superClass = "Cat";
		
		RelationshipEdge edge = _manager.new RelationshipEdge(subClass, superClass);
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.ASSOCIATION);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.ASSOCIATION).size());
		assertTrue(_manager.getRelationshipEdges(RelationshipType.ASSOCIATION).contains(edge));
	}
	
	@Test
	public void TestAddUsesRelationship() {
		String subClass = "Cat";
		String superClass = "Thread";
		
		RelationshipEdge edge = _manager.new RelationshipEdge(subClass, superClass);
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.USES);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.USES).size());
		assertTrue(_manager.getRelationshipEdges(RelationshipType.USES).contains(edge));
	}
	
	@Test
	public void TestNoRelationshipDuplicates() {
		String subClass = "Cat";
		String superClass = "Animal";
		
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.ASSOCIATION);
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.ASSOCIATION).size());

		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.INHERITANCE);
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.INHERITANCE).size());
	
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.ASSOCIATION);
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.ASSOCIATION).size());

		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.INHERITANCE);
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.INHERITANCE).size());
	}
	
	@After
	public void tearDown() {
		_manager = null;
	}
}
