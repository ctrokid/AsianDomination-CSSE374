package integrationTests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import api.IClassField;
import api.IRelationshipManager;
import api.ITargetClass;
import asm.visitor.ClassFieldVisitor;
import impl.ClassDeclaration;
import impl.RelationshipManager;
import impl.TargetClass;

public class TestClassFieldVisitor {
	private IRelationshipManager _relationshipManager;
	private ITargetClass _targetClass;
	@SuppressWarnings("unused")
	private String[] _classList;
	private ClassFieldVisitor _visitor;
	
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
		_targetClass.addPart(new ClassDeclaration("Pizza", "", "java/lang/Object", new String[] {}));
		_visitor = new ClassFieldVisitor(Opcodes.ASM5, _targetClass, _relationshipManager);
	}
	
	@Test
	public void TestSimplePublicStringField() {
		_visitor.visitField(Opcodes.ACC_PUBLIC, "map", "String", null, null);
		
		Collection<IClassField> fields = _targetClass.getFieldParts();
		assertEquals(1, fields.size());
		// FIXME : finish test case
	}
	
	@After
	public void tearDown() {
		_relationshipManager = null;
		_targetClass = null;
		_visitor = null;
		_classList = null;
	}
}