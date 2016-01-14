package asm.visitor;

import api.IProjectModel;
import api.IRelationshipManager;
import impl.InputCommand;
import impl.PrintCommand;
import impl.ProjectModel;
import impl.RelationshipManager;

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
		String diagramOutputPath = "input_output/sequenceDiagramTest";
		String asmOutputPath = "input_output/sequenceTest";	
		
		PrintCommand print = new PrintCommand();
		InputCommand inputCommand = print.run();
		
		IRelationshipManager relationshipManager = new RelationshipManager(inputCommand.getClasses());
		IProjectModel model = null;
		
		model = new ProjectModel(inputCommand, relationshipManager, asmOutputPath, diagramOutputPath);
		model.parseModel();
	}
}
