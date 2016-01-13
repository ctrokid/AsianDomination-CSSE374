package visitor;

public interface IDiagramOutputStream extends IVisitor {
	public void prepareFile();
	public void endFile(String inputPath, String outputPath);
}
