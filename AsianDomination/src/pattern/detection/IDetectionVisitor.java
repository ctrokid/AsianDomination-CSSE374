package pattern.detection;

import api.IProjectModel;
import visitor.IVisitor;

public interface IDetectionVisitor {
	public IVisitor getVisitor();
	public void setProjectModel(IProjectModel model);
}
