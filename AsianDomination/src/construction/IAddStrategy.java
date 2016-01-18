package construction;

import api.IProjectModel;

public interface IAddStrategy {
	public void setProjectModel(IProjectModel model);
	public void buildModel(String[] params);
}
