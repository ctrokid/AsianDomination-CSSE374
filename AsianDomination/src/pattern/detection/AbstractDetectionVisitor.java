package pattern.detection;

import java.util.Properties;

import api.IProjectModel;
import framework.AbstractPhase;

public abstract class AbstractDetectionVisitor extends AbstractPhase {

	public AbstractDetectionVisitor(Properties props) {
		super(props);
	}

	@Override
	protected abstract void loadConfig(Properties props);

	@Override
	public abstract void execute(IProjectModel model);

}
