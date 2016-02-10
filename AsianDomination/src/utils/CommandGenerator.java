package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fake.FakeUMLAddStrategy;
import framework.IPhase;
import input.InputCommand;
import output.UMLDiagramOutputStream;
import pattern.detection.AdapterPatternDetector;
import pattern.detection.CompositePatternDetector;
import pattern.detection.DecoratorPatternDetector;
import pattern.detection.SingletonDetectionVisitor;

public class CommandGenerator {
	public static final int SEQUENCE_DIAGRAM_MAX_DEPTH = 5;

	public static enum ExecuteCommand {
		M1_Lab1_3Uml,
		M2_AbstractPizzaStoreFactoryUml,
		M3_CollectionsShuffleSD,
		M4_ChocolateBoiler,
		M5_Lab2_1Uml,
		M5_Lab5_1Uml,
		M6_Lab7_2Uml,
		ProjectUml,
		ProjectSD
	}
	
	public static InputCommand getInputCommand(ExecuteCommand cmd) {
		InputCommand inputCommand = null;
		
		switch (cmd) {
			case M1_Lab1_3Uml:
				inputCommand = getM1LabUML();
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
			case M5_Lab2_1Uml:
				inputCommand = getM5Lab2UML();
				break;
			case M5_Lab5_1Uml:
				inputCommand = getM5Lab5UML();
				break;
			case M6_Lab7_2Uml:
				inputCommand = getM6Lab7UML();
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
	
	private static InputCommand getM6Lab7UML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"problem/client/AnimatorApp",
			"problem/graphics/AnimationPanel",
			"problem/graphics/MainWindow",
			"problem/sprites/AbstractSprite",
			"problem/sprites/CircleSprite",
			"problem/sprites/CompositeSprite",
			"problem/sprites/CompositeSpriteIterator",
			"problem/sprites/CrystalBall",
			"problem/sprites/ISprite",
			"problem/sprites/NullSpriteIterator",
			"problem/sprites/RectangleSprite",
			"problem/sprites/RectangleTower",
			"problem/sprites/SpriteFactory"
		};
		
		Properties props = new Properties();
		props.setProperty("output-dir", "demo_diagrams/M6Lab7");
		
		List<IPhase> phases = new ArrayList<IPhase>();
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new CompositePatternDetector(props));
		phases.add(new UMLDiagramOutputStream(props));
		
		cmd = new InputCommand(phases);
		return cmd;
	}
	
	private static InputCommand getM5Lab5UML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"problem/client/App",
			"problem/client/IteratorToEnumerationAdapter",
			"problem/lib/LinearTransformer"
		};
		
		Properties props = new Properties();
		props.setProperty("output-dir", "demo_diagrams/M5Lab5");
		
		List<IPhase> phases = new ArrayList<IPhase>();
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new AdapterPatternDetector(props));
		phases.add(new UMLDiagramOutputStream(props));
		
		cmd = new InputCommand(phases);
		
		return cmd;
	}

	private static InputCommand getM5Lab2UML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"examples/decorator/Beverage",
			"examples/decorator/CondimentDecorator",
			"examples/decorator/Milk",
			"examples/decorator/DarkRoast",
			"examples/decorator/Decaf",
			"examples/decorator/Espresso",
			"examples/decorator/HouseBlend",
			"examples/decorator/Mocha",
			"examples/decorator/Soy",
			"examples/decorator/StarbuzzCoffee",
			"examples/decorator/Whip"
		};
		
		Properties props = new Properties();
		props.setProperty("output-dir", "demo_diagrams/M5Lab2");
		
		List<IPhase> phases = new ArrayList<IPhase>();
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new DecoratorPatternDetector(props));
		phases.add(new UMLDiagramOutputStream(props));
		
		cmd = new InputCommand(phases);
		
		return cmd;
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
		
		Properties props = new Properties();
		props.setProperty("output-dir", "demo_diagrams/M1Lab1-3");
		
		List<IPhase> phases = new ArrayList<IPhase>();
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new UMLDiagramOutputStream(props));
		
		cmd = new InputCommand(phases);
		
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
		
		Properties props = new Properties();
		props.setProperty("output-dir", "demo_diagrams/M2PizzaFactory");
		
		List<IPhase> phases = new ArrayList<IPhase>();
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new UMLDiagramOutputStream(props));
		
		cmd = new InputCommand(phases);
		
		return cmd;
	}

	private static InputCommand getM3CollectionsSD() {
		InputCommand cmd = null;
		String initialClass = "java/util/Collections";
		String initialMethod = "shuffle";
		String initialParams = "List";
		String outputPath = "demo_diagrams/M3CollectionsSD";
		
		// TODO: Get this to work!!!
//		cmd = new SequenceInputCommand(new SDDiagramOutputStream(outputPath, "lib/sdedit-4.01.jar", initialClass, initialMethod, initialParams, SEQUENCE_DIAGRAM_MAX_DEPTH, new Visitor()), initialClass, initialMethod, initialParams, SEQUENCE_DIAGRAM_MAX_DEPTH);
		return cmd;
	}

	private static InputCommand getM4ChocolateBoilerUML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"headfirst/singleton/chocolate/ChocolateBoiler",
			"headfirst/singleton/chocolate/ChocolateController"
		};
		
		Properties props = new Properties();
		props.setProperty("output-dir", "demo_diagrams/M4ChocolateBoiler");
		
		List<IPhase> phases = new ArrayList<IPhase>();
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new SingletonDetectionVisitor(props));
		phases.add(new UMLDiagramOutputStream(props));
		
		cmd = new InputCommand(phases);
		
		return cmd;
	}

	private static InputCommand getProjectUML() {
		InputCommand cmd = null;
		String[] classes = new String[] {
			"api/IClassField",
			"api/IClassMethod",
			"api/IClassDeclaration",
			"api/IMethodStatement",
			"api/IProjectModel",
			"api/ITargetClass",
			"api/ITargetClassPart",
			"api/IRelationshipManager",
			"api/IRelationship",
			"impl/Relationship",
			"impl/RelationshipManager",
			"impl/ClassField",
			"impl/ClassMethod",
			"impl/ClassDeclaration",
			"impl/MethodStatement",
			"impl/ProjectModel",
			"impl/TargetClass",
			"impl/Relationship",
			"asm/visitor/ClassDeclarationVisitor",
			"asm/visitor/ClassFieldVisitor",
			"asm/visitor/ClassMethodVisitor",
			"asm/visitor/MethodAssociationVisitor",
			"asm/visitor/DesignParser",
			"construction/AbstractAddStrategy",
			"construction/SDAddStrategy",
			"construction/UMLAddStrategy",
			"input/InputCommand",
			"output/AbstractDiagramOutputStream",
			"output/SDDiagramOutputStream",
			"output/UMLDiagramOutputStream",
			"utils/AsmClassUtils",
			"utils/DotClassUtils",
			"utils/LaunchDiagramGenerator",
			"utils/PackageInspector",
			"visitor/ITraverser",
			"visitor/IVisitMethod",
			"visitor/IVisitor",
			"visitor/Visitor",
			"pattern/decoration/AbstractTargetClassDecorator",
			"pattern/decoration/AdapterDecorator",
			"pattern/decoration/DecoratorTargetClass",
			"pattern/decoration/CompositeDecorator",
			"pattern/decoration/SingletonDecorator",
			"pattern/detection/AdapterPatternDetector",
			"pattern/detection/DecoratorPatternDetector",
			"pattern/detection/SingletonPatternDetector",
			"pattern/detection/IPatternDetectionStrategy",
			"pattern/detection/CompositePatternDetector"
		};
		String outputPath = "demo_diagrams/ProjectUML";
		
		Properties props = new Properties();
		props.setProperty("output-dir", "demo_diagrams/ProjectUML");
		
		List<IPhase> phases = new ArrayList<IPhase>();
		phases.add(new FakeUMLAddStrategy(props, classes));
		phases.add(new SingletonDetectionVisitor(props));
		phases.add(new DecoratorPatternDetector(props));
		phases.add(new CompositePatternDetector(props));
		phases.add(new AdapterPatternDetector(props));
		phases.add(new UMLDiagramOutputStream(props));
		
		cmd = new InputCommand(phases);
		
		return cmd;
	}

	@SuppressWarnings("unused")
	private static InputCommand getProjectSD() {
		InputCommand cmd = null;
		String initialClass = "asm/visitor/DesignParser";
		String initialMethod = "main";
		String initialParams = "String[]";
		String outputPath = "demo_diagrams/ProjectSD";
		
		// TODO: Get this to work!!!
//		cmd = new SequenceInputCommand(new UMLDiagramOutputStream(outputPath, DOT_PATH, new Visitor()), initialClass, initialMethod, initialParams, 3);
		return cmd;
	}
}
