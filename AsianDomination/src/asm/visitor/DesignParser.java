package asm.visitor;

import api.IProjectModel;
import impl.ProjectModel;
import input.InputCommand;
import utils.CommandGenerator;
import utils.CommandGenerator.ExecuteCommand;

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
		
//		CommandGenerator.SEQUENCE_DIAGRAM_MAX_DEPTH = 3;
		
		for (ExecuteCommand ex : ExecuteCommand.values()) {
			InputCommand inputCommand = CommandGenerator.getInputCommand(ex);
			
			if (inputCommand == null)
				return;
			
			IProjectModel model = new ProjectModel(inputCommand);
			model.parseModel();
		}
	}
}
