package asm.visitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import api.IRelationshipManager;
import api.ITargetClass;
import impl.RelationshipManager;
import impl.TargetClass;
import utils.LaunchDiagramGenerator;
import utils.PackageInspector;
import utils.LaunchDiagramGenerator.DiagramFileExtension;
import visitor.IVisitor;
import visitor.UMLOutputStream;

public class DesignParser {
	private static String sourceDir = "C:\\Users\\trowbrct\\Desktop\\CSSE374\\AsianDomination-CSSE374\\AsianDomination\\src";
	public static final String[] CLASSES = PackageInspector.getClasses(sourceDir, new File(sourceDir));
		{
//		"headfirst.factory.pizzaaf.Cheese",
//		"headfirst.factory.pizzaaf.ChicagoPizzaIngredientFactory",
//		"headfirst.factory.pizzaaf.Clams",
//		"headfirst.factory.pizzaaf.FreshClams",
//		"headfirst.factory.pizzaaf.FrozenClams",
//		"headfirst.factory.pizzaaf.MarinaraSauce",
//		"headfirst.factory.pizzaaf.MozzarellaCheese",
//		"headfirst.factory.pizzaaf.NYPizzaIngredientFactory",
//		"headfirst.factory.pizzaaf.PizzaIngredientFactory",
//		"headfirst.factory.pizzaaf.PlumTomatoSauce",
//		"headfirst.factory.pizzaaf.ReggianoCheese",
//		"headfirst.factory.pizzaaf.Sauce",
//		"headfirst.factory.pizzaaf.ThickCrustDough",
//		"headfirst.factory.pizzaaf.ThinCrustDough",
//		"headfirst.factory.pizzafm.NYPizzaStore",
	};
	
	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 *
	 * @param args:
	 *            the names of the classes, separated by spaces. For example:
	 *            java DesignParser java.lang.String
	 *            edu.rosehulman.csse374.ClassFieldVisitor java.lang.Math
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//IProjectModel model = new ProjectModel(CLASSES, IRelationshipManager);
		//model.parseAndCreateDiagram();
		ITargetClass[] targetClasses = new TargetClass[CLASSES.length];
		IRelationshipManager relationshipManager = new RelationshipManager(CLASSES);
		
		String asmOutputPath = "input_output/factoryTest.gv";
		String dotOutputPath = "input_output/factoryTest";
		
		OutputStream out = new FileOutputStream(asmOutputPath);
		IVisitor dotOut = new UMLOutputStream(out);
		((UMLOutputStream) dotOut).prepareDotFile("Sans", "8");
		
		for (int i = 0; i < CLASSES.length; i++) {
			targetClasses[i] = new TargetClass();
			
			// ASM's ClassReader does the heavy lifting of parsing the compiled
			// Java class
			ClassReader reader = new ClassReader(CLASSES[i]);
			
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, targetClasses[i], relationshipManager);
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, targetClasses[i], relationshipManager);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, targetClasses[i], relationshipManager);
			
			// TODO: add more DECORATORS here in later milestones to accomplish
			// specific tasks
			// Tell the Reader to use our (heavily decorated) ClassVisitor to
			// visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			
			// All TargetClass instances are populated with data
			// This should print out each class with the internal representation
			targetClasses[i].accept(dotOut);
		}

		// This does the relationship printing
		relationshipManager.accept(dotOut);
		
		((UMLOutputStream) dotOut).endDotFile();
		LaunchDiagramGenerator.RunGVEdit(asmOutputPath, dotOutputPath, DiagramFileExtension.PDF);
	}
}
