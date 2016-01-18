package impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import input.InputCommand;
import input.SequenceInputCommand;
import input.UMLInputCommand;
import utils.PackageInspector;

public class PrintCommand {
	private String _asmOutputPath;
	private String _diagramOutputPath;
	
	public PrintCommand(String diagramOutputPath, String asmOutputPath) {
		_asmOutputPath = asmOutputPath;
		_diagramOutputPath = diagramOutputPath;
	}
	
	public InputCommand run() throws Exception {
		InputCommand cmd = null;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to AsianDomination. You can generate UML and Sequence Diagram. \n"
				+ "UML Diagram example: \"UML| package path or classes names\" \n"
				+ "UML| C:\\Users\\trowbrct\\Desktop\\CSSE374\\AsianDomination-CSSE374\\AsianDomination\\src\n"
				+ "Squence Diagram example: \"SEQ| initial class name| method name| parameters| depth (default 5)\" \n"
				+ "SEQ| DesignParser| main| (String, int)| 3\n"
				+ "Please enter specific command below: \n");
		
		String line = in.readLine();
		String[] clientInput = line.trim().split("|");
		
		if (clientInput[0].trim().equals("UML")) {
			if (clientInput.length != 2) {
				System.err.println("Incorrect command. Not enough arguments.\n");
				this.run();
			}
			
			String[] parameters = handleClassesPath(clientInput[1].trim());
			
			cmd = new UMLInputCommand(_diagramOutputPath, _asmOutputPath, parameters);
			
		} else if (clientInput[0].trim().equals("SEQ")) {
			if (!(clientInput.length <= 5 || clientInput.length >= 4)) {
				System.err.println("Incorrect command. Not enough arguments.\n");
				this.run();
			}
			
			String className = clientInput[1].trim();			
			String methodName = clientInput[2].trim();
			String parameters = clientInput[3].trim();
			int callDepth = 5;
			
			if (isInteger(clientInput[4].trim())) {
				callDepth = Integer.parseInt(clientInput[4].trim());
			}
			
			cmd = new SequenceInputCommand(_diagramOutputPath, _asmOutputPath, className, methodName, parameters, callDepth);
		
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
