package impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import utils.PackageInspector;

public class PrintCommand {

	public InputCommand run() throws Exception {
		InputCommand cmd = null;
		String commandType = "";
		String[] classes = null;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to AsianDomination. You can generate UML and Sequence Diagram. \n"
				+ "UML Diagram example: \"UML, package path or classes names\" \n"
				+ "Squence Diagram example: \"SEQ, package path or classes names, initial class name, method name, depth (default 5)\" \n"
				+ "Please enter specific command below: ");
		
		String line = in.readLine();
		String[] clientInput = line.trim().split(",");
		
		if (clientInput[0].trim().equals("UML")) {
			if (clientInput.length != 2) {
				System.err.println("Incorrect command. Not enough arguments.\n");
				this.run();
			}
			
			classes = handleClassesPath(clientInput[1].trim());
			
			commandType = "UML";
			cmd = new InputCommand(commandType, classes);
			
		} else if (clientInput[0].trim().equals("SEQ")) {
			if (!(clientInput.length <= 5 || clientInput.length >= 4)) {
				System.err.println("Incorrect command. Not enough arguments.\n");
				this.run();
			}
			
			classes = handleClassesPath(clientInput[1].trim());
			String className = clientInput[2].trim();			
			String methodName = clientInput[3].trim();
			int callDepth = 5;
			
			if (isInteger(clientInput[4].trim())) {
				callDepth = Integer.parseInt(clientInput[4].trim());
			}
			
			commandType = "SEQ";
			cmd = new SequenceInputCommand(commandType, classes, className, methodName, callDepth);
		
		} else {
			System.err.println("No such operation is allowed");
			this.run();
		}
		
		return cmd;
	}

	private String[] handleClassesPath(String sourceDir) {
		// TODO : check path for validity
		return PackageInspector.getClasses(sourceDir, new File(sourceDir));
	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
