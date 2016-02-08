package asm.visitor;

import api.IProjectModel;
import impl.ProjectModel;
import input.InputCommand;
import input.SequenceInputCommand;
import utils.CommandGenerator;
import utils.CommandGenerator.ExecuteCommand;
import utils.UMLConfiguration;

public class DesignParser {

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
//		FIXME : when command line integration is working, uncomment this and remove below.
//		String diagramOutputPath = "input_output/sequenceDiagramTest";
//		String asmOutputPath = "input_output/sequenceTest";	
//		
//		PrintCommand print = new PrintCommand(diagramOutputPath, asmOutputPath);
//		InputCommand inputCommand = print.run();
		
//		------------------------------------
//		CODE TO RUN PROJECT SEQUENCE DIAGRAM
//		String initialClass = "asm/visitor/DesignParser";
//		String initialMethod = "main";
//		String initialParams = "String[]";
//		String outputPath = "demo_diagrams/ProjectSD";
//		
//		InputCommand inputCommand = new SequenceInputCommand(outputPath, outputPath, initialClass, initialMethod, initialParams, 5);
		
		for (ExecuteCommand ex : ExecuteCommand.values()) {
			InputCommand inputCommand = CommandGenerator.getInputCommand(ex);
			
			if (inputCommand == null)
				continue;
			
			IProjectModel model = new ProjectModel(inputCommand);
			
			model.buildModel();
			model.printModel();
		}
		
//		UMLConfiguration config = new UMLConfiguration();
//		IProjectModel model = config.getProjectModel();
//		
//		model.buildModel();
//		model.printModel();
	}
}
