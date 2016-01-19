package unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import impl.RelationshipManager;
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
		
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.IMPLEMENTATION);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).size());
		assertTrue(_manager.containsRelationshipEdge(subClass, superClass, RelationshipType.IMPLEMENTATION));
	}
	
	@Test
	public void TestAddInheritanceRelationship() {
		String subClass = "Cat";
		String superClass = "Animal";
		
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.INHERITANCE);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.INHERITANCE).size());
		assertTrue(_manager.containsRelationshipEdge(subClass, superClass, RelationshipType.INHERITANCE));
	}
	
	@Test
	// good test for singleton pattern
	public void TestAddAssociationRelationship() {
		String subClass = "Cat";
		String superClass = "Cat";
		
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.ASSOCIATION);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.ASSOCIATION).size());
		assertTrue(_manager.containsRelationshipEdge(subClass, superClass, RelationshipType.ASSOCIATION));
	}
	
	@Test
	public void TestAddUsesRelationship() {
		String subClass = "Cat";
		String superClass = "Thread";
		
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.USES);
		
		assertEquals(1, _manager.getRelationshipEdges(RelationshipType.USES).size());
		assertTrue(_manager.containsRelationshipEdge(subClass, superClass, RelationshipType.USES));
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
	
	@Test
	public void TestRelationshipManagerContainsRelationship() {
		String subClass = "Cat";
		String superClass = "Animal";
		String badSuperClass = "Vehicle";
		
		_manager.addRelationshipEdge(subClass, subClass, RelationshipType.ASSOCIATION);
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.ASSOCIATION);
		_manager.addRelationshipEdge(subClass, superClass, RelationshipType.USES);
		
		assertTrue(_manager.containsRelationshipEdge(subClass, superClass, RelationshipType.ASSOCIATION));
		assertTrue(_manager.containsRelationshipEdge(subClass, subClass, RelationshipType.ASSOCIATION));
		assertFalse(_manager.containsRelationshipEdge(subClass, badSuperClass, RelationshipType.ASSOCIATION));
	}
	
	@After
	public void tearDown() {
		_manager = null;
	}
}
