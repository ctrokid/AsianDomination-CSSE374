package utils;

import java.io.IOException;

public class LaunchDiagramGenerator {
	public static enum DiagramFileExtension {
		PDF,
		PNG,
		PS
	}
	
	private String _executablePath;
	
	public LaunchDiagramGenerator(String executablePath) {
		_executablePath = executablePath;
	}
	
	public int RunGVEdit(String outputPath, DiagramFileExtension ext) {
		String cmd = _executablePath + " -T " + ext.toString().toLowerCase()  + " " + outputPath + " -o "+ outputPath + "." + ext.toString().toLowerCase();
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
			System.out.println("Dot diagram successfully generated.\n");
		else
			System.out.println("Dot diagram failure. Please check " + outputPath + " for the problem.\n");
		
		return exitVal;
	}
	
	public int RunSDEdit(String outputPath, DiagramFileExtension ext) {
		String cmd = "java -jar lib/sdedit-4.01.jar -o " + outputPath + "." + ext.toString() + " -t " + ext.toString() + " " + outputPath;
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
			System.out.println("Sequence diagram successfully generated.\n");
		else
			System.out.println("Sequence diagram failure. Please check " + outputPath + " for the problem.\n");
		
		return exitVal;
	}
}
