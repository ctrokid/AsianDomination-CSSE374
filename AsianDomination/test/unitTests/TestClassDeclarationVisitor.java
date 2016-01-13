package unitTests;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.objectweb.asm.Opcodes;

import api.IClassDeclaration;
import api.IRelationshipManager;
import api.ITargetClass;
import asm.visitor.ClassDeclarationVisitor;
import impl.RelationshipManager;
import impl.TargetClass;
import utils.DotClassUtils.RelationshipType;

@SuppressWarnings("deprecation")
public class TestClassDeclarationVisitor {
	private static final int VERSION = 51;
	private static final int ACCESS = 33;
	
	private IRelationshipManager _relationshipManager;
	private ITargetClass _targetClass;
	private String[] _classList;
	private ClassDeclarationVisitor _visitor;
	
	@Before
	public void setup() {
		// the / separated package is generated by ASM
		_classList = new String[] {
				"headfirst/factory/pizzaaf/Cheese",
				"headfirst/factory/pizzaaf/ChicagoPizzaIngredientFactory",
				"headfirst/factory/pizzaaf/Clams",
				"headfirst/factory/pizzaaf/FreshClams",
				"headfirst/factory/pizzaaf/FrozenClams",
				"headfirst/factory/pizzaaf/MarinaraSauce",
				"headfirst/factory/pizzaaf/MozzarellaCheese",
				"headfirst/factory/pizzaaf/NYPizzaIngredientFactory",
				"headfirst/factory/pizzaaf/PizzaIngredientFactory",
				"headfirst/factory/pizzaaf/PlumTomatoSauce",
				"headfirst/factory/pizzaaf/ReggianoCheese",
				"headfirst/factory/pizzaaf/Sauce",
				"headfirst/factory/pizzaaf/ThickCrustDough",
				"headfirst/factory/pizzaaf/ThinCrustDough",
				"headfirst/factory/pizzafm/NYPizzaStore",
				"headfirst/factory/pizzafm/IPizzaStore"
		};
		
		// the . separated package is from the client and PackageInspector class
		_relationshipManager = new RelationshipManager(new String[] {
				"headfirst.factory.pizzaaf.Cheese",
				"headfirst.factory.pizzaaf.ChicagoPizzaIngredientFactory",
				"headfirst.factory.pizzaaf.Clams",
				"headfirst.factory.pizzafm.NYPizzaStore",
				"headfirst.factory.pizzafm.IPizzaStore"
		});
		
		_targetClass = new TargetClass();
		_visitor = new ClassDeclarationVisitor(Opcodes.ASM5, _targetClass, _relationshipManager);
	}
	
	@Test
	public void TestSimpleDeclaration() {
		_visitor.visit(VERSION, ACCESS, _classList[0], null, "java/lang/Object", new String[] {});
		
		IClassDeclaration decl = _targetClass.getDeclaration();
		assertEquals("Cheese", decl.getName());
		assertEquals("Object", decl.getSuperType());
		assertEquals(new String[] {}, decl.getInterfaces());
		
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 0);
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.INHERITANCE).size() == 0);
	}
	
	@Test
	public void TestInheritanceDeclaration() {
		_visitor.visit(VERSION, ACCESS, _classList[1], null, _classList[0], new String[] {});
		
		IClassDeclaration decl = _targetClass.getDeclaration();
		assertEquals("ChicagoPizzaIngredientFactory", decl.getName());
		assertEquals("Cheese", decl.getSuperType());
		assertEquals(new String[] {}, decl.getInterfaces());
		
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 0);
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.INHERITANCE).size() == 1);
	}
	
	@Test
	public void TestInheritanceFailureClassDNE() {
		_visitor.visit(VERSION, ACCESS, _classList[1], null, "Thread", new String[] {});
		
		IClassDeclaration decl = _targetClass.getDeclaration();
		assertEquals("ChicagoPizzaIngredientFactory", decl.getName());
		assertEquals("Thread", decl.getSuperType());
		assertEquals(new String[] {}, decl.getInterfaces());
		
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 0);
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.INHERITANCE).size() == 0);
	}
	
	@Test
	public void TestInterfaceDeclaration() {
		_visitor.visit(VERSION, ACCESS, _classList[1], null, "java/lang/Object", new String[] { _classList[_classList.length - 1] });
		
		IClassDeclaration decl = _targetClass.getDeclaration();
		assertEquals("ChicagoPizzaIngredientFactory", decl.getName());
		assertEquals("Object", decl.getSuperType());
		assertEquals(new String[] { "IPizzaStore" }, decl.getInterfaces());
		
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 1);
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.INHERITANCE).size() == 0);
	}
	
	@Test
	public void TestInterfaceAndInheritanceDeclaration() {
		_visitor.visit(VERSION, ACCESS, _classList[1], null, _classList[0], new String[] { _classList[_classList.length - 1] });
		
		IClassDeclaration decl = _targetClass.getDeclaration();
		assertEquals("ChicagoPizzaIngredientFactory", decl.getName());
		assertEquals("Cheese", decl.getSuperType());
		assertEquals(new String[] { "IPizzaStore" }, decl.getInterfaces());
		
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.IMPLEMENTATION).size() == 1);
		assertTrue(_relationshipManager.getRelationshipEdges(RelationshipType.INHERITANCE).size() == 1);
	}
	
	@After
	public void tearDown() {
		_relationshipManager = null;
		_targetClass = null;
		_visitor = null;
		_classList = null;
	}
}
