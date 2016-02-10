package construction;

import api.IProjectModel;
import framework.IPhase;

public interface IAddStrategy extends IPhase {
	public void buildModel(IProjectModel model);
}
