package asm.visitor;

import input.InputCommand;
import utils.CommandGenerator;
import utils.LaunchDiagramGenerator;
import utils.CommandGenerator.ExecuteCommand;
import utils.ProjectConfiguration;

@SuppressWarnings("unused")
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
//		for (ExecuteCommand ex : ExecuteCommand.values()) {
//			InputCommand inputCommand = CommandGenerator.getInputCommand(ex);
//			
//			if (inputCommand == null)
//				continue;
//			
//			inputCommand.execute();
//		}
		
		ProjectConfiguration config = new ProjectConfiguration("resources/config.properties");
		InputCommand cmd = config.getInputCommand();

		cmd.execute();
	}
}
