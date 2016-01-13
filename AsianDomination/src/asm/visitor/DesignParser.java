package asm.visitor;

import java.io.File;
import java.io.IOException;

import api.IProjectModel;
import api.IRelationshipManager;
import impl.ProjectModel;
import impl.RelationshipManager;
import utils.PackageInspector;

public class DesignParser {
	private static String sourceDir = "C:\\Users\\trowbrct\\Desktop\\CSSE374\\AsianDomination-CSSE374\\AsianDomination\\src";
	public static final String[] CLASSES = PackageInspector.getClasses(sourceDir, new File(sourceDir));
//	{ "headfirst.factory.pizzaaf.Cheese", "headfirst.factory.pizzaaf.ChicagoPizzaIngredientFactory",
//			"headfirst.factory.pizzaaf.Clams", "headfirst.factory.pizzaaf.FreshClams",
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
//	};

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
		IRelationshipManager relationshipManager = new RelationshipManager(CLASSES);

		String asmOutputPath = "input_output/factoryTest.gv";
		String diagramOutputPath = "input_output/factoryTest";
		
		IProjectModel model = new ProjectModel(CLASSES, relationshipManager, asmOutputPath, diagramOutputPath);
		model.parseModel();
	}
}
