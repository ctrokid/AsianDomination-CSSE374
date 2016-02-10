package pattern.detection;

import java.util.Properties;

import api.IProjectModel;
import framework.AbstractPhase;

public abstract class AbstractPatternDetectionStrategy extends AbstractPhase {
	
	public AbstractPatternDetectionStrategy(Properties props) {
		super(props);
	}

	@Override
	public void execute(IProjectModel model) {
		detectPatterns(model);
	}

	protected abstract void detectPatterns(IProjectModel model);
	
}
