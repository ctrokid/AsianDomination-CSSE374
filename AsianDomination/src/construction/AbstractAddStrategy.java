package construction;

import api.IProjectModel;

public abstract class AbstractAddStrategy implements IAddStrategy {
	protected IProjectModel _projectModel;

	public AbstractAddStrategy(IProjectModel model) {
		this._projectModel = model;
	}

	@Override
	public abstract void buildModel(String[] params);

}
