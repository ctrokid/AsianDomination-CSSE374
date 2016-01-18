package construction;

import api.IProjectModel;

public class SEAddStrategy extends AbstractAddStrategy {
	protected IProjectModel _projectModel;
	
	@Override
	public void setProjectModel(IProjectModel model) {
		_projectModel = model;
	}

	@Override
	public void buildModel(String[] classes) {
		// TODO Auto-generated method stub

	}
	
}
