package visitor;

import java.io.IOException;
import java.io.OutputStream;

import api.IClassDeclaration;
import impl.ClassDeclaration;
import impl.MethodStatement;
import utils.LaunchDiagramGenerator;
import utils.LaunchDiagramGenerator.DiagramFileExtension;

public class SequenceOutputStream extends VisitorAdapter {
	private OutputStream out;
	
	public SequenceOutputStream(OutputStream out) {
		this.out = out;
		this.setupVisitClassDeclaration();
		this.setupVisitMethodStatement();
	}
	
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
	
	public void setupVisitMethodStatement() {
		super.addVisit(VisitType.Visit, MethodStatement.class, (ITraverser t) -> {
			MethodStatement stmt = (MethodStatement) t;
			StringBuilder sb = new StringBuilder();
			sb.append(stmt.getClassName() + " " + stmt.getMethodName() + " " + stmt.getReturn() + "\n");
			write(sb.toString());
		});
	}
	
	public void setupVisitClassDeclaration() {
		super.addVisit(VisitType.Visit, ClassDeclaration.class, (ITraverser t) -> {
			IClassDeclaration clazz = (ClassDeclaration) t;
			StringBuilder sb = new StringBuilder();
			sb.append("/ " + clazz.getName() + "\n");
			write(sb.toString());
		});
	}

	@Override
	public void endFile(String inputPath, String outputPath) {
		LaunchDiagramGenerator.RunSDEdit(inputPath, outputPath, DiagramFileExtension.PDF);
	}
}
