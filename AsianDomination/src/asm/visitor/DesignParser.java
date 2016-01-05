package asm.visitor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import api.ITargetClass;
import asm.visitor.LaunchDot.DotExtension;
import impl.TargetClass;
import visitor.IVisitor;
import visitor.TargetClassOutputStream;

public class DesignParser {
	public static final String[] CLASSES = {
//			"testClasses.Animal",
//			"testClasses.Dog",
//			"testClasses.Cat",
//			"testClasses.ISoundable"
			"problem.AppLauncher",
			"problem.BrowserLauncher",
			"problem.IApplicationLauncher",
			"problem.IHandler",
			"problem.ILaunchable",
			"problem.Launcher",
			"problem.ModifiedFileHandler",
			"problem.NewFileHandler",
			"problem.NotepadLauncher",
			"problem.PDFLauncher"
	};
	
	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 *
	 * @param args:
	 *            the names of the classes, separated by spaces. For example:
	 *            java DesignParser java.lang.String
	 *            edu.rosehulman.csse374.ClassFieldVisitor java.lang.Math
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ITargetClass[] targetClasses = new TargetClass[CLASSES.length];
		String asmOutputPath = "input_output/lab1-3.gv";
		String dotOutputPath = "input_output/lab1-3";
		
		OutputStream out = new FileOutputStream(asmOutputPath);
		IVisitor dotOut = new TargetClassOutputStream(out);
		((TargetClassOutputStream) dotOut).prepareDotFile("Sans", "8");
		
		for (int i = 0; i < CLASSES.length; i++) {
			targetClasses[i] = new TargetClass();
			
			// ASM's ClassReader does the heavy lifting of parsing the compiled
			// Java class
			ClassReader reader = new ClassReader(CLASSES[i]);
			
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, targetClasses[i]);
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, targetClasses[i]);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, targetClasses[i]);
			
			// TODO: add more DECORATORS here in later milestones to accomplish
			// specific tasks
			// Tell the Reader to use our (heavily decorated) ClassVisitor to
			// visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			
			// All TargetClass instances are populated with data
			// This should print out each class with the internal representation
			targetClasses[i].accept(dotOut);
		}

		// This does the relationship printing
		dotOut.visitCollection(targetClasses);
		
		((TargetClassOutputStream) dotOut).endDotFile();
		LaunchDot.RunGvedit(asmOutputPath, dotOutputPath, DotExtension.PDF);
	}
}
