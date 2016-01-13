package impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PrintCommand {

	public String run() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to AsianDomination. You can generate UML and Sequence Diagram. \n"
				+ "UML Diagram example: \"UML, package path or classes names\" \n"
				+ "Squence Diagram example: \"SEQ, class name, method name, depth (default 5)\" \n"
				+ "Please enter specific command below: ");
		String line = in.readLine();
		String[] clientInput = line.trim().split(",");
		if (clientInput[0].trim().equals("UML")) {
			if (clientInput.length != 2) {
				System.err.println("Incorrect command.\n");
				this.run();
			}
			// TODO: NEED TO CHECK INPUT FORMAT FOR PATH
			return "UML";
		} else if (clientInput[0].trim().equals("SEQ")) {
			if (!(clientInput.length <= 4 || clientInput.length >= 3)) {
				System.err.println("Incorrect command.\n");
				this.run();
			}
			if (isInteger(clientInput[3].trim()) == false) {
				System.err.println("Incorrect depth format\n");
				this.run();
			}
			return "SEQ";
		} else {
			System.err.println("No such operation is allowed");
			this.run();
		}
		return "";
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
