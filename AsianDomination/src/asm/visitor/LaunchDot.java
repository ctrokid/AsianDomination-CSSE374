package asm.visitor;

import java.io.IOException;

public class LaunchDot {
	public static enum DotExtension {
		PDF,
		PNG,
		PS
	}
	
	public static void RunGvedit(String inputPath, String outputPath, DotExtension ext) throws IOException {
		// TODO: this should be added to path. just call 'dot - T...'
		String cmd = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot -T " + ext.toString().toLowerCase()  + " " + inputPath + " -o "+ outputPath + "." + ext.toString().toLowerCase();
		System.out.println("Running Dot Command: ");
		System.out.println(cmd);
		Runtime.getRuntime().exec(cmd);
	}
}
