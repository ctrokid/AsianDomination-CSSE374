package api;
import visitor.ITraverser;
import visitor.IVisitor;

public interface ITargetClassPart extends ITraverser {
	public void accept(IVisitor v);
}
