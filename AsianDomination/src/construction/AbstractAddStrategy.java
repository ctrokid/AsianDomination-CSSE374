package construction;

import api.IProjectModel;

public abstract class AbstractAddStrategy implements IAddStrategy {
	protected IProjectModel _projectModel;
	
	@Override
	public abstract void buildModel(String[] params);

	@Override
	public void setProjectModel(IProjectModel model) {
		_projectModel = model;
	}
}
