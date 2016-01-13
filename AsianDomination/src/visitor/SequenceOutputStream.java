package visitor;

import java.io.IOException;
import java.io.OutputStream;

import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;

public class SequenceOutputStream extends VisitorAdapter {
	private OutputStream out;
	
	public SequenceOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@SuppressWarnings("unused")
	private void write(String s) {
		try {
			this.out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void prepareFile() {
		// Unimplemented. Nothing to do
	}

	@Override
	public void endFile(String inputPath, String outputPath) {
		LaunchDiagramGenerator.RunSDEdit(inputPath, outputPath, DiagramFileExtension.PDF);
	}
}
