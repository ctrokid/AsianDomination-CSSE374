package pattern.detection;

import java.util.Properties;

import api.IProjectModel;
import framework.AbstractPhase;
import pattern.decoration.PatternConfig;

public abstract class AbstractPatternDetectionStrategy extends AbstractPhase {
	protected PatternConfig pc;
	public AbstractPatternDetectionStrategy(Properties props) {
		super(props);
		pc = PatternConfig.getInstance();
	}

	@Override
	public void execute(IProjectModel model) {
		detectPatterns(model);
	}

	protected abstract void detectPatterns(IProjectModel model);
	
}
