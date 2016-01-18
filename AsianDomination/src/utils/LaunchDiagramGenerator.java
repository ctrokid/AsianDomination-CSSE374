package utils;

import java.io.IOException;

public class LaunchDiagramGenerator {
	public static enum DiagramFileExtension {
		PDF,
		PNG,
		PS
	}
	
	public static int RunGVEdit(String inputPath, String outputPath, DiagramFileExtension ext) {
		// TODO: this should be added to path. just call 'dot - T...'
		String cmd = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot -T " + ext.toString().toLowerCase()  + " " + inputPath + " -o "+ outputPath + "." + ext.toString().toLowerCase();
		System.out.println("Running Dot Command: ");
		System.out.println(cmd);
		
		int exitVal = -1;
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			exitVal = proc.exitValue();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		if (exitVal == 0)
			System.out.println("Dot diagram successfully generated.");
		else
			System.out.println("Dot diagram failure. Please check " + inputPath + " for the problem.");
		
		return exitVal;
	}
	
	public static int RunSDEdit(String inputPath, String outputPath, DiagramFileExtension ext) {
		String cmd = "java -jar lib/sdedit-4.01.jar -o " + outputPath + "." + ext.toString() + " -t " + ext.toString() + " " + inputPath + ".sd";
		System.out.println("Running SDedit Command: ");
		System.out.println(cmd);

		int exitVal = -1;
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			exitVal = proc.exitValue();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		
		if (exitVal == 0)
			System.out.println("Sequence diagram successfully generated.");
		else
			System.out.println("Sequence diagram failure. Please check " + inputPath + " for the problem.");
		
		return exitVal;
	}
}
