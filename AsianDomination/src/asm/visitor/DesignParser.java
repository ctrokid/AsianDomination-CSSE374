package asm.visitor;

import java.io.File;

import api.IProjectModel;
import api.IRelationshipManager;
import impl.InputCommand;
import impl.PrintCommand;
import impl.ProjectModel;
import impl.RelationshipManager;
import impl.SequenceInputCommand;
import utils.PackageInspector;

public class DesignParser {
//	 private static String sourceDir =
//	 "C:\\Users\\yangr\\git\\AsianDomination-CSSE374\\AsianDomination\\DummyClasses";
//	 public static final String[] CLASSES =
//	 PackageInspector.getClasses(sourceDir, new File(sourceDir));
	// { "headfirst.factory.pizzaaf.Cheese",
	// "headfirst.factory.pizzaaf.ChicagoPizzaIngredientFactory",
	// "headfirst.factory.pizzaaf.Clams",
	// "headfirst.factory.pizzaaf.FreshClams",
	// "headfirst.factory.pizzaaf.FrozenClams",
	// "headfirst.factory.pizzaaf.MarinaraSauce",
	// "headfirst.factory.pizzaaf.MozzarellaCheese",
	// "headfirst.factory.pizzaaf.NYPizzaIngredientFactory",
	// "headfirst.factory.pizzaaf.PizzaIngredientFactory",
	// "headfirst.factory.pizzaaf.PlumTomatoSauce",
	// "headfirst.factory.pizzaaf.ReggianoCheese",
	// "headfirst.factory.pizzaaf.Sauce",
	// "headfirst.factory.pizzaaf.ThickCrustDough",
	// "headfirst.factory.pizzaaf.ThinCrustDough",
	// "headfirst.factory.pizzafm.NYPizzaStore",
	// };

	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 *
	 * @param args:
	 *            the names of the classes, separated by spaces. For example:
	 *            java DesignParser java.lang.String
	 *            edu.rosehulman.csse374.ClassFieldVisitor java.lang.Math
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {		
		String diagramOutputPath = "input_output/sequenceDiagramTest";
		String asmOutputPath = "";
		
		PrintCommand print = new PrintCommand();
		InputCommand inputCommand = print.run();
		
		IRelationshipManager relationshipManager = new RelationshipManager(inputCommand.getClasses());
		IProjectModel model = null;
		
		if (inputCommand.getCommandType().equals("UML")) {
			asmOutputPath = "input_output/inputCommand.gv";
		} else if(inputCommand.getCommandType().equals("SEQ")){
			asmOutputPath = "input_output/sequenceTest.sd";			
		}
		
		model = new ProjectModel(inputCommand, relationshipManager, asmOutputPath, diagramOutputPath);
		model.parseModel();
	}
}
