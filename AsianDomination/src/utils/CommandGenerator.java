package utils;

import input.InputCommand;
import input.SequenceInputCommand;
import input.UMLInputCommand;

public class CommandGenerator {
	public static int SEQUENCE_DIAGRAM_MAX_DEPTH = 5;
	
	public static enum ExecuteCommand {
		M1_Lab1_3Uml,
		M2_AbstractPizzaStoreFactoryUml,
		M3_CollectionsShuffleSD,
		M4_ChocolateBoiler,
		ProjectUml,
		ProjectSD
	}
	
	public static InputCommand getInputCommand(ExecuteCommand cmd) {
		InputCommand inputCommand = null;
		
		switch (cmd) {
			case M1_Lab1_3Uml:
//				inputCommand = getM1LabUML();
				break;
			case M2_AbstractPizzaStoreFactoryUml:
				inputCommand = getM2FactoryUML();
				break;
			case M3_CollectionsShuffleSD:
				inputCommand = getM3CollectionsSD();
				break;
			case M4_ChocolateBoiler:
				inputCommand = getM4ChocolateBoilerUML();
				break;
			case ProjectUml:
				inputCommand = getProjectUML();
				break;
			case ProjectSD:
//				inputCommand = getProjectSD();
				break;
		}
		
		return inputCommand;
	}
	
	private static InputCommand getM1LabUML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"problem/AppLauncher",
			"problem/BrowserLauncher",
			"problem/IApplicationLauncher",
			"problem/IHandler",
			"problem/ILaunchable",
			"problem/Launcher",
			"problem/ModifiedFileHandler",
			"problem/NewFileHandler",
			"problem/NotepadLauncher",
			"problem/PDFLauncher"
		};
		String outputPath = "demo_diagrams/M1Lab1-3";
		
		cmd = new UMLInputCommand(outputPath, outputPath, classes);
		
		return cmd;
	}
	
	private static InputCommand getM2FactoryUML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"headfirst/factory/pizzaaf/ReggianoCheese",
			"headfirst/factory/pizzaaf/FreshClams",
			"headfirst/factory/pizzaaf/ThinCrustDough",
			"headfirst/factory/pizzaaf/MozzarellaCheese",
			"headfirst/factory/pizzaaf/Cheese",
			"headfirst/factory/pizzaaf/NYPizzaIngredientFactory",
			"headfirst/factory/pizzaaf/PizzaIngredientFactory",
			"headfirst/factory/pizzaaf/Clams",
			"headfirst/factory/pizzaaf/Sauce",
			"headfirst/factory/pizzaaf/MarinaraSauce",
			"headfirst/factory/pizzaaf/ChicagoPizzaIngredientFactory",
			"headfirst/factory/pizzaaf/FrozenClams",
			"headfirst/factory/pizzaaf/NYPizzaStore",
			"headfirst/factory/pizzaaf/ThickCrustDough",
			"headfirst/factory/pizzaaf/PlumTomatoSauce"
		};
		String outputPath = "demo_diagrams/M2PizzaFactory";
		
		cmd = new UMLInputCommand(outputPath, outputPath, classes);
		
		return cmd;
	}

	private static InputCommand getM3CollectionsSD() {
		InputCommand cmd = null;
		String initialClass = "java/util/Collections";
		String initialMethod = "shuffle";
		String initialParams = "List";
		String outputPath = "demo_diagrams/M3CollectionsSD";
		
		cmd = new SequenceInputCommand(outputPath, outputPath, initialClass, initialMethod, initialParams, SEQUENCE_DIAGRAM_MAX_DEPTH);
		return cmd;
	}

	private static InputCommand getM4ChocolateBoilerUML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"headfirst/singleton/chocolate/ChocolateBoiler",
			"headfirst/singleton/chocolate/ChocolateController"
		};
		String outputPath = "demo_diagrams/M4ChocolateBoiler";
		
		cmd = new UMLInputCommand(outputPath, outputPath, classes);
		
		return cmd;
	}

	private static InputCommand getProjectUML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"api/IClassField",
			"api/IClassMethod",
			"api/IMethodStatement",
			"api/IProjectModel",
			"api/IRelationshipManager",
			"api/ITargetClass",
			"api/ITargetClassPart",
			"impl/ClassField",
			"impl/ClassMethod",
			"impl/MethodStatement",
			"impl/RelationshipManager",
			"impl/ProjectModel",
			"impl/TargetClass",
			"impl/PrintCommand",
			"asm/visitor/ClassDeclarationVisitor",
			"asm/visitor/ClassFieldVisitor",
			"asm/visitor/ClassMethodVisitor",
			"asm/visitor/MethodAssociationVisitor",
			"asm/visitor/DiagramType",
			"asm/visitor/DesignParser",
			"construction/AbstractAddStrategy",
			"construction/IAddStrategy",
			"construction/SDAddStrategy",
			"construction/UMLAddStrategy",
			"input/InputCommand",
			"input/SequenceInputCommand",
			"input/UMLInputCommand",
			"output/AbstractDiagramOutputStream",
			"output/IDiagramOutputStream",
			"output/SDDiagramOutputStream",
			"output/UMLDiagramOutputStream",
			"utils/AsmClassUtils",
			"utils/DotClassUtils",
			"utils/LaunchDiagramGenerator",
			"utils/PackageInspector",
			"utils/SignatureParser",
			"visitor/ITraverser",
			"visitor/IVisitMethod",
			"visitor/IVisitor",
			"visitor/LookupKey",
			"visitor/VisitorAdapter",
			"visitor/VisitType"
		};
		String outputPath = "demo_diagrams/ProjectUML";
		
		cmd = new UMLInputCommand(outputPath, outputPath, classes);
		
		return cmd;
	}

	@SuppressWarnings("unused")
	private static InputCommand getProjectSD() {
		InputCommand cmd = null;
		String initialClass = "asm/visitor/DesignParser";
		String initialMethod = "main";
		String initialParams = "String[]";
		String outputPath = "demo_diagrams/ProjectSD";
		
		cmd = new SequenceInputCommand(outputPath, outputPath, initialClass, initialMethod, initialParams, 3);
		return cmd;
	}
}
