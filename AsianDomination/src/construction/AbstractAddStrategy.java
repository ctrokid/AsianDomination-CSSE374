package construction;

import java.util.Properties;

import api.IProjectModel;
import framework.AbstractPhase;

public abstract class AbstractAddStrategy extends AbstractPhase {
	protected String[] _params;

	public AbstractAddStrategy(Properties config) {
		super(config);
	}

	protected abstract void buildModel(IProjectModel model);

	@Override
	public void execute(IProjectModel model) {
		buildModel(model);
	}
}
