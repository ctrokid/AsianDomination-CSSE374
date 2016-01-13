package visitor;

public interface IDiagramOutputStream extends IVisitor {
	public void prepareFile();
	public void endFile();
}
